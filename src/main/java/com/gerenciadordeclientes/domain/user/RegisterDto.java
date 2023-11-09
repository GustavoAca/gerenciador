package com.gerenciadordeclientes.domain.user;

public record RegisterDto(String login, String password, Role role, String nome ) {
}
