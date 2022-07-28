package com.logistic.log.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.logistic.log.model.Cliente;
import com.logistic.log.repository.ClienteRepository;
import com.logistic.log.service.ClienteService;

import lombok.AllArgsConstructor;



@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {
	
	
	private ClienteRepository clienteRepository;
	
	private ClienteService clienteService;
	
	@GetMapping
	public List<Cliente> listar(){
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar (@PathVariable Long clienteId){
		return clienteRepository.findById(clienteId)
				.map(ResponseEntity::ok) // <-- método reference
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) //<- outra maneira de retornar o status da aplicação em vez do ResponseEntity
	public Cliente adicionar (@Valid @RequestBody Cliente cliente) {
		return clienteService.salvar(cliente);
		
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> Atualizar (@PathVariable @Valid Long clienteId,@RequestBody  Cliente cliente){
		
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId); //<-- força atualização do cliente em vez de criar novamente.
		cliente = clienteService.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
		
	}
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover ( @PathVariable Long clienteId){
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		clienteService.excluir(clienteId);
		
		return ResponseEntity.noContent().build();
	}

}
