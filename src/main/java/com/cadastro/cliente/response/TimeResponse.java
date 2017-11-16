package com.cadastro.cliente.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cadastro.cliente.model.Time;

public class TimeResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private List<Time> times = new ArrayList<>();
   
    private String codigoRetorno;
    
    private String mensagemRetorno;

	
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

	public List<Time> getTimes() {
		return times;
	}

	public void setTimes(List<Time> times) {
		this.times = times;
	}

}
