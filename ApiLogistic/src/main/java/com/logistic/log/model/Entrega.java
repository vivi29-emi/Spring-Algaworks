package com.logistic.log.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.EqualsAndHashCode;


// Class que modela o que vai retornar da Api
@Data
@EqualsAndHashCode (onlyExplicitlyIncluded= true) 
@Entity
public class Entrega {
	
	@Id
	@EqualsAndHashCode.Include 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	@Embedded//<-- serve para abstrair os dados da tabela destinatário para dentro da tabela entrega
	private Destinatario destinatario;
	
	@NotNull
	private BigDecimal taxa;
	
	
	@JsonProperty(access = Access.READ_ONLY) /*<- omiti para o usuario determinado dados ou também para que ele não preencha o campo. 
	                                               Com o Read ONly o campo aparece somente para leitura.*/
	
	@Enumerated(EnumType.STRING)//<-- serve para armazenar na coluna status a string  (pendente ,finalizada  e cancelada)
	private StatusEntrega status;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataPedido; /*<- OffsetDateTime  é uma representação imutável de uma data e hora com um deslocamento.
	Essa classe armazena todos os campos de data e hora, com precisão de nanossegundos, bem como o deslocamento de UTC/Greenwich.*/
	
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataFinalização; 
	
	@NotNull
	@ManyToOne
	private Cliente cliente;
	
	@OneToMany(mappedBy="entrega", cascade = CascadeType.ALL)
	private List<Ocorrencia> ocorrencias = new ArrayList<>();// devolve a lista de ocorrencias
	
	
	// metodo que cria as ocorrencia

	public Ocorrencia adicionarOcorrencia(String descricao) {
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setDescricao(descricao);
		ocorrencia.setDataRegistro(OffsetDateTime.now());
		ocorrencia.setEntrega(this);
		
		this.getOcorrencias().add(ocorrencia);
		
		return ocorrencia;
		
	}
	

}
