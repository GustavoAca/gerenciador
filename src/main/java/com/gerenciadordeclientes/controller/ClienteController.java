package com.gerenciadordeclientes.controller;

import com.gerenciadordeclientes.dto.cliente.ClienteDto;
import com.gerenciadordeclientes.exception.NaoEncontradoException;
import com.gerenciadordeclientes.model.Cliente;
import com.gerenciadordeclientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @Cacheable("clientes")
    public Page<ClienteDto> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        System.out.println("Buscando clientes");
        return clienteService.todos(pageable);
    }

    @GetMapping("/nome/{nome}")
    @Cacheable("cliente")
    public Page<ClienteDto> encontrarPorNome(@PathVariable String nome,
                                             @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return clienteService.encontrarPorNome(nome, pageable);
    }

    @GetMapping("/{id}")
    @Cacheable("clienteId")
    public ClienteDto buscaPorId(@PathVariable Long id) throws NaoEncontradoException {
        return clienteService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<ClienteDto> post(@RequestBody Cliente cliente) {
        return clienteService.adicionar(cliente);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<ClienteDto> put(@RequestBody Cliente cliente) throws NaoEncontradoException {
        return clienteService.atualizar(cliente);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        clienteService.deletar(id);
    }
}
