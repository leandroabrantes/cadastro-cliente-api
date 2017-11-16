package com.cadastro.cliente.model;

import java.io.Serializable;

public class ClienteCampanha implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long codigoCliente;
	private Long codigoCampanha;
  
	private ClienteCampanha clienteCampanha;
    
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getCodigoCliente() {
		return codigoCliente;
	}


	public void setCodigoCliente(Long codigoCliente) {
		this.codigoCliente = codigoCliente;
	}


	public Long getCodigoCampanha() {
		return codigoCampanha;
	}


	public void setCodigoCampanha(Long codigoCampanha) {
		this.codigoCampanha = codigoCampanha;
	}


	public ClienteCampanha getClienteCampanha() {
		return clienteCampanha;
	}


	public void setClienteCampanha(ClienteCampanha clienteCampanha) {
		this.clienteCampanha = clienteCampanha;
	}


	

	
}
