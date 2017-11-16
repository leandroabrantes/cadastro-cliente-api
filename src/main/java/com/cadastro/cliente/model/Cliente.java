package com.cadastro.cliente.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entidade que representa um cliente
 * 
 * @author Leandro Silva
 * 
 */

@Entity
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public Cliente() {
		super();
	}
	public Cliente(Long codigo,String nomeCompleto,String email, Date dataNascimento) {
		this.codigo = codigo;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.dataNascimento = dataNascimento;
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	private Long codigo;
	
	@Column(name="nomeCompleto",nullable=false)
	private String	nomeCompleto;
	
	@Column(name="email",nullable=false,unique=true)
	private String	email;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dataNascimento")
	private Date    dataNascimento;
	
	@Column(name="codigoTime")
	private Long 	codigoTime;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dataCadastro",nullable=false)
	private Date    dataCadastro;

	@Temporal(TemporalType.DATE)
	@Column(name="dataDesativacao",nullable=true)
	private Date    dataDesativacao;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Date getDataDesativacao() {
		return dataDesativacao;
	}
	public void setDataDesativacao(Date dataDesativacao) {
		this.dataDesativacao = dataDesativacao;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Long getCodigoTime() {
		return codigoTime;
	}
	public void setCodigoTime(Long codigoTime) {
		this.codigoTime = codigoTime;
	}
	
	
}
