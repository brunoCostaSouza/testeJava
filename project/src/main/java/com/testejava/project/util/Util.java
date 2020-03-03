package com.testejava.project.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.caelum.stella.validation.CPFValidator;

public class Util {

	public static Date convertData(String dataString) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		try {
			c.setTimeInMillis(format.parse(dataString).getTime());
		} catch (Exception e) {
			return null;
		}
		
		return c.getTime();
	}
	
	public static String retirarMascaraCpf(String cpf) {
		return cpf.replace(".", "").replace("-", "").replace("/", "").trim();
	}
	
	public static boolean cpfValido(String cpf) {
		CPFValidator validador = new CPFValidator();
		try {
			validador.assertValid(cpf);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
