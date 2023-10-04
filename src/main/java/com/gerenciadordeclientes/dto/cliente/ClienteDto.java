package com.gerenciadordeclientes.dto.cliente;

import com.gerenciadordeclientes.domain.Usuario;
import com.gerenciadordeclientes.domain.veiculo.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor()
@NoArgsConstructor
public class ClienteDto implements Serializable {

    private Long id;
    private String nome;
    private String contato;
    private List<Veiculo> veiculo;
    private Usuario usuario;

}
