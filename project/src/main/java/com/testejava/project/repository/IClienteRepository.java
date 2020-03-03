package com.testejava.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.testejava.project.model.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {

	@Query("FROM Cliente c WHERE LOWER(c.nome) like %:searchNome% OR c.cpf like %:searchCpf% ")
	Page<Cliente> search(@Param("searchNome") String searchNome, @Param("searchCpf") String searchCpf,
			Pageable pageable);
	
	Cliente findByCpf(String cpf);
}
