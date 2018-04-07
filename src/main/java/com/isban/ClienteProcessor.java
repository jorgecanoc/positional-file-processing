package com.isban;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.isban.sql.model.Cliente;

public class ClienteProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		String body = exchange.getIn().getBody(String.class);
		String[] clienteCsv = body.split(";");
		Cliente cliente = new Cliente();
		cliente.setNumeroDeCuenta(Integer.valueOf(clienteCsv[0]));
		cliente.setSucursal(Integer.valueOf(clienteCsv[1]));
		cliente.setNombres(clienteCsv[2]);
		cliente.setApellidoPaterno(clienteCsv[3]);
		cliente.setApellidoMaterno(clienteCsv[4]);
		cliente.setProductoTarjetaCredito(clienteCsv[5]);
		cliente.setProductoHipoteca(clienteCsv[6]);
		cliente.setEmail(clienteCsv[7]);
		cliente.setTelefono(clienteCsv[8]);
		exchange.getIn().setBody(cliente);
	}

}
