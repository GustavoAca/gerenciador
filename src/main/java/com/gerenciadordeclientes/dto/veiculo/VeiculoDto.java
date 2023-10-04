package com.gerenciadordeclientes.dto.veiculo;

import com.gerenciadordeclientes.domain.veiculo.TiposVeiculos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoDto implements Serializable {

    private Long id;

    private String nome;

    private String placa;

    private Boolean uber;

    private String vencimentoBoleto;

    private Float mensalidade;

    private TiposVeiculos tiposVeiculos;

    private Long cliente;
}
