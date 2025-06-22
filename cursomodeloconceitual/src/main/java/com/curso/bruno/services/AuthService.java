package com.curso.bruno.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.curso.bruno.domain.Cliente;
import com.curso.bruno.repositories.ClienteRepository;
import com.curso.bruno.services.exeption.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private EmailService emailService;
	
	
	private Random randon = new Random();

	public void sendNewPassword(String email) {

		Cliente cliente = clienteRepository.findByEmail(email);
		if (cliente == null) {
			throw new ObjectNotFoundException("E-mail Não encontrado!");
		}
		
		String newPass = newPassword();
		cliente.setSenha(pe.encode(newPass));
		
		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPass);
	}

	private String newPassword() {
		char vet[] = new char[10];
		for (int i = 0; i < vet.length; i++) {
			vet[i] = randonChar();		
		}
		return String.valueOf(vet);
	}

	private char randonChar() {
		int opt = randon.nextInt(3);
		if(opt == 0) {//gera uma digito
			return (char) (randon.nextInt(10) +48);
		}
		if(opt == 1) {//gera uma letra minuscula
			return (char) (randon.nextInt(26) +65);
		}
		else  {//gera uma letra minuscula
			return (char) (randon.nextInt(26) +97);
		}
		
	}
}
