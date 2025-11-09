package com.curso.bruno.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.curso.bruno.services.EmailService;
import com.curso.bruno.services.MockEmailService;
import com.curso.bruno.services.SmtpEmailService;

@Configuration
@Profile("test")
public class TestMailConfig {

    @Bean
    JavaMailSender javaMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        // Configure minimal settings to avoid external connections during tests
        sender.setHost("localhost");
        sender.setPort(25);
        return sender;
    }

    @Bean
    MailSender mailSender(JavaMailSender javaMailSender) {
        // JavaMailSender extends MailSender, so reuse the same instance
        return javaMailSender;
    }
    
    @Bean
    SmtpEmailService smtpEmailService() {
		return new SmtpEmailService();
	}
    
	@Bean
	EmailService emailService() {
			return new MockEmailService();
	}
	

}
