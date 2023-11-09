package com.gerenciadordeclientes.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gerenciadordeclientes.domain.user.User;
import com.gerenciadordeclientes.domain.veiculo.Veiculo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

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
	private User user;
}
