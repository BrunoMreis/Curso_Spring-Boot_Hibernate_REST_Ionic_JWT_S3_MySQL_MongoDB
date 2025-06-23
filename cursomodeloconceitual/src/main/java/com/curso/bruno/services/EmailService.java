package com.curso.bruno.services;

import jakarta.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.curso.bruno.domain.Cliente;
import com.curso.bruno.domain.Pedido;

public interface EmailService {

	void senderOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);

	void sendOrderConfirmationHtmlEmail(Pedido obj);

	void sendHtmlEmail(MimeMessage msg);

	void sendNewPasswordEmail(Cliente cliente, String newPass);

}
