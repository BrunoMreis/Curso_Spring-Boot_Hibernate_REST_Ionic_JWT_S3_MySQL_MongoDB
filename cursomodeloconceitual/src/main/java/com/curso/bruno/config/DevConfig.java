package com.curso.bruno.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.curso.bruno.services.DBService;
import com.curso.bruno.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	private DBService dbSERVICE;
	
	protected String strategy;
	
	public DevConfig(@Autowired DBService dbSERVICE, @Value("${spring.jpa.hibernate.ddl-auto}") String strategy) {
		this.dbSERVICE = dbSERVICE;
		this.strategy = strategy;
	}
	
	@Bean
	boolean instantiateDatebase() throws ParseException  {
		
//		if(!"creat".equals(strategy)) {
//			return false;
//		}não está povoando tabelo sem o creat
		
		dbSERVICE.instantiateTestDatabase();
		return true;
	}
	
	@Bean
	SmtpEmailService emailService() {
			return new SmtpEmailService();
	}

}
