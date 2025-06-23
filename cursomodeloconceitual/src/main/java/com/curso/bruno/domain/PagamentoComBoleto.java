package com.curso.bruno.domain;

import java.util.Date;

import jakarta.persistence.Entity;

import com.curso.bruno.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataDoVencimento;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataDoPagamento;

	public PagamentoComBoleto() {
	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataDoVencimento,
			Date dataDoPagamento) {
		super(id, estado, pedido);
		this.setDataDoVencimento(dataDoVencimento);
		this.setDataDoPagamento(dataDoPagamento);
	}
	
	public Date getDataDoVencimento() {
		return dataDoVencimento;
	}

	public void setDataDoVencimento(Date dataDoVencimento) {
		this.dataDoVencimento = dataDoVencimento;
	}

	public Date getDataDoPagamento() {
		return dataDoPagamento;
	}

	public void setDataDoPagamento(Date dataDoPagamento) {
		this.dataDoPagamento = dataDoPagamento;
	}


}
