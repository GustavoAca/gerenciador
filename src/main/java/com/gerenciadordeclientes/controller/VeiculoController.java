package com.gerenciadordeclientes.controller;

import com.gerenciadordeclientes.dto.veiculo.VeiculoDto;
import com.gerenciadordeclientes.model.veiculo.Veiculo;
import com.gerenciadordeclientes.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	@Cacheable("veiculos")
	public Page<VeiculoDto> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable){
		return veiculoService.getAll(pageable);
	}

	@GetMapping("/{id_veiculo}")
	@Cacheable("veiculoid")
	public VeiculoDto getById(@PathVariable Long id){
		return veiculoService.getById(id);
	}

	@GetMapping("/titulo/{nome}")
	@Cacheable("veiculoPorNome")
	public Page<VeiculoDto> GetByTitulo(@PathVariable String nome,
										@PageableDefault(page = 0, size = 10) Pageable pageable){
		return veiculoService.getByTitulo(nome, pageable);
	}

	@PostMapping
	public ResponseEntity<VeiculoDto> post(@RequestBody Veiculo veiculo){
		return veiculoService.salvar(veiculo);
	}

	@PutMapping
	public ResponseEntity<VeiculoDto> put (@RequestBody Veiculo veiculo){
		return veiculoService.alterar(veiculo);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		veiculoService.deletar(id);
	}

	@GetMapping("/vencimento/{dataVencimento}")
	@Cacheable("veiculosporvencimento")
	public Page<VeiculoDto> getVeiculosPorVencimento(@PathVariable String dataVencimento,
													 @PageableDefault(page = 0, size = 10) Pageable pageable){
		return veiculoService.getVeiculosPorVencimento(dataVencimento, pageable);
	}
}
