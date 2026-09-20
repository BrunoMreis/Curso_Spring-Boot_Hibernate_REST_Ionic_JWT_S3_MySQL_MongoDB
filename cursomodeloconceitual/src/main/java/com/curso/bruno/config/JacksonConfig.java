package com.curso.bruno.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.curso.bruno.domain.PagamentoComBoleto;
import com.curso.bruno.domain.PagamentoComCartao;

import tools.jackson.databind.ObjectMapper;

@Configuration
public class JacksonConfig {
// https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare
//Atualizado para Jackson 3.x
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper().rebuild()
				.registerSubtypes(PagamentoComCartao.class, PagamentoComBoleto.class)
				.build();
	}
}