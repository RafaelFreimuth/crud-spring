package com.teste.funcionario.util.generic;

public class StringUtil {

	public static boolean isNotNullOrEmpty(String string) {
		return string != null && !string.trim().isEmpty();
	}
	
	public static boolean isNullOrEmpty(String string) {
		return !isNotNullOrEmpty(string);
	}
	
	public static String gerarString(String textoAhRepetir, int qtdRepeticoes) {
		String texto = new String();
		
		for (int i = 0; i <= qtdRepeticoes; i++) {
			texto = texto.concat(textoAhRepetir);
		}
		
		return texto;
	}
}
