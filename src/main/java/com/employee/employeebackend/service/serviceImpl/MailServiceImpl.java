package com.employee.employeebackend.service.serviceImpl;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.employee.employeebackend.entity.Employee;
import com.employee.employeebackend.exception.BadDataException;
import com.employee.employeebackend.service.MailService;

@Service
public class MailServiceImpl implements MailService {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	TemplateEngine templateEngine;
	
	@Override
    public void sendMail(final String to, final String subject,
                         final String text) {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
//            message.setFrom(AppConstants.INFO_EMAIL_ID);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new BadDataException(e.getLocalizedMessage());
        }
    }

	@Override
	public void esafNotification(Employee employee) {
        Context ctx = new Context();
        ctx.setVariable("firstName", employee.getFirstName());
        ctx.setVariable("lastName", employee.getLastName());
        ctx.setVariable("emailId", employee.getEmailId());
        String text = templateEngine.process("esafNotification.html", ctx);
        String subject = "ESAF APPLICATION";
        sendMail(employee.getEmailId(), subject, text);
	}

}
