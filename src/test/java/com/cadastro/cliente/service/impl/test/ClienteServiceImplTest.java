package com.cadastro.cliente.service.impl.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.cadastro.cliente.exceptions.RegistroNaoEncontradoException;
import com.cadastro.cliente.integracao.CampanhaIntegracaoService;
import com.cadastro.cliente.model.Campanha;
import com.cadastro.cliente.model.Cliente;
import com.cadastro.cliente.model.ClienteCampanha;
import com.cadastro.cliente.repository.ClienteRepository;
import com.cadastro.cliente.service.impl.ClienteServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceImplTest {

	
	ClienteServiceImpl service;
	
	@Mock
	ClienteRepository repository;
	
	@Mock
	private CampanhaIntegracaoService campanhaService;
	
	private List<ClienteCampanha> campanhas;
	Cliente cliente;
	
    @Before
	public void setupMock() {
    	
    	service = new ClienteServiceImpl();
		service.setClienteRepository(repository);
		service.setCampanhaService(campanhaService);
		
		cliente = new Cliente();
		cliente.setCodigo(2L);
		cliente.setNomeCompleto("Leandro Silva");
		cliente.setEmail("leandro_abrantes2003@yahoo.com.br");
	    cliente.setCodigoTime(1L);
	    cliente.setDataCadastro(new Date());
	    
	    campanhas = new ArrayList<ClienteCampanha>();
    }

	@Test
	public void consultarTest() throws Exception {
		when(repository.findOne(1L)).thenReturn(cliente);
		Cliente result = service.consultar(1L);
		assertThat(result, is(equalTo(cliente)));

	}
	
	@Test
	public void alterarTest() {
		when(repository.saveAndFlush(cliente)).thenReturn(cliente);
		Cliente result = service.alterar(cliente);
		assertEquals(result, cliente);

	}
	
	@Test
	public void excluirTest() throws com.cadastro.cliente.exceptions.RegistroNaoEncontradoException  {
		when(repository.findOne(1L)).thenReturn(cliente);
		doNothing().when(repository).delete(cliente);
		service.excluir(1L);

	}
    
	@Test(expected=RegistroNaoEncontradoException.class)
	public void excluirExceptionTest() throws RegistroNaoEncontradoException {
		when(repository.findOne(5L)).thenReturn(cliente);
		doNothing().when(repository).delete(cliente);
		service.excluir(3L);

	}
	
	@Test
	public void incluirTest() throws Exception {
		when(repository.saveAndFlush(cliente)).thenReturn(cliente);
		when(campanhaService.buscaCampanhaCliente(1L)).thenReturn(campanhas);
		ResponseEntity<?> result = service.incluir(cliente);
		assertTrue(result instanceof ResponseEntity );

	}
	
}
