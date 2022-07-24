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
	
	private EntregaRepository entregaRepository;
	
	// metodo que verifica se a entrega existe ou não, caso não retorna a mensagem no body
   @Transactional
	public Ocorrencia registrar(Long entregaId, String descricao) {
		Entrega entrega = entregaRepository.findById(entregaId)
				.orElseThrow(() -> new NegocioException("Entrega não encontrada"));
		
		return entrega.adicionarOcorrencia(descricao);//<-- caso a entrega existir retorna a descrição no body
		 
	}
   // busca o id da entrega se existir 
   public Entrega busca(Long entregaId) {
	   return entregaRepository.findById(entregaId)
			   .orElseThrow(()-> new ExceptionEntidadeNaoEncontrada("Entrega não encontrada"));
   }
}
