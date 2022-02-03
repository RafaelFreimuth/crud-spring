package com.teste.funcionario.service.impl.validador;

import static com.teste.funcionario.util.generic.StringUtil.isNotNullOrEmpty;
import static com.teste.funcionario.util.generic.StringUtil.isNullOrEmpty;
import static java.text.MessageFormat.format;

import org.springframework.stereotype.Component;

import com.teste.funcionario.database.entidades.Funcionario;
import com.teste.funcionario.database.repository.FuncionarioRepository;
import com.teste.funcionario.util.validador.AbstractValidator;

@Component
public class ValidadorFuncionario extends AbstractValidator<Funcionario, FuncionarioRepository>{

	@Override
	public void validateInsert(Funcionario funcionario) {
		validarPrecisaoDeCampos(funcionario);
		validarEmail(funcionario.getEmail());
		validarNaoPossuiMaisDeUmCadastroParaOMesmoNis(funcionario);
	}

	@Override
	public void validateUpdate(Funcionario funcionario) {
		validarPrecisaoDeCampos(funcionario);
		validarEmail(funcionario.getEmail());
		validarNaoPossuiMaisDeUmCadastroParaOMesmoNis(funcionario);
	}

	private void validarNaoPossuiMaisDeUmCadastroParaOMesmoNis(Funcionario funcionario) {
		Funcionario funcionarioConflito = getRepository().findByNisAndNotEqualsId(funcionario.getNis(), funcionario.getId());
		
		if (funcionarioConflito != null) {
			throw new RuntimeException(format("Já existe o funcionario {0} com o NIS {1}", 
											  funcionarioConflito.getNomeCompleto(), 
											  funcionarioConflito.getNis()));
		}
	}

	protected void validarPrecisaoDeCampos(Funcionario funcionario) {
		String nomeFuncionario = funcionario.getNome();
		String sobrenome = funcionario.getSobrenome();
		String nis = funcionario.getNis();
		
		if (isNullOrEmpty(funcionario.getNome()) || nomeFuncionario.trim().length() < 2) {
			throw new RuntimeException("O campo nome precisa ter no minímo 2 caracteres.");
		}
		
		if (nomeFuncionario.length() > 30) {
			throw new RuntimeException("O campo nome precisa ter no maximo 30 caracteres.");
		}
		
		if (isNullOrEmpty(funcionario.getSobrenome()) || sobrenome.trim().length() < 2) {
			throw new RuntimeException("O campo sobrenome precisa ter no minímo 2 caracteres.");
		}
		
		if (sobrenome.length() > 50) {
			throw new RuntimeException("O campo sobrenome precisa ter no máximo 50 caracteres.");
		}
		
		if (isNullOrEmpty(funcionario.getNis()) || nis.trim().length() != 11) {
			throw new RuntimeException("O campo NIS precisa ter 11 caracteres.");
		} 
		else {
			try {				
				Long.valueOf(funcionario.getNis());
			} catch (Exception e) {
				throw new RuntimeException("O campo NIS deve conter somente numeros.");
			}
		}
		
	}

	protected void validarEmail(String email) {
		if (isNotNullOrEmpty(email)) {			
			validarEmail(email);
		}
	}

	
}
