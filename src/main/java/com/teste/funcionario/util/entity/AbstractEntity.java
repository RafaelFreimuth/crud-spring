package com.teste.funcionario.util.entity;

public interface AbstractEntity {

	abstract Long getIdEntity();
	
	default String getIdentificacaoEntidade() {
		return this.getClass().getCanonicalName();
	}
}
