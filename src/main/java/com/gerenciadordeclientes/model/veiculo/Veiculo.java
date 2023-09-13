package com.gerenciadordeclientes.model.veiculo;

import com.bemprotege.backend.model.veiculo.TiposVeiculos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gerenciadordeclientes.model.Cliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "veiculos")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String placa;

	private Boolean uber;

	private String vencimentoBoleto;

	private Float mensalidade;

	@Enumerated(EnumType.STRING)
	private TiposVeiculos tiposVeiculos;
	
	@ManyToOne
	@JsonIgnoreProperties("veiculo")
	private Cliente cliente;
}
