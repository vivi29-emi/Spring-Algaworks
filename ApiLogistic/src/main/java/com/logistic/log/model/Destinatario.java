package com.logistic.log.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Embeddable //<-- indicando que a classe pode ser embutida em qualquer entidade ou outra tabela
public class Destinatario {
	
	@NotBlank
	@Size(max= 60)
	@Column(name="destinatario_nome")//<-- indicar que o atributo pertence a tabela destinatario e não da classe entrega
	private String nome;
	
	@NotBlank
	@Size(max= 255)
	@Column(name="destinatario_logradouro")
	private String logradouro;
	
	@NotBlank
	@Size(max= 30)
	@Column(name="destinatario_numero")
	private String numero;
	
	@NotBlank
	@Size(max= 60)
	@Column(name="destinatario_complemento")
	private String complemento;
	
	@NotBlank
	@Size(max= 30)
	@Column(name="destinatario_bairro")
	private String bairro;

}
