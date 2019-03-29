package com.curso.bruno.resource.exeption;

import java.io.Serializable;

public class FieldMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String msg;
	
	public FieldMessage() {
	}

	public FieldMessage(String nome, String msg) {
		this.nome = nome;
		this.msg = msg;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	
}
