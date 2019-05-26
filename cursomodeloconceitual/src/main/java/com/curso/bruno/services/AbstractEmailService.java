package com.curso.bruno.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.curso.bruno.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	

	@Override
	public void senderOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = prepareSimpleMailMessage(obj);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareSimpleMailMessage(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setSubject("Pedido Confirmado: "+ obj.getId());
		sm.setText(obj.toString());
		return sm;
	}
	
	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context = new Context();
		context.setVariable("pedido", obj);
		return templateEngine.process("email/confirmacaoPedido", context);
		
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj) {
		MimeMessage mm;
		try {
			mm = prepareMimeMailMessage(obj);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			prepareSimpleMailMessage(obj);
		}
		
	}
	protected MimeMessage prepareMimeMailMessage(Pedido obj) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(obj.getCliente().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pedido Confirmado: "+ obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(obj),true);
		return mimeMessage;
	}

}
