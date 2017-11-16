package com.cadastro.cliente.exceptions;

public class RegistroNaoEncontradoException extends ClienteException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RegistroNaoEncontradoException() {
		super();
		this.setCodigoRetorno("-1");
		this.setMensagem("Nenhum registro Encontrado");
	}
	

}
