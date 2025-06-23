package com.curso.bruno.dto;

import jakarta.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.curso.bruno.domain.Categoria;

public class CategoriaDTO {

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5 , max = 80, message ="Tamanho menor que 5 ou maior que 80 caracteres")
	private String nome;

	public CategoriaDTO() {
	}

	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
