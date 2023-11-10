package com.gerenciadordeclientes.service;

import com.gerenciadordeclientes.GerenciadorApplicationTests;
import com.gerenciadordeclientes.domain.user.AuthenticationDto;
import com.gerenciadordeclientes.domain.user.RegisterDto;
import com.gerenciadordeclientes.domain.user.Role;
import com.gerenciadordeclientes.domain.user.User;
import com.gerenciadordeclientes.exception.NaoEncontradoException;
import com.gerenciadordeclientes.mock.MockFactory;
import org.checkerframework.checker.units.qual.N;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationServiceTest extends GerenciadorApplicationTests {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private MockFactory mockFactory;

    @Nested
    class Dado_um_usuario extends GerenciadorApplicationTests {
        RegisterDto registerDto;
        ResponseEntity responseEntity;

        @BeforeEach
        void setup() {
            registerDto = mockFactory.registrar("G@gmail.com");
        }

        @Nested
        class Quando_tentar_cadatrar extends GerenciadorApplicationTests {

            @BeforeEach
            void setup() {
                responseEntity = authenticationService.register(registerDto);
            }

            @Test
            void Deve_registrar() {
                assertNotNull(responseEntity);
                assertEquals(HttpStatusCode.valueOf(200), responseEntity.getStatusCode());
            }
        }

        @Nested
        class Quando_tentar_logar extends GerenciadorApplicationTests {
            @BeforeEach
            void setup() {
                authenticationService.register(mockFactory.registrar("Grimal@gmail.com"));
                AuthenticationDto authenticationDto = new AuthenticationDto("Grimal@gmail.com", "12345");
                responseEntity = authenticationService.login(authenticationDto);
            }

            @Test
            void Deve_ser_realizado_login() {
                assertNotNull(responseEntity);
                assertEquals(HttpStatusCode.valueOf(201), responseEntity.getStatusCode());
            }
        }

        @Nested
        class Quando_tentar_excluir extends GerenciadorApplicationTests {
            User user;

            @BeforeEach
            void setup() {
                authenticationService.register(mockFactory.registrar("grind@gmail.com"));
                user = authenticationService.findAll(PageRequest.of(0, 2)).stream().filter(u -> u.getLogin().equals("grind@gmail.com")).findFirst().orElseThrow();
                authenticationService.deletar(user.getId());
            }

            @Test
            void Deve_ser_realizado_login() {
                assertThrows(NaoEncontradoException.class, () -> authenticationService.trazerPorId(user.getId()));
            }
        }
    }
}