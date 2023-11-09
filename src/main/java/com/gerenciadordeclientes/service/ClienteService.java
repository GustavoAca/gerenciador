package com.gerenciadordeclientes.service;

import com.gerenciadordeclientes.domain.Cliente;
import com.gerenciadordeclientes.dto.cliente.ClienteDto;
import com.gerenciadordeclientes.dto.cliente.ClienteMapper;
import com.gerenciadordeclientes.exception.NaoEncontradoException;
import com.gerenciadordeclientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public ClienteDto buscarPorId(Long id) throws NaoEncontradoException {
        return clienteRepository.findById(id).map(clienteMapper::toDto)
                .orElseThrow(() -> new NaoEncontradoException("Cliente não encontrado"));
    }

    public Page<ClienteDto> encontrarPorNome(String nome, Pageable pageable) {
        return clienteRepository.findAllByNomeContainingIgnoreCase(nome, pageable)
                .map(clienteMapper::toDto);
    }

    public Page<ClienteDto> todos(Pageable pageable) {
        return clienteRepository.findAll(pageable).map(clienteMapper::toDto);
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    public ClienteDto salvar(Cliente cliente) {
        return clienteMapper.toDto(clienteRepository.save(cliente));
    }

    @CacheEvict(value = {"buscarPorNome", "buscarClienteId", "buscarClientes"}, allEntries = true)
    public ResponseEntity<ClienteDto> atualizar(Cliente cliente, Long id) {
        var clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            clienteRepository.save(cliente);
            return ResponseEntity.ok(clienteMapper.toDto(cliente));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
