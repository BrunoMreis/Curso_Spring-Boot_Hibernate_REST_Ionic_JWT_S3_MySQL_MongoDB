package com.curso.bruno.domain.enums;

public enum EstadoPagamento {
	
	PENDENTE(1,"Pendente"),
	QUITADO(2,"Quitado"),
	CANCELADO(3,"Cancelado");
	
	
	private Integer cod;
	private String estado;
	
	
	private EstadoPagamento(Integer cod, String estado) {
		this.setCod(cod);
		this.setEstado(estado);
	}


	public Integer getCod() {
		return cod;
	}


	public void setCod(Integer cod) {
		this.cod = cod;
	}



	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}

	public static EstadoPagamento toEnum(Integer id) {

		if (id == null) {
			return null;
		}

		for (EstadoPagamento x : EstadoPagamento.values()) {
			if (id.equals(x.getCod())) {
				return x;
			}

		}
		throw new IllegalArgumentException("Id inválido " + id);
	}
	
}
