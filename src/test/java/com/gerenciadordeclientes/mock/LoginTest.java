package com.gerenciadordeclientes.mock;

import lombok.Getter;

@Getter
public enum LoginTest {

    G("G", "G@gmail.com"),
    GRIMAL("Grimal", "Grimal@gmail.com"),
    GRIND("Grind","grind@gmail.com");

    private final String nome;
    private final String email;

    LoginTest(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }
}
