package com.teste.funcionario.resource.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.funcionario.database.entidades.Funcionario;
import com.teste.funcionario.resource.FuncionarioResource;
import com.teste.funcionario.service.FuncionarioService;

@RestController
@RequestMapping(path = "/funcionario")
@Transactional(rollbackOn = Throwable.class)
@CrossOrigin
public class FuncionarioResourceImpl implements FuncionarioResource {

	@Autowired
	private FuncionarioService funcionarioService;
	
	@Override
	public Page<Funcionario> getPaged(int pagina, int size) {
		return funcionarioService.findFuncionarios(pagina, size);
	}
	
	@Override
	public Funcionario save(Funcionario entity) {
		return funcionarioService.cadastrarFuncionario(entity);
	}
	
	@Override
	public void delete(Long id) {
		funcionarioService.delete(id);
	}
	
	@Override
	public Funcionario update(Long id, Funcionario entity) {
		return funcionarioService.update(id, entity);
	}
	
	@Override
	public Funcionario findById(Long id) {
		return funcionarioService.findById(id);
	}

}
