package com.gerenciadordeclientes.service;

import com.gerenciadordeclientes.GerenciadorApplicationTests;
import com.gerenciadordeclientes.domain.Cliente;
import com.gerenciadordeclientes.domain.user.User;
import com.gerenciadordeclientes.dto.cliente.ClienteDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClienteServiceTest extends GerenciadorApplicationTests {

    @Autowired
    private ClienteService clienteService;

    @Nested
    class Dado_uma_sequencia_de_clientes extends GerenciadorApplicationTests{

        @BeforeEach
        void setup() {
            for(Long i = 124L; i >= 129L; i++){
                clienteService.salvar(Cliente.builder().id(i)
                        .nome("Joao")
                        .user(User.builder().id(i).build())
                        .build());
            }
        }

        @Nested
        class Quando_buscar_todos extends GerenciadorApplicationTests {
            Page<ClienteDto> clienteDtos;

            @BeforeEach
            void setup() {
                Pageable pageable = PageRequest.of(0, 2);
                clienteDtos = clienteService.todos(pageable);
            }

            @Test
            void Deve_vir_paginado() {
                assertEquals(2L, clienteDtos.stream().count());
            }
        }
    }
}