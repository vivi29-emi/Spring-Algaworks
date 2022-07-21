package com.logistic.log.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode (onlyExplicitlyIncluded= true) //<- equals e hachCode verif sig
@Data
@Entity 
public class Cliente {
	
	@Id
	@EqualsAndHashCode.Include 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max= 60)
	private String nome;
	
	
	@NotBlank
	@Email
	@Size(max= 255)
	private String email;
	
	@Size(max= 20)
	private String telefone;
	
	
	
}
