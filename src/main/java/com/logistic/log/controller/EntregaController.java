package com.logistic.log.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.logistic.log.api.DTO.EntregaDto;
import com.logistic.log.api.assembler.EntregaAssembler;
import com.logistic.log.model.Entrega;
import com.logistic.log.model.request.EntregaRequest;
import com.logistic.log.repository.EntregaRepository;
import com.logistic.log.service.FinalizacaoEntregaService;
import com.logistic.log.service.SolicEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EntregaController {
	
	private SolicEntregaService solicEntregaService;
	private EntregaRepository entregaRepository;
	private EntregaAssembler entregaAssembler; // <-- biblioteca que estancia uma classe dentro do spring (verificar as estrategis que o moldeMapper utiliza)  
	private FinalizacaoEntregaService finalizacaoEntregaService; 
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaDto solicitar( @Valid @RequestBody EntregaRequest entregaRequest) {
		Entrega novaEntrega = entregaAssembler.toEntity(entregaRequest);
		Entrega entregaSolicitada = solicEntregaService.solicitar(novaEntrega);
		
		
		return entregaAssembler.toModel(entregaSolicitada);
	}
	
	// crud que finaliza a entrega
	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long entregaId) {
		finalizacaoEntregaService.finalizar(entregaId);
		
	}
	
	@GetMapping
	public List<EntregaDto>listar(){
		return entregaAssembler.toCllectionModel(entregaRepository.findAll());
	}
	
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaDto> buscar (@PathVariable Long entregaId){
		return entregaRepository.findById(entregaId)
				.map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
				.orElse(ResponseEntity.notFound().build());
					
					
				}
			
	}
	


