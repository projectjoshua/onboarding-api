package com.stg.helpers;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.stg.daos.ConfigDao;
import com.stg.models.Config;
import com.stg.models.User;

import freemarker.template.Configuration;

/**
 * Class that handles the sending of emails
 *
 * @author David Landes
 */
@Component
public class MailHelper {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ConfigDao configDao;

    @Autowired
    private Configuration freemarkerEngine;

    @PostConstruct
    public void init() {
	JavaMailSenderImpl ms = (JavaMailSenderImpl) mailSender;
	Config hostConfig = configDao.findOne("EMAIL_HOST");
	Config userConfig = configDao.findOne("EMAIL_USERNAME");
	Config passConfig = configDao.findOne("EMAIL_PASSWORD");
	Config portConfig = configDao.findOne("EMAIL_PORT");

	if (hostConfig != null) {
	    ms.setHost(hostConfig.getValue());
	}
	if (userConfig != null) {
	    ms.setUsername(userConfig.getValue());
	}
	if (passConfig != null) {
	    ms.setPassword(passConfig.getValue());
	}
	if (portConfig != null) {
	    ms.setPort(Integer.valueOf(portConfig.getValue()));
	}
    }

    /**
     * Sends the mail to the specified address using the subject, template name, and parameters to be passed to the template. The templates are found in the src/main/resources/templates directory
     *
     * @param toAddress
     * @param subject
     * @param templateName
     * @param params
     * @throws MessagingException
     */
    public void sendMail(String toAddress, String subject, String templateName, Map<String, Object> params) throws MessagingException {
	MimeMessagePreparator preparator = new MimeMessagePreparator() {
	    @Override
	    public void prepare(MimeMessage mimeMessage) throws Exception {
		MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
		message.setTo(toAddress);
		message.setFrom(((JavaMailSenderImpl) mailSender).getUsername());
		message.setSubject(subject);

		String text = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerEngine.getTemplate("/" + templateName + ".ftl", "UTF-8"), params);

		message.setText(text, true);
	    }
	};
	mailSender.send(preparator);
    }

    /**
     * Sends the welcome email based on the user and whether or not the user already exists. If the user already exists, it will send the welcome back version of the email.
     *
     * @param user
     * @param isExistingUser
     * @throws Exception
     */
    public void sendWelcomeEmail(User user, boolean isExistingUser) throws Exception {
	String subject = null;
	String templateName = null;
	Map<String, Object> templateMap = new HashMap<String, Object>();

	if (isExistingUser) {
	    subject = "Welcome Back!";
	    templateName = "welcome";
	} else {
	    subject = "Welcome!";
	    templateName = "welcome";
	}

	// Set the configurations
	templateMap.put("emailAddress", user.getEmail());
	templateMap.put("name", user.getFirstName() + " " + user.getLastName());

	try {
	    this.sendMail(user.getEmail(), subject, templateName, templateMap);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw e;
	}
    }
}
