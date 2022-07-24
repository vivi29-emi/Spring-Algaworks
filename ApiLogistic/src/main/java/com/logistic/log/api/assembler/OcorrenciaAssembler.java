package com.logistic.log.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.logistic.log.api.DTO.OcorrenciaDto;
import com.logistic.log.model.Ocorrencia;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Component
public class OcorrenciaAssembler {
	
	private ModelMapper modelMapper;
	
	public OcorrenciaDto toModel(Ocorrencia ocorrencia) {
		
		return modelMapper.map(ocorrencia, OcorrenciaDto.class);
	}
	
	public List<OcorrenciaDto> toCollectionModel(List<Ocorrencia>ocorrencia){
		return ocorrencia.stream()
		       .map(this::toModel)
		       .collect(Collectors.toList());
		
	}

}
