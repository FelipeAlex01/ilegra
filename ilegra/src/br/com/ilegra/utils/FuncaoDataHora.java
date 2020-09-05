package br.com.ilegra.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FuncaoDataHora {
	
	private static SimpleDateFormat horaComoStringComMilis;
	private static SimpleDateFormat dataComoString;;

	public static String getDataHoje() {
		Date data = new Date();

		String strData = getDataComoString().format(data);
		
		return strData;
	}
	
	public static SimpleDateFormat getHoraComoStringComMilis() {
		SimpleDateFormat formatador = horaComoStringComMilis;

		if (formatador == null) {
			horaComoStringComMilis = formatador = new SimpleDateFormat("HH:mm:ss:SSS");
		}

		return formatador;
	}
	
	private static SimpleDateFormat getDataComoString() {
		SimpleDateFormat formatador = dataComoString;

		if (formatador == null) {
			dataComoString = formatador = new SimpleDateFormat("dd/MM/yyyy");
		}

		return formatador;
	}

}
