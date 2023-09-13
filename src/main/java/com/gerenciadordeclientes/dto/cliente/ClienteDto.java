package com.gerenciadordeclientes.dto.cliente;

import com.gerenciadordeclientes.model.Usuario;
import com.gerenciadordeclientes.model.veiculo.Veiculo;
import lombok.*;
import lombok.experimental.SuperBuilder;
;
import java.util.List;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor()
@NoArgsConstructor
public class ClienteDto {

    private Long id;
    private String nome;
    private String contato;
    private List<Veiculo> veiculo;
    private Usuario usuario;

}
