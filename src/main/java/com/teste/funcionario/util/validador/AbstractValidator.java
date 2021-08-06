package com.teste.funcionario.util.validador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.funcionario.util.entity.AbstractEntity;

public abstract class AbstractValidator<ENT extends AbstractEntity, REPO extends JpaRepository<ENT, Long>> {

	@Autowired
	private REPO repository;
	
	public void validateInsert(ENT entity) {};
	
	public void validateUpdate(ENT entity) {};
	
	public void validateDelete(ENT entity) {};
	
	protected REPO getRepository() {
		return repository;
	}
}
