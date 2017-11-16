package com.cadastro.cliente.model;

import java.io.Serializable;
import java.util.List;


/**
 * Entidade que representa um time(clube do coração)
 * 
 * @author Leandro Silva
 * 
 */

public class Time implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long codigo;
	
	private String nome;
	
	private List<Campanha> campanhas;

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

	public List<Campanha> getCampanhas() {
		return campanhas;
	}

	public void setCampanhas(List<Campanha> campanhas) {
		this.campanhas = campanhas;
	}


}
