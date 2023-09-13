package com.gerenciadordeclientes.service;

import com.gerenciadordeclientes.dto.cliente.ClienteDto;
import com.gerenciadordeclientes.dto.cliente.ClienteMapper;
import com.gerenciadordeclientes.gerenciador.GerenciadorApplication;
import com.gerenciadordeclientes.model.Cliente;
import com.gerenciadordeclientes.model.Usuario;
import com.gerenciadordeclientes.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = GerenciadorApplication.class)
class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @Nested
    class Dado_uma_sequencia_de_clientes {

        @BeforeEach
        void setup() {
            clienteService.adicionar(Cliente.builder().id(124l)
                    .nome("Joao")
                    .usuario(Usuario.builder().id(1L).build())
                    .build());
            clienteService.adicionar(Cliente.builder().id(123l)
                    .nome("Joao")
                    .usuario(Usuario.builder().id(1L).build())
                    .build());
            clienteService.adicionar(Cliente.builder().id(121l)
                    .nome("Joao")
                    .usuario(Usuario.builder().id(1L).build())
                    .build());
            clienteService.adicionar(Cliente.builder().id(124l)
                    .nome("Joao")
                    .usuario(Usuario.builder().id(1L).build())
                    .build());
            clienteService.adicionar(Cliente.builder().id(125l)
                    .nome("Joao")
                    .usuario(Usuario.builder().id(1L).build())
                    .build());
        }

        @Nested
        class Quando_buscar_todos {
            Page<ClienteDto> clienteDtos;
            @BeforeEach
            void setup(){
                Pageable pageable = PageRequest.of(0,2);
              clienteDtos =  clienteService.todos(pageable).getBody();
            }

            @Test
            void Deve_vir_paginado(){
                assertEquals(2L, clienteDtos.getTotalElements());
            }
        }
    }
}