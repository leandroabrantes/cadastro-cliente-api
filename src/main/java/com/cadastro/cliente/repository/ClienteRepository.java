package com.cadastro.cliente.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cadastro.cliente.model.Cliente;
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  
	
	public Cliente findByEmailAndDataDesativacao(String email, Date dataDesativacao);

}
