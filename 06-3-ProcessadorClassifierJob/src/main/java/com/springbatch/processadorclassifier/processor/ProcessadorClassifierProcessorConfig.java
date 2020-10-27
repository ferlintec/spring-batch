package com.springbatch.processadorclassifier.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemProcessorBuilder;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.processadorclassifier.dominio.Cliente;


/**
 * Permite aplicar regras de negócio específicas para cada tipo de linha.
 * Neste casa, as linhas são classificadas entre clientes e transações.
 * Detecta o tipo, e então aplica o classifier.
 * 
 * @author adferlin
 *
 */
@Configuration
public class ProcessadorClassifierProcessorConfig {
	@SuppressWarnings("rawtypes")
	@Bean
	public ItemProcessor processadorClassifierProcessor() {

		return new ClassifierCompositeItemProcessorBuilder<>()
				.classifier(classifier())
				.build();
		
	}

	private Classifier classifier() {

		return new Classifier<Object, ItemProcessor>() {

			@Override
			public ItemProcessor classify(Object objeto) {

				if (objeto instanceof Cliente)
					return new ClienteProcessor();
				else
					return new TransacaoProcessor();
			}
			
		};
		
	}
}
