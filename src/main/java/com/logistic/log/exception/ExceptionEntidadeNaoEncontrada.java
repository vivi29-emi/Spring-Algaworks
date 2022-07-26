package com.logistic.log.exception;

import com.logistic.log.service.NegocioException;

public class ExceptionEntidadeNaoEncontrada extends NegocioException {

	
	private static final long serialVersionUID = 1L;

	public ExceptionEntidadeNaoEncontrada(String messagem) {
		super(messagem);
	
	}
	

}
