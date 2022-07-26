package com.logistic.log.service;

import org.springframework.stereotype.Service;

import com.logistic.log.exception.ExceptionEntidadeNaoEncontrada;
import com.logistic.log.model.Entrega;
import com.logistic.log.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

	private EntregaRepository entregaRepository;
	
	// busca o id da entrega se existir 
	public Entrega buscar(Long entregaId) {
		
		return entregaRepository.findById(entregaId)
				.orElseThrow (() -> new ExceptionEntidadeNaoEncontrada ("Entrega não encontrada"));
	}
	
}
