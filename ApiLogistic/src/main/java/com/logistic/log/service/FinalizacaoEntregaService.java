package com.logistic.log.service;

import org.springframework.stereotype.Service;

import com.logistic.log.model.Entrega;
import com.logistic.log.model.StatusEntrega;
import com.logistic.log.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {
	
	private EntregaRepository entregaRepository;
	private RegistroOcorrencService registroOcorrencService; 
	
	public void finalizar (Long entregaId) {
		Entrega entrega = registroOcorrencService.busca(entregaId);
		
		if (!entrega.getStatus().equals(StatusEntrega.PENDENTE)) {
			throw new NegocioException("Entrega não pode ser finalizada");
		 
		}
		
		entrega.setStatus(StatusEntrega.FINALIZADA);
		
		entregaRepository.save(entrega);
		
		
	}
	

}
