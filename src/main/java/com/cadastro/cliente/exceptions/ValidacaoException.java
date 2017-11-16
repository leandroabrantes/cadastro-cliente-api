package com.cadastro.cliente.exceptions;

public class ValidacaoException extends ClienteException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ValidacaoException(String codigo,String msg) {
		this.setCodigoRetorno(codigo);
		this.setMensagem(msg);
	}

}
