package com.gerenciadordeclientes.service;

import java.util.List;
import java.util.Optional;

import com.gerenciadordeclientes.exception.NaoEncontradoException;
import com.gerenciadordeclientes.model.Usuario;
import com.gerenciadordeclientes.model.UsuarioLogin;
import com.gerenciadordeclientes.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService  {

	private final UsuarioRepository repository;

	@Autowired
	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}

	public Optional<Usuario> cadastraUsuario(Usuario usuario) {

		if (repository.findByUsuario(usuario.getUsuario()).isPresent()) {
			return Optional.empty();
		}

		return Optional.of(repository.save(usuario));
	}

	public Optional<Usuario> atualizarUsuario(Usuario usuario) {
		if (repository.findById(usuario.getId()).isPresent()) {


			return Optional.of(repository.save(usuario));
		}
		return Optional.empty();
	}

	public void deletar(Long id){
		repository.deleteById(id);
	}

	public Page<Usuario> findAll(Pageable pageable){
		return repository.findAll(pageable);
	}

	public Optional<Usuario> trazerPorUsuario(String us){
		return repository.findByUsuario(us);
	}

	public ResponseEntity<Optional<UsuarioLogin>> autenticar(UsuarioLogin usuarioLogin){
		var usuarioLoginOptional = Optional.of(usuarioLogin);
		Optional<Usuario> usuarioEncontrado = repository.findByUsuario(usuarioLoginOptional.get().getUsuario());
		return ResponseEntity.ok(usuarioLoginOptional);
	}

	public Usuario trazerPorId(Long id) throws NaoEncontradoException {
		return repository.findById(id).orElseThrow(() -> new NaoEncontradoException("Usuario não encontrado"));
	}
}
