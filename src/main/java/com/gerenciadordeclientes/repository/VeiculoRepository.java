package com.gerenciadordeclientes.repository;

import com.gerenciadordeclientes.domain.veiculo.Veiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
	Page<Veiculo> findAllByNomeContainingIgnoreCase(String nome, Pageable pageable);

	@Query(value = "select * from veiculos where vencimento_boleto = ?1", nativeQuery = true)
	Page<Veiculo> findVeiculoByVencimento(@Param("vencimento_boleto") String vencimento, Pageable pageable);
}
