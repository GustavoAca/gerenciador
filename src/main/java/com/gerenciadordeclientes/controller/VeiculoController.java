package com.gerenciadordeclientes.controller;

import com.gerenciadordeclientes.domain.veiculo.Veiculo;
import com.gerenciadordeclientes.domain.veiculo.Vencimento;
import com.gerenciadordeclientes.dto.veiculo.VeiculoDto;
import com.gerenciadordeclientes.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/veiculos")
@CrossOrigin("*")
public class VeiculoController {

	private final VeiculoService veiculoService;

	@Autowired
	public VeiculoController(VeiculoService veiculoService) {
		this.veiculoService = veiculoService;
	}

	@GetMapping
	@Cacheable(value = "buscarVeiculos", key = "#pageable.pageNumber")
	public Page<VeiculoDto> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable){
		return veiculoService.getAll(pageable);
	}

	@GetMapping("/{id}")
	@Cacheable(value = "buscarVeiculoId", key = "#id")
	public VeiculoDto getById(@PathVariable Long id){
		return veiculoService.getById(id);
	}

	@GetMapping("/titulo/{nome}")
	@Cacheable(value = "buscarVeiculoPorNome", key = "#nome")
	public Page<VeiculoDto> GetByTitulo(@PathVariable String nome,
										@PageableDefault(page = 0, size = 10) Pageable pageable){
		return veiculoService.getByTitulo(nome, pageable);
	}

	@PostMapping
	public ResponseEntity<VeiculoDto> post(@RequestBody Veiculo veiculo){
		return veiculoService.salvar(veiculo);
	}

	@PutMapping
	public ResponseEntity<VeiculoDto> atualizar(@RequestBody Veiculo veiculo){
		return veiculoService.alterar(veiculo);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		veiculoService.deletar(id);
	}

	@GetMapping("/vencimento/{dataVencimento}")
	@Cacheable(value = "buscarPorVencimento", key = "#pageable.pageNumber")
	public Page<VeiculoDto> getVeiculosPorVencimento(@PathVariable Vencimento dataVencimento,
													 @PageableDefault(page = 0, size = 10) Pageable pageable){
		return veiculoService.getVeiculosPorVencimento(dataVencimento, pageable);
	}
}
