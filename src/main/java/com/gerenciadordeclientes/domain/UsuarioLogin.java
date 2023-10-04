package com.gerenciadordeclientes.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UsuarioLogin {

		private Long id;

		@NotNull
		@Size(min=2, max=100)
		private String nome;

		@NotNull
		@Email(message = "O usuário deve ser um email valido ex:maria@email.com")
		private String usuario;

		@NotNull
		@Size(min=4, max=100)
		private String senha;

		private String foto;
		
		private String token;
}
