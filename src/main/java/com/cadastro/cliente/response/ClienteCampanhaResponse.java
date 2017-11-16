package com.cadastro.cliente.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cadastro.cliente.model.ClienteCampanha;


public class ClienteCampanhaResponse implements Serializable {

	private static final long serialVersionUID = 1L;

    private List<ClienteCampanha> campanhas = new ArrayList<>();
   
    private String codigoRetorno;
    
    private String mensagemRetorno;

	public List<ClienteCampanha> getCampanhas() {
		return campanhas;
	}

	public void setCampanhas(List<ClienteCampanha> campanhas) {
		this.campanhas = campanhas;
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
