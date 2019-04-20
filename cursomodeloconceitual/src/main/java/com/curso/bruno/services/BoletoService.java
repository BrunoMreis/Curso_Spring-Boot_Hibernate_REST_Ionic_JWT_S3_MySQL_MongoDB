package com.curso.bruno.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.curso.bruno.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagmentoComBoleto(PagamentoComBoleto pagamentoComBoleto, Date instante) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instante);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagamentoComBoleto.setDataDoVencimento(cal.getTime());
		
	}

}
