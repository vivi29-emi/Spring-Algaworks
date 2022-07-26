package com.logistic.log.model.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class OcorrenciaRequest {

	@NotBlank
	private String descricao;
}
