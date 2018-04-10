package com.isban;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SplitFile implements Processor {

    private static final Logger LOG = LoggerFactory.getLogger(SplitFile.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		String text = exchange.getIn().getBody(String.class);
		List<String> list = new ArrayList<String>();
		String[] splitText = text.split("\\r?\\n");
		for (int i = 1; i < splitText.length - 1; i++) {
			list.add(splitText[i]);
		}
		
		String headerText = splitText[0];
		String lastLineText = splitText[splitText.length - 1];
		String fileName = exchange.getIn().getHeader("FileName", String.class);
		
		LOG.info("Encabezado: " +headerText);
		LOG.info("Ultima Linea: " +lastLineText);
		LOG.info("Nombre del archivo: " +fileName);
		
		int lineNumber = 0;
		String errorFile = "";
		
		//Validacion del nombre
		String idAplicacionN = fileName.substring(0, 8);
		String numeroOperacionDiaria = fileName.substring(8, 17);
		String fechaDeOperacion = fileName.substring(17, 34);
		String punto = fileName.substring(34, 35);
		String extension = fileName.substring(35,38);
		
		if(!idAplicacionN.matches("^[a-zA-Z0-9]*$") && !idAplicacionN.equals("MAT00001")) {
			lineNumber++;
			errorFile = errorFile + "ERR" + "NOM" + String.format("%010d", lineNumber) + "    "+"ERR"+"NO"+"01"+"NOMBRE DE ARCHIVO NO VALIDO - " + "ID APLICACION"+"\n"; 
		}
		
		if(!numeroOperacionDiaria.matches("^[0-9]*$")) {
			lineNumber++;
			errorFile = errorFile + "ERR" + "NOM" + String.format("%010d", lineNumber) + "    "+"ERR"+"NO"+"02"+"NOMBRE DE ARCHIVO NO VALIDO - " + "NUMERO OPERACION"+"\n";
		}
		
		if(!fechaDeOperacion.matches("^[0-9]*$")) {
			lineNumber++;
			errorFile = errorFile + "ERR" + "NOM" + String.format("%010d", lineNumber) + "    "+"ERR"+"NO"+"03"+"NOMBRE DE ARCHIVO NO VALIDO - " + "FECHA OPERACION"+"\n";
		}
		
		if(!punto.matches("\\.")) {
			lineNumber++;
			errorFile = errorFile + "ERR" + "NOM" + String.format("%010d", lineNumber) + "    "+"ERR"+"NO"+"04"+"NOMBRE DE ARCHIVO NO VALIDO - " + "PUNTO"+"\n";
		}
		
		if(!extension.matches("^[a-zA-Z0-9]*$") && !extension.equals("OPE")) {
			lineNumber++;
			errorFile = errorFile + "ERR" + "NOM" + String.format("%010d", lineNumber) + "    "+"ERR"+"NO"+"05"+"NOMBRE DE ARCHIVO NO VALIDO - " + "EXTENSION"+"\n";
		}
		
		//Validacion del encabezado
		String claveE = headerText.substring(0, 3);
		String idAplicacionE = headerText.substring(3, 11);
		String secuencia = headerText.substring(11, 21);
		
		if(!claveE.matches("^[a-zA-Z0-9_]*$") && !claveE.equals("CAB")) {
			lineNumber++;
			errorFile = errorFile + "ERR" + "CAB" + String.format("%010d", lineNumber) + "    "+"ERR"+"EN"+"01"+"ENCABEZADO NO VALIDO - " + "CLAVE"+"\n"; 
		}
		
		if(!idAplicacionE.matches("^[a-zA-Z0-9]*$") && !idAplicacionE.equals("MAT00001")) {
			lineNumber++;
			errorFile = errorFile + "ERR" + "CAB" + String.format("%010d", lineNumber) + "    "+"ERR"+"EN"+"02"+"ENCABEZADO NO VALIDO - " + "ID APLICACION"+"\n"; 
		}
		
		if(!secuencia.matches("^[0-9]*$")) {
			lineNumber++;
			errorFile = errorFile + "ERR" + "CAB" + String.format("%010d", lineNumber) + "    "+"ERR"+"EN"+"03"+"ENCABEZADO NO VALIDO - " + "SECUENCIA"+"\n"; 
		}
		
		//Validacion de ultima linea
		String claveU = lastLineText.substring(0, 3);
		String idAplicacionU = lastLineText.substring(3, 11);
		String numeroLineasAplicacion = lastLineText.substring(11, 21);
		
		if(!claveU.matches("^[a-zA-Z0-9_]*$") && !claveU.equals("FIN")) {
			lineNumber++;
			errorFile = errorFile + "ERR" + "FIN" + String.format("%010d", lineNumber) + "    "+"ERR"+"FI"+"01"+"ENCABEZADO NO VALIDO - " + "CLAVE"+"\n"; 
		}
		
		if(!idAplicacionU.matches("^[a-zA-Z0-9]*$") && !idAplicacionU.equals("MAT00001")) {
			lineNumber++;
			errorFile = errorFile + "ERR" + "FIN" + String.format("%010d", lineNumber) + "    "+"ERR"+"FI"+"02"+"ENCABEZADO NO VALIDO - " + "ID APLICACION"+"\n"; 
		}
		
		if(!numeroLineasAplicacion.matches("^[0-9]*$") && !(Integer.valueOf(numeroLineasAplicacion) == list.size())) {
			lineNumber++;
			errorFile = errorFile + "ERR" + "FIN" + String.format("%010d", lineNumber) + "    "+"ERR"+"FI"+"03"+"ENCABEZADO NO VALIDO - " + "NUMERO DE LINEAS NO COINCIDE"+"\n"; 
		}
		if (lineNumber > 0) {
			String nombreArchivoError = "MAT00001" + numeroOperacionDiaria + fechaDeOperacion + ".ERR";
			String encabezadoArchivoError = "CAB" + "MAT00001" + numeroOperacionDiaria;
			String ultimaLineaArchivoError = "FIN" + "MAT00001" + String.format("%010d", lineNumber);
			String archivoError = encabezadoArchivoError + "\n" + errorFile + ultimaLineaArchivoError;
			exchange.getIn().setHeader("Validacion_Exitosa", "false");
			exchange.getIn().setHeader("Nombre_Archivo_Error", nombreArchivoError);
			exchange.getIn().setBody(archivoError);
		} else {
			String nombreArchivo = "MAT00001" + numeroOperacionDiaria + fechaDeOperacion + ".OUT";
			String encabezadoArchivo = "CAB" + "MAT00001" + numeroOperacionDiaria;
			String ultimaLineaArchivo = "FIN" + "MAT00001" + String.format("%010d", list.size());
			exchange.getIn().setHeader("Validacion_Exitosa", "true");
			exchange.getIn().setHeader("Nombre_Archivo", nombreArchivo);
			exchange.getIn().setHeader("Encabezado", encabezadoArchivo);
			exchange.getIn().setHeader("Ultima_Linea", ultimaLineaArchivo);
			exchange.getIn().setBody(list);
		}
	}

}
