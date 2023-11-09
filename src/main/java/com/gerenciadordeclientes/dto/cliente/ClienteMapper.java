package com.gerenciadordeclientes.dto.cliente;

import com.gerenciadordeclientes.domain.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteDto toDto(Cliente entity){
        return ClienteDto.builder().id(entity.getId())
                .nome(entity.getNome())
                .contato(entity.getContato())
                .veiculo(entity.getVeiculo())
                .user(entity.getUser())
                .build();
    }

    public ClienteDto toDtoReduzito(Cliente entity){
        return ClienteDto.builder().id(entity.getId())
                .nome(entity.getNome())
                .contato(entity.getContato())
                .build();
    }

    public Cliente toEntity(ClienteDto dto){
        return Cliente.builder().id(dto.getId())
                .nome(dto.getNome())
                .contato(dto.getContato())
                .veiculo(dto.getVeiculo())
                .user(dto.getUser())
                .build();
    }
}
