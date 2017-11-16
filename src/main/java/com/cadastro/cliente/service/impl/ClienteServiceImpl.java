package com.cadastro.cliente.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.cadastro.cliente.exceptions.IntegracaoException;
import com.cadastro.cliente.exceptions.MensagemRetorno;
import com.cadastro.cliente.exceptions.RegistroNaoEncontradoException;
import com.cadastro.cliente.exceptions.ValidacaoException;
import com.cadastro.cliente.integracao.CampanhaIntegracaoService;
import com.cadastro.cliente.model.Campanha;
import com.cadastro.cliente.model.Cliente;
import com.cadastro.cliente.model.ClienteCampanha;
import com.cadastro.cliente.repository.ClienteRepository;
import com.cadastro.cliente.service.ClienteService;

@Component
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private CampanhaIntegracaoService campanhaService;


	@Override
	public ResponseEntity<?> incluir(Cliente novocliente) throws ValidacaoException {

		validarParametros(novocliente);

		try {
			Cliente clienteCadastrado = buscarClientePorEmail(novocliente.getEmail());
			if (clienteCadastrado != null) {
				return executarFluxoCadastroExistente(clienteCadastrado.getCodigo(), novocliente.getCodigo());
			} else {
				novocliente.setDataCadastro(new Date());
				novocliente = clienteRepository.save(novocliente);
				return executarFluxoCadastroNovo(novocliente);
			}
		} catch (Exception e) {
			// TODO Implementar Log
			e.printStackTrace();
			return new ResponseEntity<MensagemRetorno>(new MensagemRetorno("0", "Cliente salvo com sucesso"),HttpStatus.OK);
		}

	}

	private ResponseEntity<?> executarFluxoCadastroNovo(Cliente cliente) throws IntegracaoException {

		List<Campanha> campanhasAtivas = buscaCampanhasPorTimeCliente(cliente.getCodigoTime());
		if (campanhasAtivas != null && campanhasAtivas.size() > 0) {
			associarClienteCampanha(campanhasAtivas, cliente.getCodigo());
		}
		
		return new ResponseEntity<MensagemRetorno>(new MensagemRetorno("0", "Cliente salvo com sucesso"),HttpStatus.OK);

	}

	private ResponseEntity<?> executarFluxoCadastroExistente(Long codigoCliente, Long codigoTime) throws Exception {
		MensagemRetorno response = new MensagemRetorno();
		response.setCodigo("0");
		response.setMensagem("Cadastro já foi efetuado");
		List<Campanha> campanhasAtivas = null;
		// verifica se o cliente possui campanha cadastrada
		List<ClienteCampanha> campanhas = buscaCampanhasCliente(codigoCliente);

		try {
			campanhasAtivas = buscaCampanhasPorTimeCliente(codigoTime);

			// Se nao possui, retorno uma lista de campanhas
			if (campanhas == null || campanhas.size() == 0) {
				return new ResponseEntity<List<Campanha>>(campanhasAtivas, HttpStatus.OK);
			} else {
				associarNovasCampanhas(campanhasAtivas, campanhas, codigoCliente);
			}
		} catch (Exception e) {
			// TODO:Implementar Log
			e.printStackTrace();
		}
		return new ResponseEntity<MensagemRetorno>(response, HttpStatus.OK);
	}

	private void associarNovasCampanhas(List<Campanha> campanhasAtivas, List<ClienteCampanha> campanhas, Long cliente)
			throws IntegracaoException {

		Set<Long> campanhasParaAssociar = new TreeSet<Long>();

		for (ClienteCampanha campanhaAssociada : campanhas) {
			for (Campanha campanhaNaoAssociada : campanhasAtivas) {
				if (campanhaAssociada.getClienteCampanha().getCodigoCampanha().equals(campanhaNaoAssociada.getCodigo())
						|| campanhasParaAssociar.contains(campanhaAssociada.getClienteCampanha().getCodigoCampanha())) {
					continue;
				} else {
					campanhasParaAssociar.add(campanhaNaoAssociada.getCodigo());
				}
			}
		}
		campanhasAtivas = new ArrayList<>();
		for (Long codigo : campanhasParaAssociar) {
			campanhasAtivas.add(new Campanha(codigo, null, null, null, null));
		}
		this.associarClienteCampanha(campanhasAtivas, cliente);

	}

	private List<Campanha> buscaCampanhasPorTimeCliente(Long codigoTime) throws IntegracaoException {
		return campanhaService.buscaCampanhasPorTimeCliente(codigoTime).get(0).getCampanhas();

	}

	private List<ClienteCampanha> buscaCampanhasCliente(Long codigoCliente) throws Exception {
		return campanhaService.buscaCampanhaCliente(codigoCliente);
	}

	private void associarClienteCampanha(List<Campanha> campanhasAtivas, Long codigoCliente)
			throws IntegracaoException {
		List<Long> codigos = campanhasAtivas.stream().map(Campanha::getCodigo)
				                                    .collect(Collectors.toList());

		campanhaService.associarClienteCampanha(codigos, codigoCliente);
	}

	@Override
	public Cliente consultar(Long codigo) throws RegistroNaoEncontradoException {
		// TODO Auto-generated method stub
		Cliente cliente = clienteRepository.findOne(codigo);
		if (cliente == null) {
			throw new RegistroNaoEncontradoException();
		}

		return cliente;
	}

	@Override
	public void excluir(Long codigo) throws RegistroNaoEncontradoException {
		Cliente cliente = clienteRepository.findOne(codigo);

		if (cliente == null) {
			throw new RegistroNaoEncontradoException();
		}

		clienteRepository.delete(cliente);

	}

	@Override
	public Cliente alterar(Cliente cliente) {
		clienteRepository.saveAndFlush(cliente);
		return cliente;
	}

	@Override
	public void associarClienteCampanha(Long codigoCliente, Long codigoCampanha) throws IntegracaoException {

		List<Long> codigosCampanha = new ArrayList<>();
		codigosCampanha.add(codigoCampanha);

		this.campanhaService.associarClienteCampanha(codigosCampanha, codigoCliente);

	}

	private void validarParametros(Cliente cliente) throws ValidacaoException {
		if (cliente == null)
			throw new ValidacaoException("-1", "Cliente nao pode ser nulo");

		if (StringUtils.isEmpty(cliente.getNomeCompleto()))
			throw new ValidacaoException("-1", "O nome do cliente é obrigatório");

		if (StringUtils.isEmpty(cliente.getEmail()))
			throw new ValidacaoException("-1", "Email é obrigatório");

		if (StringUtils.isEmpty(cliente.getCodigoTime()))
			throw new ValidacaoException("-1", "O time do coração é obrigatório");

	}

	private Cliente buscarClientePorEmail(String email) {
		// Busca cliente cadastrado e ativo(data desativacao == null)
		return clienteRepository.findByEmailAndDataDesativacao(email, null);
	}

	@Override
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}

	public ClienteRepository getClienteRepository() {
		return clienteRepository;
	}

	public void setClienteRepository(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public CampanhaIntegracaoService getCampanhaService() {
		return campanhaService;
	}

	public void setCampanhaService(CampanhaIntegracaoService campanhaService) {
		this.campanhaService = campanhaService;
	}

}