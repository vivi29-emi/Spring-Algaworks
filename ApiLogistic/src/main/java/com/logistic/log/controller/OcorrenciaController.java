package com.logistic.log.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.logistic.log.api.DTO.OcorrenciaDto;
import com.logistic.log.api.assembler.OcorrenciaAssembler;
import com.logistic.log.model.Entrega;
import com.logistic.log.model.Ocorrencia;
import com.logistic.log.model.request.OcorrenciaRequest;
import com.logistic.log.service.BuscaEntregaService;
import com.logistic.log.service.RegistroOcorrencService;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {
	
	private BuscaEntregaService buscaEntregaService;
    private RegistroOcorrencService registroOcorrenciaService;
    private OcorrenciaAssembler ocorrenciaAssembler;
   
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaDto registrar (@PathVariable Long entregaId,
			@Valid @RequestBody OcorrenciaRequest ocorrenciaRequest) {
		Ocorrencia ocorrenciaRegistrada= registroOcorrenciaService.registrar(entregaId,ocorrenciaRequest.getDescricao());
		
		return ocorrenciaAssembler.toModel(ocorrenciaRegistrada);
		
	}
	
	@GetMapping
	public List<OcorrenciaDto>listar(@PathVariable Long entregaId){
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		
		return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
		
	}
}
