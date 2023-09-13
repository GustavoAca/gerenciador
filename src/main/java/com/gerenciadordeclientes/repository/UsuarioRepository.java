package com.gerenciadordeclientes.repository;

import java.util.Optional;

import com.gerenciadordeclientes.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByUsuario(String usuario);

}
