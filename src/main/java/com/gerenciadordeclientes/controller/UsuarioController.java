package com.gerenciadordeclientes.controller;

import com.gerenciadordeclientes.domain.Usuario;
import com.gerenciadordeclientes.domain.UsuarioLogin;
import com.gerenciadordeclientes.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	private final UsuarioService usuarioService;

	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping
	@Cacheable("usuarios")
	public Page<Usuario> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable){
		return usuarioService.findAll(pageable);
	}
	
	@PostMapping("/logar")
	public ResponseEntity<Optional<UsuarioLogin>> Autentication(@RequestBody UsuarioLogin user){
		return usuarioService.autenticar(user);
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody Usuario usuario) {
		return usuarioService.cadastraUsuario(usuario)
				.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {
		return usuarioService.atualizarUsuario(usuario)
				.map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@DeleteMapping("/{id_usuario}")
	public void delete(@PathVariable Long id_usuario) {
		usuarioService.deletar(id_usuario);
	}
}
