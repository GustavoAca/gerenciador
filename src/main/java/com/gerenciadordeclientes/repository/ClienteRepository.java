package com.gerenciadordeclientes.repository;

import java.util.List;

import com.gerenciadordeclientes.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>  {
	Page<Cliente> findAllByNomeContainingIgnoreCase(String nome, Pageable pageable);

}
