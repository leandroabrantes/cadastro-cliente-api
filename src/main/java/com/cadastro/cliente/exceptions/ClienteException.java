package com.cadastro.cliente.exceptions;

/**
 * Classe que representa uma exceção generia da api de cliente
 * 
 * @Autor: Leandro Silva
 * @since: 11/2017
 * 
 */
public class ClienteException extends Exception {

	private static final long serialVersionUID = 1L;

	private String codigoRetorno;
	private String mensagem;

	public String getCodigoRetorno() {
		return codigoRetorno;
	}

	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
