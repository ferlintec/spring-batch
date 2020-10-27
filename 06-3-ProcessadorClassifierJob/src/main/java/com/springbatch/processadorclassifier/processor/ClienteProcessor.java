package com.springbatch.processadorclassifier.processor;

import org.springframework.batch.item.ItemProcessor;

import com.springbatch.processadorclassifier.dominio.Cliente;

public class ClienteProcessor<O> implements ItemProcessor<Cliente, Cliente> {

	@Override
	public Cliente process(Cliente cliente) throws Exception {
		

		System.out.println(String.format("\nAplicando regras do cliente %s", cliente.getEmail()));
		System.out.println(cliente.toString());
		return null;
		
	}

}
