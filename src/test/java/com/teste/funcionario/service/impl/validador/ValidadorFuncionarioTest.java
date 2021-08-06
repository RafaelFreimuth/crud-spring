package com.teste.funcionario.service.impl.validador;

import java.text.MessageFormat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.teste.funcionario.database.entidades.Funcionario;
import com.teste.funcionario.util.generic.StringUtil;

@SpringBootTest
public class ValidadorFuncionarioTest {

	private ValidadorFuncionario validador;
	
	@BeforeEach
	private void setUp() {
		validador = new ValidadorFuncionario();
	}
	
	@Test
	@DisplayName("Deve lançar exceção caso campo nome não contenha caracteres minímos.")
	void deveLancarErroCasoNomeNaoContenhaTamanhoDeCaracteresMinimos() throws Exception {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("");
		
		Assertions.assertThatThrownBy(() -> validador.validarPrecisaoDeCampos(funcionario))
				  .isInstanceOf(RuntimeException.class)
				  .hasMessage("O campo nome precisa ter no minímo 2 caracteres.");
	}
	
	@Test
	@DisplayName("Deve lançar exceção caso campo nome ultrapasse quantidade maxima de caracteres.")
	void deveLancarExcecaoCasoOCampoNomeTenhaMaisCaracteresQueOLimite() throws Exception {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(StringUtil.gerarString("A", 31));
		
		Assertions.assertThatThrownBy(() -> validador.validarPrecisaoDeCampos(funcionario))
				  .isInstanceOf(RuntimeException.class)
				  .hasMessage("O campo nome precisa ter no maximo 30 caracteres.");
	}
	
	@Test
	@DisplayName("Deve lançar exceção caso campo sobrenome não contenha caracteres minímos.")
	void deveLancarExcecaoCasoCampoSobrenomeTenhaMenosCaracteresQueOLimite() throws Exception {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("RA");
		funcionario.setSobrenome("A");
		
		Assertions.assertThatThrownBy(() -> validador.validarPrecisaoDeCampos(funcionario))
				  .isInstanceOf(RuntimeException.class)
				  .hasMessage("O campo sobrenome precisa ter no minímo 2 caracteres.");
	}
	
	@Test
	@DisplayName("deve lançar exceção caso campo sobrenome ultrapasse quantidade maxima de caracteres.")
	void deveLancarExcecaoCasoCampoSobrenomeTenhaMaisCaracteresQueOLimite() throws Exception {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("RA");
		funcionario.setSobrenome(StringUtil.gerarString("A", 51));
		
		Assertions.assertThatThrownBy(() -> validador.validarPrecisaoDeCampos(funcionario))
				  .isInstanceOf(RuntimeException.class)
				  .hasMessage("O campo sobrenome precisa ter no máximo 50 caracteres.");
	}
	
	@Test
	@DisplayName("Deve lançar exceção caso campo NIS tenha quantidade diferente.")
	void deveLancarExcecaoCasoCampoNisNaoContenhaAhQuantidadeDeCaracteresPermitidas() throws Exception {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("RAFAEL");
		funcionario.setSobrenome("ALVES");
		funcionario.setNis("1579845678");
		
		Assertions.assertThatThrownBy(() -> validador.validarPrecisaoDeCampos(funcionario))
				  .isInstanceOf(RuntimeException.class)
				  .hasMessage("O campo NIS precisa ter 11 caracteres.");
	}
	
	@Test
	@DisplayName("Deve lançar exceção caso campo NIS não contenha apenas numeros.")
	void deveLancarExcecaoCasoCampoNisNaoContenhaSomenteNumeros() throws Exception {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("RAFAEL");
		funcionario.setSobrenome("ALVES");
		funcionario.setNis("1579845678A");
		
		Assertions.assertThatThrownBy(() -> validador.validarPrecisaoDeCampos(funcionario))
				  .isInstanceOf(RuntimeException.class)
				  .hasMessage("O campo NIS deve ser somente numeros.");
	}
	
	@Test
	@DisplayName("Deve lançar exceção caso campo email não seja valido")
	void deveLancarExcecaoCasoCampoEmailNaoSejaValido() throws Exception {
		Assertions.assertThatThrownBy(() -> validador.validarEmail("teste"))
				  .isInstanceOf(RuntimeException.class)
				  .hasMessage(MessageFormat.format("O Email ({0}) não é valido.", "teste"));
	}
	
}
