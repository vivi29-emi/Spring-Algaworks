package com.logistic.log.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
 // Irá retornar no body a resposta do erro ou seja o campo que não foi colocado a requisiçao

@JsonInclude(Include.NON_NULL)// <- quando os atributos estiverem null não vai aparecer.
@Data
public class Problem {
	
	private Integer status;
	private OffsetDateTime dataHora; //<- Hora que requisição foi feita
	private String titulo;
	private List<Campo>Campos;
	
	@AllArgsConstructor
	@Data
	public static class Campo{
		
		private String nome; //<- irá mostrar no body o nome do campo que está com problema
		private String mensagem; //<- irá mostrar a mensagem do motivo
	}
	
	

}
