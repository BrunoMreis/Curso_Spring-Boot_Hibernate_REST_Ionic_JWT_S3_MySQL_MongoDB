package com.curso.bruno;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CursomodeloconceitualApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(CursomodeloconceitualApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
	
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}