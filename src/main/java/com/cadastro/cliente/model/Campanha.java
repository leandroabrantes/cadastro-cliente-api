package com.cadastro.cliente.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Entidade que representa uma campanha
 * 
 * @author Leandro Silva
 * 
 */

public class Campanha implements Serializable,Comparable<Campanha>{

	private static final long serialVersionUID = 1L;
	
	public Campanha() {
		super();
	}
	public Campanha(Long codigo,String	nome,Time time,Date dataInicio,Date dataFim) {
		this.codigo = codigo;
		this.nome = nome;
		this.time = time;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		
	}
	
	private Long codigo;
	
	private String	nome;
   
	
	private Time time;
    
	private Date dataInicio;
    
	private Date dataFim;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	@Override
	public int compareTo(Campanha o) {
		if (o.getCodigo() > this.getCodigo()) {
			return 1;
		} else if (o.getCodigo() < this.getCodigo()) {
			return -1;
		}else {
			return 0;
		}
	}

	
	
}
