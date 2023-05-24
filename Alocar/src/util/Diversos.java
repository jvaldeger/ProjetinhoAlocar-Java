package util;

import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class Diversos {

	public static NumberFormat doisDigitos(int tipo) {
		NumberFormat doisDigitos = null;
		switch(tipo){
		case 0 : doisDigitos = NumberFormat.getNumberInstance();
		break;
		
		case 1 : doisDigitos = NumberFormat.getCurrencyInstance();
		break;
		
		case 2 : doisDigitos = NumberFormat.getPercentInstance();
		}
		
		doisDigitos.setMinimumFractionDigits(2);
		doisDigitos.setMaximumFractionDigits(2);
		return doisDigitos;

	}

	public static String converterValor(String numero) {
		numero = numero.replace(".", "");
		numero = numero.replace(",", ".");
		numero = numero.replace("R$", "");
		numero = numero.replace("%", "");
		return numero;
	}

	public static boolean confirmar(String mensagem, String titulo) {
		return (JOptionPane.showConfirmDialog(null, mensagem, titulo,
				JOptionPane.YES_NO_OPTION) == 0);
	}

	public static void mostrarDados(String reposta, String titulo, boolean icone) {
		

		int op = icone ? JOptionPane.INFORMATION_MESSAGE
				: JOptionPane.ERROR_MESSAGE;

		JOptionPane.showMessageDialog(null, reposta, titulo, op);
	}

	public static boolean testaNum(String dado, String titulo) {
		boolean teste = true;

		try {
			Double.parseDouble(dado);
		} catch (NumberFormatException erro) {
			mostrarDados("Houve erro na conversão\n"
					+ "Digite apenas caracteres numéricos " + erro.getMessage(),
					titulo, false);
			teste = false;

		}
		return (teste);
	}

	public static boolean intervalo(double n, double n1, double n2,
			String titulo) {
		if (n < 0) {
			mostrarDados("Favor digitar valores superiores a zero", titulo,false);
			return (false);
		} else if (n1 != n2 && (n < n1 || n > n2)) {
			mostrarDados("Valores fora do intervalo de " + n1 + "e" + n2, titulo, false);
			return (false);
		} else
			return (true);

	}

	public static DefaultFormatterFactory FormatoMascara(String titulo, int op) {
		
		MaskFormatter mascara = null;
		try {
			switch (op) {
			case 0:
				mascara = new MaskFormatter("##/##/####"); 
				break;
			case 1:
				mascara = new MaskFormatter("##:##"); 
				break;
			case 2:
				mascara = new MaskFormatter("(0##)-###-###"); 

			}
		} catch (ParseException e) {
			mostrarDados("Erro no dado formatado" + e.getMessage(), titulo,
					false);

		}
		DefaultFormatterFactory formato = new DefaultFormatterFactory(mascara, mascara);
		return (formato);
	}

}
