package com.logistic.log.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.logistic.log.exception.ExceptionEntidadeNaoEncontrada;
import com.logistic.log.model.Entrega;
import com.logistic.log.model.Ocorrencia;
import com.logistic.log.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrencService {
	
	private BuscaEntregaService buscaEntregaService;
	
	// metodo que verifica se a entrega existe ou não, caso não ,retorna a mensagem no body, caso sim , adiciona o registro
   @Transactional
	public Ocorrencia registrar(Long entregaId, String descricao) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
				
		
		return entrega.adicionarOcorrencia(descricao);//<-- caso a entrega existir retorna a descrição no body
		 
	}
   
}
