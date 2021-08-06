package com.teste.funcionario.database.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.teste.funcionario.util.entity.AbstractEntity;

@Entity
public class Funcionario implements AbstractEntity, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
	@SequenceGenerator(name = "id", sequenceName = "idFuncionario")
	private Long id;

	@Column(length = 30, nullable = false)
	private String nome;

	@Column(length = 50, nullable = false)
	private String sobrenome;

	@Column(length = 100, nullable = true)
	private String email;

	@Column(length = 11, nullable = false)
	private String nis;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNis() {
		return nis;
	}
	
	public String getNomeCompleto() {
		return getNome().concat(" ").concat(getSobrenome());
	}

	public void setNis(String nis) {
		this.nis = nis;
	}

	@Override
	public Long getIdEntity() {
		return getId();
	}

	@Override
	public String getIdentificacaoEntidade() {
		return "Funcionario";
	}

}
