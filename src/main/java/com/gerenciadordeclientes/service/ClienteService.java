package com.gerenciadordeclientes.service;

import com.gerenciadordeclientes.domain.Cliente;
import com.gerenciadordeclientes.dto.cliente.ClienteDto;
import com.gerenciadordeclientes.dto.cliente.ClienteMapper;
import com.gerenciadordeclientes.exception.NaoEncontradoException;
import com.gerenciadordeclientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

    public Page<ClienteDto> encontrarPorNome(String nome, Pageable pageable){
     return clienteRepository.findAllByNomeContainingIgnoreCase(nome, pageable)
             .map(clienteMapper::toDto);
    }

    public Page<ClienteDto> todos(Pageable pageable){
        return clienteRepository.findAll(pageable).map(clienteMapper::toDto);
    }

    public void deletar(Long id){
        clienteRepository.deleteById(id);
    }

    public ResponseEntity<ClienteDto> adicionar(Cliente cliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteMapper.toDto(clienteRepository.save(cliente)));
    }

    public ResponseEntity<ClienteDto> atualizar(Cliente cliente) throws NaoEncontradoException {
        try{
            return ResponseEntity.ok(clienteMapper.toDto(cliente));
        }catch (Exception e){
            throw new NaoEncontradoException("Cliente não encontrado");
        }
    }
}
