package com.teste.funcionario.service;

import org.springframework.data.domain.Page;

import com.teste.funcionario.database.entidades.Funcionario;

public interface FuncionarioService {

	Page<Funcionario> findFuncionarios(int pagina, int size);

	Funcionario cadastrarFuncionario(Funcionario funcionario);
	
	void delete(Long id);

	Funcionario update(Long id, Funcionario entity);

	Funcionario findById(Long id);
}
