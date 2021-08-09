package com.teste.funcionario.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.teste.funcionario.database.entidades.Funcionario;
import com.teste.funcionario.database.repository.FuncionarioRepository;
import com.teste.funcionario.service.FuncionarioService;
import com.teste.funcionario.service.impl.validador.ValidadorFuncionario;
import com.teste.funcionario.util.service.AbstractService;

@Service
public class FuncionarioServiceImpl extends AbstractService<Funcionario, FuncionarioRepository, ValidadorFuncionario> implements FuncionarioService {
	

	protected FuncionarioServiceImpl() {
		super(Funcionario.class, new String[] {
				"nome",
				"sobrenome",
				"email",
				"nis"
		});
	}

	@Override
	public Page<Funcionario> findFuncionarios(int pagina, int size) {
		return repository.findAll(PageRequest.of(pagina, size));
	}

	@Override
	public Funcionario cadastrarFuncionario(Funcionario funcionario) {
		return save(funcionario);
	}
	
	@Override
	public void delete(Long id) {
		super.delete(id);
	}
	
	@Override
	public Funcionario update(Long id, Funcionario entity) {
		return super.update(id, entity);
	}

	@Override
	public Funcionario findById(Long id) {
		return repository.findById(id).orElse(null);
	}

}
