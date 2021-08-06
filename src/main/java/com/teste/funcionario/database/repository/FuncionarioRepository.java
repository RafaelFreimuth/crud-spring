package com.teste.funcionario.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.teste.funcionario.database.entidades.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	Funcionario findByNomeAndSobrenome(String nome, String sobrenome);

	@Query(value = "select funcionario from Funcionario funcionario where funcionario.nis = ?1 and funcionario.id <> ?2")
	Funcionario findByNisAndNotEqualsId(String nis, Optional<Long> id);
}
