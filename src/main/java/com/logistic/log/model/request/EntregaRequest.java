package com.logistic.log.model.request;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EntregaRequest {

	@Valid
	@NotNull
	private ClienteIdRequest cliente;
	
	@Valid
	@NotNull
	private DestinatarioRequest destinatario;
	
	@NotNull
	private BigDecimal taxa;
	
	
}
