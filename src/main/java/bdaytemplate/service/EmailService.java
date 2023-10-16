package bdaytemplate.service;

import bdaytemplate.dao.EmployeeDAOImp;
import bdaytemplate.dto.EmailRequest;
import bdaytemplate.dto.EmployeeDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	private EmployeeDAOImp employeeDAOImp;

	public EmailService(EmployeeDAOImp employeeDAOImp) {
		this.employeeDAOImp = employeeDAOImp;
	}

	@Value("${spring.mail.username}")
	private String senderEmail;

	public void sendEmailWithInlineImage() throws MessagingException {
		List<EmployeeDetails> to = employeeDAOImp.findAllWithBirthday();

		for (EmployeeDetails value : to) {
			String birthdayImagePath = "src/main/resources/brthday.jpg";
			String secondImagePath = "src/main/resources/Msyslogo.jpg";
			String reportingManagerEmail = value.getReporting_manager();
			String cc = value.getReporting_manager();
			String bcc = senderEmail;

			FileSystemResource birthdayResource = new FileSystemResource(new File(birthdayImagePath));
			FileSystemResource secondResource = new FileSystemResource(new File(secondImagePath));

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			String someVariable = value.getName();
			String subject = "Happy Birthday " + someVariable + "!";
			helper.setSubject(subject);
			helper.setFrom("Talent Management <" + senderEmail + ">");

			if (value.getEmail() != null) {
				helper.addTo(value.getEmail());

				if (reportingManagerEmail != null) {
					helper.addCc(cc);
				}

				helper.addCc(bcc);

				String content = "<b style='font-family: Verdana; color: black;'>Dear " + value.getName() + ",</b><br>"
						+ "<br><span style='font-family: Verdana; color: black;'>Msys wishes you a very Happy Birthday! Enjoy your Beautiful day</span>"
						+ "<br><br><br><img src='cid:brthday' alt='Birthday Image' width='500px' height='339px' /><br><br>"
						+ "<b style='color: black;'>Best Regards,</b><br><b style='color: black;'>Talent Management</b>"
						+ "<br><img src='cid:Msyslogo' alt='Second Image' title='Msys Logo' width='130px' height='50px' /><br><br>";

				helper.setText(content, true);
				helper.addInline("brthday", birthdayResource);
				helper.addInline("Msyslogo", secondResource);

				mailSender.send(message);
			}
		}
	}
	public List<EmployeeDetails> getListOfAllbirthdayEmployee() {
		return employeeDAOImp.findAllWithBirthday();
	}
}
