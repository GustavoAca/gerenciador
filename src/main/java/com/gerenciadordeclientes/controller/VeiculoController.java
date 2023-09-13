package com.gerenciadordeclientes.controller;

import java.util.List;

import com.gerenciadordeclientes.dto.veiculo.VeiculoDto;
import com.gerenciadordeclientes.model.veiculo.Veiculo;
import com.gerenciadordeclientes.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Page<VeiculoDto>> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		return veiculoService.getAll(pageable);
	}

	@GetMapping("/{id_veiculo}")
	@Cacheable("veiculoid")
	public ResponseEntity<VeiculoDto> getById(@PathVariable Long id){
		return veiculoService.getById(id);
	}

	@GetMapping("/titulo/{nome}")
	@Cacheable("veiculoPorNome")
	public ResponseEntity<Page<VeiculoDto>> GetByTitulo(@PathVariable String nome,
														@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
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
	public ResponseEntity<Page<VeiculoDto>> getVeiculosPorVencimento(@PathVariable String dataVencimento,
																	 @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		return veiculoService.getVeiculosPorVencimento(dataVencimento, pageable);
	}
}
