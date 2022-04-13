package controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
@Named (value="TextoBean")
@RequestScoped
public class TextoBean implements Serializable {
	private static final long serialVersionUID = 7466849006351421144L;
	private String resultado;
	private String label;
	private String texto;
	public TextoBean() {
		this.texto="";
		this.resultado="";
		this.label="";
	}
	public void mostrarResultado() {
		String textoIngresado=getTexto();
//		List<String> listaResultado=new ArrayList<String>();
		int isNumber=0;
		StringBuilder resultadoFinal = new StringBuilder();
		// Se validará que se ingrese alguna cadena
		if(textoIngresado.isBlank()) {
			setResultado("Error, por favor ingrese un valor válido \n");
			return;
		}
			// Dividimos la cadena ingresada por cada enter(corto de línea) que tenga
			String[] lista=textoIngresado.split(System.getProperty("line.separator"));
			// Recorremos la lista y verificamos que el indice sea mayor a 0, para así
			// validar que tiene número o algún carácter atrás de \		
			for(String string:lista) {
				Boolean contiene=string.contains("\\");
				if(contiene) {
					if(string.indexOf("\\") > 0) {
						// Dividimos entre el \ para saber los números y letras
						String[] listaGeneral=string.split("\\\\");
						// Convirtiendo el string a Int
						try {
							isNumber=Integer.parseInt(listaGeneral[0]);
						}catch (NumberFormatException e) {
							resultadoFinal.append(string+" no contiene un número válido\n");
							continue;
						}
//						Verificamos que contenga solo letras, espacio y eliminamos
//						lo que no sea eso
						String verificar=contieneSoloLetras(listaGeneral[1]);
						String[] verificacion=verificar.split(" ");
						int numeroValidacion=verificacion.length;
						boolean resultado2=numeroValidacion==isNumber;
						resultadoFinal.append(String.format("%s\\%b\n", verificar, resultado2));
					}else {
						resultadoFinal.append("ERROR: La fila ingresada no contiene ningún dato antes del \\ !\n");
					}
			}else {
				resultadoFinal.append("ERROR: La fila ingresada no contiene \\ !\n");
			}
		}
		setResultado(resultadoFinal.toString());
		setLabel("Su resultado es: ");
	}
	private String contieneSoloLetras(String cadena) {
		String cadenaNueva=cadena.replaceAll("[^\\p{L}\\p{Z}]", "").toLowerCase();
	    return cadenaNueva;
	}
	public void limpiarTodo() {
		setLabel("");
		setResultado("");
		setTexto("");
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	
}
