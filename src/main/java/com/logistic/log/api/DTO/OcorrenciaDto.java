package com.logistic.log.api.DTO;

import java.time.OffsetDateTime;


import lombok.Data;

// camada representante da model e para criar o subrecurso na url
@Data
public class OcorrenciaDto {
	
	private Long Id;
    private String descricao;
    private OffsetDateTime dataRegistro;
}
