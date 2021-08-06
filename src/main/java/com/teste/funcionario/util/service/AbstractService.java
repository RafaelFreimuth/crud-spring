package com.teste.funcionario.util.service;

import static java.text.MessageFormat.format;

import java.lang.reflect.Field;
import java.util.Optional;

import javax.persistence.Id;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.funcionario.util.entity.AbstractEntity;
import com.teste.funcionario.util.validador.AbstractValidator;

public abstract class AbstractService<ENT extends AbstractEntity,
									  REPOSITORY extends JpaRepository<ENT, Long>,
									  VALIDA extends AbstractValidator<ENT, REPOSITORY>>{

	@Autowired
	protected REPOSITORY repository;
	
	@Autowired
	private VALIDA validate;
	
	private final Class<ENT> entityClass;
	
	private String[] camposAtualizaveis;
	
	protected AbstractService(Class<ENT> entityClass, String[] camposAtualizaveis) {
		this.entityClass = entityClass;
		this.camposAtualizaveis = camposAtualizaveis;
	}
	
	public ENT save(ENT entity) {
		
		validate.validateInsert(entity);
		
		ENT entityPersisted = repository.save(entity);
		
		return entityPersisted;
	}
	
	public ENT update(Long id, ENT entityAhAtualizar) {
		Optional<ENT> entityBDOpt = repository.findById(id);
		
		if (entityBDOpt.isEmpty()) {
			throwEntityNotFound(id, entityClass.getSimpleName());
		}
		
		validate.validateUpdate(entityAhAtualizar);
		
		ENT entityBD = entityBDOpt.get();
		
		atualizarCamposDoObjeto(entityBD, entityAhAtualizar);		
		
		return repository.save(entityBD);
	}

	private void atualizarCamposDoObjeto(ENT entityBD, ENT entityAtualizar) {
		for (String campo : camposAtualizaveis) {
			if (!isId(campo)) {
				try {
					Object newProperty = PropertyUtils.getProperty(entityAtualizar, campo);
					PropertyUtils.setProperty(entityBD, campo, newProperty);
				} 
				catch (Throwable e) {
					throw new RuntimeException(format("Erro ao copiar  o campo {0}", campo));
				}
			}
		}
	}

	private boolean isId(String object) {
		Field field = FieldUtils.getField(entityClass, object);
		return field != null ? field.isAnnotationPresent(Id.class) : false;
	}
	
	public void delete(Long id) {
		Optional<ENT> entityById = repository.findById(id);
		
		if (entityById.isEmpty()) {
			throwEntityNotFound(id, entityClass.getSimpleName());
		}
		
		repository.delete(entityById.get());
	}

	private void throwEntityNotFound(Long id, String entity) {
		throw new RuntimeException(format("Não foi encontrada a entidade {0} com o id {1}", entity, id));
	}

}
