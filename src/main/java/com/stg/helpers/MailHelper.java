package com.stg.helpers;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.stg.daos.ConfigDao;
import com.stg.models.Config;

@Component
public class MailHelper {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ConfigDao configDao;

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

    public void sendMail() throws MessagingException {
	// TODO: Change this to use the emails found in the database
	MimeMessage mail = mailSender.createMimeMessage();

	MimeMessageHelper helper = new MimeMessageHelper(mail, true);
	helper.setTo("david@landesapps.com");
	helper.setReplyTo("david.landes@stgconsulting.com");
	helper.setFrom("david.landes@stgconsulting.com");
	helper.setSubject("Email Test");
	helper.setText("YAY!");

	mailSender.send(mail);
    }
}
