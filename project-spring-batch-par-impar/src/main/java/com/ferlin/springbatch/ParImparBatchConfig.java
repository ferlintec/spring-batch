package com.ferlin.springbatch;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@EnableBatchProcessing
@Configuration
public class ParImparBatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	

	@Bean
	public Job imprimeOlaMundo() {
	
		return jobBuilderFactory
				.get("imprimeParImpar")
				.start(imprimeParImparStep())
				.incrementer(new RunIdIncrementer())
				.build();
	}

	public Step imprimeParImparStep() {
		 return stepBuilderFactory
				 	.get("imprimeParImparStep")
				 	.<Integer, String>chunk(3) //O parâmetro indica o número de operações para que o processo faça o commit e feche 1 transação.
				 								//Isso deve ser considerado para o desempenho, pois tem um custo para se criar cada nova transação
				 								//dentro do chunk.
				 	.reader(contaAteDezReader())
				 	.processor(parOuImparProcessor())
				 	.writer(imprimeWriter())
				 	.build();
	}
	

	public IteratorItemReader<Integer> contaAteDezReader() {
		List<Integer> numerosDeUmAteDez = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		return new IteratorItemReader<Integer> (numerosDeUmAteDez.iterator());
	}

	public FunctionItemProcessor<Integer, String> parOuImparProcessor() {
		return new FunctionItemProcessor<Integer, String> (item -> item % 2 == 0 ? String.format("Item %d é Par!", item): String.format("Item %d é Ìmpar!", item));
	}

	public ItemWriter<String> imprimeWriter() {
		
		return  itens -> itens.forEach(System.out::println);
	}



//	@Bean
//	@StepScope
//	public Tasklet imprimeParImparasklet(@Value("#{jobParameters['nome']}") String nome) {
//		return new Tasklet() {
//			
//			@Override
//			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//				System.out.println("############## INICIO step    ########################");
//				System.out.println(String.format("STEP Par Impar: %s!!", nome));
//				System.out.println("############## FIM step    ########################");
//				return RepeatStatus.FINISHED;
//			}
//		};
//	}
	
	
}
