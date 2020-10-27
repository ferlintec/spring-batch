package com.springbatch.arquivodelimitado.writer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.springbatch.arquivodelimitado.dominio.Cliente;

@Configuration
public class ArquivoDelimitadoWriterConfig {
	
	
	//columns(new Range[]{new Range(1,10), new Range(11, 20), new Range(21, 23), new Range(24, 43)})
	@StepScope
	@Bean
	public FlatFileItemWriter<Cliente> escritaArquivoDelimitadoWriter(@Value("#{jobParameters['arquivoClientesSaida']}")  Resource arquivoClientesSaida) {
		return new FlatFileItemWriterBuilder<Cliente>()
				.name("escritaArquivoDelimitadoWriter")
				.resource(arquivoClientesSaida)
				.delimited()
				.delimiter("|")
				.names("nome","sobrenome","idade","email")
				.build();
		
	}
}
