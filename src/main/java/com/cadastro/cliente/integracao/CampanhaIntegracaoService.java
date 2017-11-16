package com.cadastro.cliente.integracao;

import java.io.Serializable;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cadastro.cliente.exceptions.IntegracaoException;
import com.cadastro.cliente.exceptions.MensagemRetorno;
import com.cadastro.cliente.model.ClienteCampanha;
import com.cadastro.cliente.model.Time;
import com.cadastro.cliente.response.ClienteCampanhaResponse;
import com.cadastro.cliente.response.TimeResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CampanhaIntegracaoService implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String URL_BASE = "http://localhost:8080/campanha/";
	private static final String URL_TIME_BASE = "http://localhost:8080/time/";

	public List<Time> buscaCampanhasPorTimeCliente(Long codigoTime) throws IntegracaoException {

		String uri = URL_TIME_BASE.concat("buscaPorCodigo?codigo=" + codigoTime);
		RestTemplate request = new RestTemplate();

		ResponseEntity<TimeResponse> response = request.getForEntity(uri, TimeResponse.class);

		if (!response.getStatusCode().is2xxSuccessful()) {
			throw new IntegracaoException();
		}

		return response.getBody().getTimes();
	}

	public List<ClienteCampanha> buscaCampanhaCliente(Long codigoCliente) throws IntegracaoException,Exception {
		String uri = URL_BASE.concat("campanhaCliente?codigoCliente=" + codigoCliente);
		RestTemplate request = new RestTemplate();

		String response = request.getForObject(uri, String.class);
		ObjectMapper mapper = new ObjectMapper();
		ClienteCampanhaResponse list = mapper.readValue(response, new TypeReference<ClienteCampanhaResponse>(){});
		
		return list.getCampanhas();
	}

	public void associarClienteCampanha(final List<Long> campanhas, Long codigoCliente)
			throws IntegracaoException {

		campanhas.forEach((c) -> {
			
			 String uri = URL_BASE.concat("associaClienteCampanha?codigoCliente="+codigoCliente +"&campanha=" +c.longValue());
			 RestTemplate request = new RestTemplate();
			 request.getForEntity(uri, MensagemRetorno.class) ;

		});
          
         
	}
}
