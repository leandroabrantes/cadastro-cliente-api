package com.cadastro.cliente.exceptions;

public class TimeInvalidoExcetpion extends ClienteException {

	
	private static final long serialVersionUID = 1L;
	
	public TimeInvalidoExcetpion(String codigo,String msg) {
		this.setCodigoRetorno(codigo);
		this.setMensagem(msg);
	}

}
