package com.gerenciadordeclientes.domain.veiculo;

import lombok.Getter;

@Getter
public enum Vencimento {
    DEZ("10"),
    QUINZE("15"),
    VINTE("20");


    private final String key;

    Vencimento(String key) {
        this.key = key;
    }
}
