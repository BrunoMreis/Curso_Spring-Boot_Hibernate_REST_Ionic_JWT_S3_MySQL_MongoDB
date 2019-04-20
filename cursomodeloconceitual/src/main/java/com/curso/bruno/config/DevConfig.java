package com.curso.bruno.config;



import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.curso.bruno.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {
	@Autowired
	private DBService dbSERVICE;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	protected String strategy;
	
	@Bean
	public boolean instantiateDatebase() throws ParseException  {
		
		if(!"creat".equals(strategy)) {
			return false;
		}
		
		dbSERVICE.instantiateTestDatabase();
		return true;
	}

}
