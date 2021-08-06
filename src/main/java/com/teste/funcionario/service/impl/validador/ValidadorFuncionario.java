package com.teste.funcionario.service.impl.validador;

import static java.text.MessageFormat.format;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.teste.funcionario.database.entidades.Funcionario;
import com.teste.funcionario.database.repository.FuncionarioRepository;
import com.teste.funcionario.util.generic.EmailUtil;
import com.teste.funcionario.util.generic.StringUtil;
import com.teste.funcionario.util.validador.AbstractValidator;

@Component
public class ValidadorFuncionario extends AbstractValidator<Funcionario, FuncionarioRepository>{

	@Override
	public void validateInsert(Funcionario funcionario) {
		validarPrecisaoDeCampos(funcionario);
		validarEmail(funcionario.getEmail());
	}

	@Override
	public void validateUpdate(Funcionario funcionario) {
		validarPrecisaoDeCampos(funcionario);
		validarEmail(funcionario.getEmail());
		validarNaoPossuiMaisDeUmCadastroParaOMesmoNis(funcionario);
	}

	private void validarNaoPossuiMaisDeUmCadastroParaOMesmoNis(Funcionario funcionario) {
		Funcionario funcionarioConflito = getRepository().findByNisAndNotEqualsId(funcionario.getNis(), Optional.of(funcionario.getId()));
		
		if (funcionarioConflito != null) {
			throw new RuntimeException(format("Já existe o funcionario {0} com o NIS {1}", funcionarioConflito.getNomeCompleto(), 
																						   funcionarioConflito.getNis()));
		}
	}

	protected void validarPrecisaoDeCampos(Funcionario funcionario) {
		if (StringUtil.isNullOrEmpty(funcionario.getNome()) || funcionario.getNome().trim().length() < 2) {
			throw new RuntimeException("O campo nome precisa ter no minímo 2 caracteres.");
		}
		
		if (funcionario.getNome().trim().length() > 30) {
			throw new RuntimeException("O campo nome precisa ter no maximo 30 caracteres.");
		}
		
		if (StringUtil.isNullOrEmpty(funcionario.getSobrenome()) || funcionario.getSobrenome().trim().length() < 2) {
			throw new RuntimeException("O campo sobrenome precisa ter no minímo 2 caracteres.");
		}
		
		if (funcionario.getSobrenome().trim().length() > 50) {
			throw new RuntimeException("O campo sobrenome precisa ter no máximo 50 caracteres.");
		}
		
		if (StringUtil.isNullOrEmpty(funcionario.getNis()) || funcionario.getNis().trim().length() != 11) {
			throw new RuntimeException("O campo NIS precisa ter 11 caracteres.");
		} else {
			try {				
				Long.valueOf(funcionario.getNis());
			} catch (Exception e) {
				throw new RuntimeException("O campo NIS deve ser somente numeros.");
			}
		}
		
	}

	protected void validarEmail(String email) {
		if (StringUtil.isNotNullOrEmpty(email)) {			
			EmailUtil.validarEmail(email);
		}
	}

	
}
