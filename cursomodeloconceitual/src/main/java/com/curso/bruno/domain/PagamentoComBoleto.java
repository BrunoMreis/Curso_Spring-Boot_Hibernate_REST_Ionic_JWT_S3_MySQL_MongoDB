package com.curso.bruno.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.curso.bruno.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.DATE)
	private Date dataDoVencimento;
	
	@Temporal(TemporalType.DATE)
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
