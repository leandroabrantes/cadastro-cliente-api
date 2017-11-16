package com.cadastro.cliente.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler({ RegistroNaoEncontradoException.class})
	public ResponseEntity<MensagemRetorno> tratarRegistroNaoEncontrado(RegistroNaoEncontradoException ex) {

		MensagemRetorno mensagem = new MensagemRetorno();
		mensagem.setCodigo(ex.getCodigoRetorno());
		mensagem.setMensagem(ex.getMensagem());

		return new ResponseEntity<MensagemRetorno>(mensagem, HttpStatus.OK);

	}

	@ExceptionHandler({ IntegracaoException.class})
	public ResponseEntity<MensagemRetorno> tratarErroIntegracao(IntegracaoException ex) {

		MensagemRetorno mensagem = new MensagemRetorno();
		mensagem.setCodigo("-1");
		mensagem.setMensagem("Falha na integração entre Cliente/Campanha.Entrar em contato com o Administrador do sistema.");

		return new ResponseEntity<MensagemRetorno>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MensagemRetorno> tratarErroNaoPrevisto(Exception ex) {

		MensagemRetorno mensagem = new MensagemRetorno();
		mensagem.setCodigo("-1");
		mensagem.setMensagem("Ocorreu um erro interno. Entre em contato com o Administrador.");

		return new ResponseEntity<MensagemRetorno>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}