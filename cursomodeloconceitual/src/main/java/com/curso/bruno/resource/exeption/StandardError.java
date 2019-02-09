package com.curso.bruno.resource.exeption;

import java.io.Serializable;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String msg;
	private Long timeStamp;
	
	
	
	
	
	public StandardError(Integer status, String mensagem, Long timeStamp) {
		super();
		this.status = status;
		this.msg = mensagem;
		this.timeStamp = timeStamp;
	}
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMensagem() {
		return msg;
	}
	public void setMensagem(String mensagem) {
		this.msg = mensagem;
	}
	public Long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

}
