package com.cadastro.cliente.request;
import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ClienteRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigo;
	
	private String	nomeCompleto;
	
	private String	email;
	
	private Date    dataNascimento;
	
	private Long 	codigoTime;
   
	@DateTimeFormat(pattern="aaaa-MM-dd")
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

	public Long getCodigoTime() {
		return codigoTime;
	}

	public void setCodigoTime(Long codigoTime) {
		this.codigoTime = codigoTime;
	}

	public Date getDataDesativacao() {
		return dataDesativacao;
	}

	public void setDataDesativacao(Date dataDesativacao) {
		this.dataDesativacao = dataDesativacao;
	}
	


}
