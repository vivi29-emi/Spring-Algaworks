package com.logistic.log.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logistic.log.model.Cliente;
import com.logistic.log.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClienteService {
	
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Long clienteId) {
		
		return clienteRepository.findById(clienteId)
			  .orElseThrow(() -> new NegocioException("Cliente não encontrado"));//<- vai pegar o que está dentro do optional e será atribuido a variavel cliente e se tiver vasio será informado a mensagem no body.
	}
	
	
	/*a anotação abaixo declara que o metodo abaixo tem que ser feito dentro dessa transaçoes, 
	caso algo que estiver fazendo dentro da transação der errado todas as transaçoes que estiver
	dentro do DB será descartada.*/
	
	@Transactional 
	public Cliente salvar(Cliente cliente) { //<- para saber onde está salvando o dados do cliente
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail()) // método de validação se o e-mail já existe para cadastro
				.stream() // <- verificar sig
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
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
