package com.logistic.log.api.DTO;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

// Uma classe DTO agrupa um conjunto de atributos de uma ou mais classes , sendo um modelo de representação da Model.

import com.logistic.log.model.StatusEntrega;

import lombok.Data;

@Data
public class EntregaDto {
	private Long id;
	private String nomeCliente;
	private DestinatarioDto destinatario;
	private BigDecimal taxa;
	private StatusEntrega status;
	private OffsetDateTime dataPedido;
	private OffsetDateTime dataFinalização;
	
	

}
