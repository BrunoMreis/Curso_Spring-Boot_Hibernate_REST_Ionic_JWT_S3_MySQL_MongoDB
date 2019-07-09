package com.curso.bruno.dto;

import java.io.Serializable;

import com.curso.bruno.domain.Cidade;

public class CidadeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer estado_id;
	private String nome;
	
	public CidadeDTO() {

	}
	
	public CidadeDTO(Cidade obj) {
		this.estado_id= obj.getId();
		this.nome = obj.getNome();
	}

	public Integer getId() {
		return estado_id;
	}

	public void setId(Integer id) {
		this.estado_id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
	
	

