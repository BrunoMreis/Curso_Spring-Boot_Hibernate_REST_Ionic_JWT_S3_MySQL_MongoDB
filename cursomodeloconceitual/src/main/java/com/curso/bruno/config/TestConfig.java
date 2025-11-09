package com.curso.bruno.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.curso.bruno.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {
	
	private DBService dbSERVICE;
	
	
	public TestConfig(@Autowired DBService dbSERVICE) {
		this.dbSERVICE = dbSERVICE;
	}
	
	@Bean
	boolean instantiateDatebase() throws java.text.ParseException  {
		dbSERVICE.instantiateTestDatabase();
		return true;
	}
	

}
