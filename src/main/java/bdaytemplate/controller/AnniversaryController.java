package bdaytemplate.controller;

import bdaytemplate.dto.EmployeeDetails;
import bdaytemplate.service.AnniversaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Controller
public class AnniversaryController {

	@Autowired(required = true)
	private JavaMailSender mailSender;

	@Autowired
	private AnniversaryService anniversaryService;

	@PostMapping("/errors")
	public String viewHomePage() {
		return "index";
	}

	@GetMapping("/images")
	public String sendHTMLEmailWithInlineImage1(Model model) throws MessagingException {
		anniversaryService.sendEmailWithInlineImage();
		model.addAttribute("message", "An anniversary mail sent");
		return "result";
	}

	@Scheduled(cron = "0 02 13 * * ?", zone = "Asia/Kolkata")
	public void sendHTMLEmailWithInlineImageDaily() throws MessagingException, IOException {
		anniversaryService.sendEmailWithInlineImage();
	}

	@GetMapping("/adding")
	public ModelAndView addEmployeeDetail() {
		ModelAndView modelAndView = new ModelAndView("add");
		System.out.println("Inside add employee");
		return modelAndView;
	}
	@PostMapping("/add-Inventryitems")
	public ModelAndView saveEmployee(
			@RequestParam("employeeid") String employeeid,
			@RequestParam("name") String name,
			@RequestParam("joiningdate") @DateTimeFormat(pattern = "dd-mm-yyyy") LocalDate joiningdate,
			@RequestParam("birthdate") @DateTimeFormat(pattern = "dd-mm-yyyy") LocalDate birthdate,
			@RequestParam("email") String email,
			@RequestParam("reporting_manager") String reporting_manager) {

		ModelAndView model = new ModelAndView();
		boolean employeeExists = anniversaryService.employeeIdExists(employeeid);
		if (employeeExists) {
			model.addObject("errorMessage", "Employee ID already exists");
		} else {
			EmployeeDetails employee = new EmployeeDetails(employeeid, name, joiningdate, birthdate, email, reporting_manager);
			anniversaryService.saveEmployee(employee);
			model.addObject("successMessage", "Employee added successfully");
		}

		model.setViewName("add"); // Set the view name to the same form page
		return model;
	}


	@GetMapping("/")
	public ModelAndView getListOfAllEmployeeindexpage() {
		List<EmployeeDetails> list = anniversaryService.getListOfAllEmployee();
		System.out.println(list);
		ModelAndView model = new ModelAndView();
		model.addObject("list", list);
		model.setViewName("get");
		return model;
	}

	@GetMapping("/getAllEmployees")
	public ModelAndView getListOfAllEmployee() {
		List<EmployeeDetails> list = anniversaryService.getListOfAllEmployee();
		System.out.println(list);
		ModelAndView model = new ModelAndView();
		model.addObject("list", list);
		model.setViewName("get");
		return model;
	}
	@GetMapping("/anniversary")
	public ModelAndView getListOfAllanniversaryEmployee() {
		List<EmployeeDetails> list = anniversaryService.getListOfAllanniversaryEmployee();
		System.out.println(list);
		ModelAndView model = new ModelAndView();
		model.addObject("list", list);
		model.setViewName("anniversary");
		return model;
	}

	@GetMapping("/deleteing")
	public ModelAndView getListOfAllEmployeeDelete() {
		List<EmployeeDetails> list = anniversaryService.getListOfAllEmployee();
		System.out.println(list);
		ModelAndView model = new ModelAndView();
		model.addObject("list", list);
		model.setViewName("Deleting_page");
		return model;
	}

	@GetMapping("/del/{id}")
	public ModelAndView deleteProductById(@PathVariable String id) {
		anniversaryService.deleteEmployeeById(id);
		List<EmployeeDetails> list = anniversaryService.getListOfAllEmployee();

		ModelAndView model = new ModelAndView();
		model.addObject("list", list);

		model.setViewName("get");
		return model;
	}

	@GetMapping("/updating")
	public ModelAndView getListOfAllEmployeeUpdate() {
		List<EmployeeDetails> list = anniversaryService.getListOfAllEmployee();
		System.out.println(list);
		ModelAndView model = new ModelAndView();
		model.addObject("list", list);
		model.setViewName("updating_page");
		return model;
	}

	@RequestMapping(value = "updating/employees/{employeeid}", method = RequestMethod.GET)
	public ModelAndView updatePage(@PathVariable("employeeid") String employeeid) {
		ModelAndView model = new ModelAndView("update");

		model.addObject("employeeid", employeeid);
		model.addObject("employee", anniversaryService.getEmployeeById(employeeid));
		return model;

	}

	@RequestMapping(value = "updates/users", method = RequestMethod.POST)
	public ModelAndView updateUser(
			@RequestParam String employeeid,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "birthdate", required = true) String birthdateStr,
			@RequestParam(value = "joiningdate", required = true) String joiningdateStr,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "reporting_manager", required = true) String reporting_manager) {

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate birthdate = LocalDate.parse(birthdateStr, formatter);
			LocalDate joiningdate = LocalDate.parse(joiningdateStr, formatter);

			EmployeeDetails userDetail = new EmployeeDetails();
			userDetail.setEmployeeid(employeeid);
			userDetail.setName(name);
			userDetail.setBirthdate(birthdate);
			userDetail.setJoiningdate(joiningdate);
			userDetail.setEmail(email);
			userDetail.setReporting_manager(reporting_manager);

			int resp = anniversaryService.update(userDetail);
			ModelAndView model = new ModelAndView();

			if (resp > 0) {
				model.addObject("successMessage", " Employee details updated successfully.");
				model.addObject("list", anniversaryService.getListOfAllEmployee());
				model.setViewName("get");
				return model;
			} else {
				model.addObject("msg", "User with id : " + employeeid + " updation failed.");
				model.addObject("userDetail", anniversaryService.getEmployeeById(employeeid));
				model.setViewName("update");
				return model;
			}
		} catch (DateTimeParseException e) {
			ModelAndView model = new ModelAndView();
			model.addObject("employeeid", employeeid);
			model.addObject("msg", "Invalid date format. Please use dd-MM-yyyy.");
			model.addObject("userDetail", anniversaryService.getEmployeeById(employeeid));
			model.setViewName("update");
			return model;
		}
	}

	@GetMapping("/employees/{id}")
	public ModelAndView searchEmployeeById(@PathVariable String id) {
		List<EmployeeDetails> list = anniversaryService.getEmployeeById(id);
		ModelAndView model = new ModelAndView();
		model.addObject("list",list);
		model.setViewName("getbyid");
		return model;
	}
}
