package com.logistic.log.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.logistic.log.api.DTO.EntregaDto;
import com.logistic.log.model.Entrega;
import com.logistic.log.model.request.EntregaRequest;

import lombok.AllArgsConstructor;


// class que faz o mapeamento dos objetos convertendo de um tipo para outro , implementado para não ficar dependente da biblioteca modelMapper
@AllArgsConstructor
@Component
public class EntregaAssembler {
	
	private ModelMapper modelMapper;
	
	public EntregaDto toModel(Entrega entrega) {
		return modelMapper.map(entrega, EntregaDto.class);
	}
	public List<EntregaDto> toCollectionModel(List<Entrega>entrega){
		return entrega.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
		
	}
	
	public Entrega toEntity(EntregaRequest entregaRequest) {
		return modelMapper.map(entregaRequest, Entrega.class);
	}

}
