package com.cadastro.cliente.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cadastro.cliente.exceptions.MensagemRetorno;
import com.cadastro.cliente.exceptions.RegistroNaoEncontradoException;
import com.cadastro.cliente.model.Cliente;
import com.cadastro.cliente.request.ClienteRequest;
import com.cadastro.cliente.response.ClienteResponse;
import com.cadastro.cliente.service.ClienteService;

/**
 * Controller responsavel por receber as requisições a api de cliente
 * 
 * @Autor: Leandro Silva
 * @since: 11/2017
 * 
 */
@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@GetMapping("/busca")
	public ResponseEntity<ClienteResponse> consultar(ClienteRequest request) throws RegistroNaoEncontradoException {

		ClienteResponse response = new ClienteResponse(); 
		response.getClientes().add(service.consultar(request.getCodigo()));
		return new ResponseEntity<ClienteResponse>(response,HttpStatus.OK);
	}
	
	@GetMapping("/lista")
	public ResponseEntity<ClienteResponse> listar() {
		ClienteResponse response = new ClienteResponse(); 
		response.setClientes(service.listar());
		
		return new ResponseEntity<ClienteResponse>(response,HttpStatus.OK);
	}
    @PostMapping("/salva")
	public ResponseEntity<?> salvar(ClienteRequest request) throws Exception{
    	Cliente cliente = new Cliente();
    	BeanUtils.copyProperties(request, cliente);
 
    	return service.incluir(cliente);   
		
	}
 
    @DeleteMapping("/exclui")
	public ResponseEntity<MensagemRetorno> excluir(ClienteRequest request) throws RegistroNaoEncontradoException{
    	service.excluir(request.getCodigo());
    	return new ResponseEntity<MensagemRetorno>(new MensagemRetorno("0", "Cliente excluído com sucesso"),HttpStatus.OK );
	}
    
    @PutMapping("/altera")
	public ResponseEntity<MensagemRetorno> alterar(ClienteRequest request){
    	Cliente cliente = new Cliente();
    	BeanUtils.copyProperties(request, cliente);
    	service.alterar(cliente);
    	return new ResponseEntity<MensagemRetorno>(new MensagemRetorno("0", "Cliente alterado com sucesso"),HttpStatus.OK );
	}
    
    
}
