package com.curso.bruno.resource.exeption;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	List<FieldMessage> errors = new ArrayList<FieldMessage>();
	
	public ValidationError(Integer status, String mensagem, Long timeStamp) {
		super(status, mensagem, timeStamp);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addErrors(String fildName, String msg) {
		this.errors.add(new FieldMessage(fildName, msg));
	}
		

	
}
