package com.curso.bruno.services;

import jakarta.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {

	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Testando envido de e-mail...");
		LOG.info(msg.toString());
		LOG.info("E-mail enviado");
		
	}


	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Testando envido de e-mail HTML...");
		LOG.info(msg.toString());
		LOG.info("E-mail enviado");
		
	}

}
