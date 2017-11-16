package com.cadastro.cliente.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cadastro.cliente.exceptions.ClienteException;
import com.cadastro.cliente.exceptions.IntegracaoException;
import com.cadastro.cliente.exceptions.RegistroNaoEncontradoException;
import com.cadastro.cliente.model.Cliente;

public interface ClienteService {
	
	public ResponseEntity<?> incluir(Cliente cliente) throws  ClienteException, Exception;
	
	public List<Cliente> listar();
	
	public Cliente consultar(Long codigo) throws RegistroNaoEncontradoException;
	
	public void excluir(Long codigo) throws RegistroNaoEncontradoException;
	
	public Cliente alterar(Cliente cliente);
	
	public void associarClienteCampanha(Long codigoCliente,Long codigoCampanha) throws IntegracaoException;

}
