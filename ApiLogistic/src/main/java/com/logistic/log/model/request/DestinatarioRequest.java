package com.logistic.log.model.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class DestinatarioRequest {
	@NotBlank
    private String nome;
	@NotBlank
	private String logradouro;
	@NotBlank
	private String numero;
	@NotBlank
	private String complemento;
	@NotBlank
	private String bairro;

}
