package br.com.jway.util;

import java.util.ArrayList;
import java.util.List;

public class Util {

	public static void msgErro(String string, String string2) {
		// TODO Auto-generated method stub
		
	}

	public static List<Long> listaAnoLetivo() {
		List<Long> lista = new ArrayList<Long>();
		for (int i = 2017; i < 2050; i++) {
			lista.add(Long.valueOf(i));
		}
		return lista;
	}

}
