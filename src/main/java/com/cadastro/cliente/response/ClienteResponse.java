package com.cadastro.cliente.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cadastro.cliente.model.Cliente;

public class ClienteResponse implements Serializable {

	private static final long serialVersionUID = 1L;

    private List<Cliente> clientes = new ArrayList<>();
   
    private String codigoRetorno;
    
    private String mensagemRetorno;

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public String getCodigoRetorno() {
		return codigoRetorno;
	}

	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

	public String getMensagemRetorno() {
		return mensagemRetorno;
	}

	public void setMensagemRetorno(String mensagemRetorno) {
		this.mensagemRetorno = mensagemRetorno;
	}


}
