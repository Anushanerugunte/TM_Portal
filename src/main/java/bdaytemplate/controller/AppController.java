package bdaytemplate.controller;

import bdaytemplate.dto.EmployeeDetails;
import bdaytemplate.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailService emailService;

    @PostMapping("/error")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        System.out.println("Inside welcome controller");
        return modelAndView;
    }
    @GetMapping("/logout")
    public ModelAndView exit() {
        ModelAndView modelAndView = new ModelAndView("login");
        System.out.println("Inside welcome controller");
        return modelAndView;
    }
    @GetMapping("/welcomepage")
    public ModelAndView welcome() {
        ModelAndView modelAndView = new ModelAndView("welcomepage");
        System.out.println("Inside welcome controller");
        return modelAndView;
    }
    @GetMapping("/emailwithimage")
    public String sendHTMLEmailWithInlineImage1(Model model) throws MessagingException {
        emailService.sendEmailWithInlineImage();
        model.addAttribute("message", "Email Sent Successfully");
        return "result";
    }

    @Scheduled(cron = "0 2 12 * * ?", zone = "Asia/Kolkata")
    public void sendHTMLEmailWithInlineImageDaily() throws MessagingException, IOException {
        emailService.sendEmailWithInlineImage();
    }

    @GetMapping("/birthday")
    public ModelAndView getListOfAllanniversaryEmployee() {
        List<EmployeeDetails> list = emailService.getListOfAllbirthdayEmployee();
        System.out.println(list);
        ModelAndView model = new ModelAndView();
        model.addObject("list", list);
        model.setViewName("birthday");
        return model;
    }
}
