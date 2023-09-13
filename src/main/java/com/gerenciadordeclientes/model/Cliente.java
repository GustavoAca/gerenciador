package com.gerenciadordeclientes.model;

import java.io.Serializable;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gerenciadordeclientes.model.veiculo.Veiculo;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(name="clientes")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String contato;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="cliente", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("cliente")
	private List<Veiculo> veiculo;
	
	@ManyToOne
	@JsonIgnoreProperties("cliente")
	private Usuario usuario;
}
