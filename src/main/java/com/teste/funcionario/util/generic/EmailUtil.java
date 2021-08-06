package com.teste.funcionario.util.generic;

import java.text.MessageFormat;

import javax.mail.internet.InternetAddress;

public class EmailUtil {

	public static void validarEmail(String email) {
		try {
			InternetAddress internetAddress = new InternetAddress(email);
			internetAddress.validate();
		} 
		catch (Exception e) {
			throw new RuntimeException(MessageFormat.format("O Email ({0}) não é valido.", email));
		}
	}
}
