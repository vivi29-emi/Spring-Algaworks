package com.logistic.log.api.exceptionhandler;





import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.logistic.log.service.NegocioException;

import lombok.AllArgsConstructor;

// Tratador de exceções da controler de formar global

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler { //<- Classe que trata varias exceptions
	
	private MessageSource messageSource;

	protected ResponseEntity<Object> handlerMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
		List<Problem.Campo> campos = new ArrayList <> (); 
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) { //<-- vai pegar todos os erros e os resultador vai passar para o objeto "error"
			// recebe o erro passando para o nome sendo o tipo do erro  e a mensagem
			String nome = ((FieldError)error).getField(); // <-- pega o campo com o erro
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());//<- determina a linguagem 
			
			campos.add(new Problem.Campo(nome,mensagem));
		}
		
		Problem problem = new Problem();
		problem.setStatus(status.value());
		problem.setDataHora(LocalDateTime.now());
		problem.setTitulo("Um ou mais campos estiverem inválidos, faça o preenchimento correto e tente novamente.");
		problem.setCampos(campos);
		
		
		
		return handleExceptionInternal(ex , problem, headers, status, request);
		
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object>handlerNegocio(NegocioException ex, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Problem problem = new Problem();
		problem.setStatus(status.value());
		problem.setDataHora(LocalDateTime.now());
		problem.setTitulo(ex.getMessage());
		
		
		return handleExceptionInternal(ex,problem,new HttpHeaders(),status,request);
		
	}
}
