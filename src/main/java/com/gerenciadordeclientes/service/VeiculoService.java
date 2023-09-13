package com.gerenciadordeclientes.service;

import com.gerenciadordeclientes.dto.veiculo.VeiculoDto;
import com.gerenciadordeclientes.dto.veiculo.VeiculoMapper;
import com.gerenciadordeclientes.model.veiculo.Veiculo;
import com.gerenciadordeclientes.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final VeiculoMapper veiculoMapper;

    @Autowired
    public VeiculoService(VeiculoRepository repository,
                          VeiculoMapper veiculoMapper) {
        this.veiculoRepository = repository;
        this.veiculoMapper = veiculoMapper;
    }

    public Page<VeiculoDto> getAll(Pageable pageable){
        return veiculoRepository.findAll(pageable).map(veiculoMapper::toDto);
    }

    public VeiculoDto getById(Long id){
        return veiculoRepository.findById(id).map(veiculoMapper::toDto).orElseThrow();
    }

    public Page<VeiculoDto> getByTitulo(String nome, Pageable pageable){
        return veiculoRepository.findAllByNomeContainingIgnoreCase(nome, pageable).map(veiculoMapper::toDto);
    }

    public ResponseEntity<VeiculoDto> salvar(Veiculo veiculoModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoMapper.toDto(veiculoRepository.save(veiculoModel)));
    }

    public ResponseEntity<VeiculoDto> alterar(Veiculo veiculoModel){
        return veiculoRepository.findById(veiculoModel.getId())
                .map(resp -> ResponseEntity.ok().body(veiculoMapper.toDto(veiculoRepository.save(veiculoModel))))
                .orElse(ResponseEntity.notFound().build());
    }

    public void deletar(Long id){
        veiculoRepository.deleteById(id);
    }

    public ResponseEntity<Page<VeiculoDto>> getVeiculosPorVencimento(String vencimento, Pageable pageable){
        return ResponseEntity.ok(veiculoRepository.findVeiculoByVencimento(vencimento, pageable).map(veiculoMapper::toDto));
    }

}
