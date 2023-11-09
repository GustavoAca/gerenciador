package com.gerenciadordeclientes.controller;

import com.gerenciadordeclientes.domain.Cliente;
import com.gerenciadordeclientes.dto.cliente.ClienteDto;
import com.gerenciadordeclientes.exception.NaoEncontradoException;
import com.gerenciadordeclientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(value = "/clientes")
@CrossOrigin("*")
public class ClienteController {
    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    @Cacheable(value = "buscarClientes", key = "#pageable.pageNumber")
    public Page<ClienteDto> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        System.out.println("Buscando clientes");
        return clienteService.todos(pageable);
    }

    @GetMapping("/nome/{nome}")
    @Cacheable(value = "buscarPorNome", key = "#nome")
    public Page<ClienteDto> encontrarPorNome(@PathVariable String nome,
                                             @PageableDefault(page = 0, size = 10) Pageable pageable) {
        System.out.println("Passou aqui");
        return clienteService.encontrarPorNome(nome, pageable);
    }

    @GetMapping("/{id}")
    @Cacheable(value = "buscarClienteId", key = "#id")
    public ClienteDto buscaPorId(@PathVariable Long id) throws NaoEncontradoException {
        return clienteService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<ClienteDto> salvar(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.salvar(cliente));
    }

    @PutMapping("/{id}/atualizar")
    public ResponseEntity<ClienteDto> atualizar(@RequestBody Cliente cliente,
                                          @PathVariable Long id) throws NaoEncontradoException {
        return clienteService.atualizar(cliente, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        clienteService.deletar(id);
    }
}
