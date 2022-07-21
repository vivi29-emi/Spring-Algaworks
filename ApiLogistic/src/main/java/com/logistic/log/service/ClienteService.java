package com.logistic.log.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logistic.log.model.Cliente;
import com.logistic.log.repository.ClienteRepository;

@Service
public class ClienteService {
	
	private ClienteRepository clienteRepository;
	
	@Transactional /*<- a anotação declara que o metodo abaixo tem que ser feito dentro dessa transaçoes, 
	caso algo que estiver fazendo dentro da transação der errado todas as transaçoes que estiver
	dentro do DB será descartada.*/
	public Cliente salvar(Cliente cliente) { //<- para saber onde está salvando o dados do cliente
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail()) // método de validação se o e-mail já existe para cadastro
				.stream() // <- verificar sig
				.anyMatch(clienteExitente -> !clienteExitente.equals(cliente));
		if(emailEmUso) {
			throw new NegocioException("Já existe um cliente cadastrado com este e-mail");
		}
				
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir (Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}

}
