package com.ferlin.springbatch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class JobBatchConfig {
	
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
//	@Autowired
//	private StepBuilderFactory stepBuilderFactory;
	
	
	@Bean
	public Job imprimeOlaMundo(Step imprimeOlaMundoStep) {
	
		return jobBuilderFactory
				.get("imprimeOlaMundoJob")
				.start(imprimeOlaMundoStep)
				.incrementer(new RunIdIncrementer())
				.build();
	}

//	public Step imprimeOlaMundoStep() {
//		 return stepBuilderFactory.get("imprimeOlaMundoStep")
//				 			.tasklet(imprimeOlaTasklet(null, null))
//				 			.build();
//	}
//
//	@Bean
//	@StepScope
//	public Tasklet imprimeOlaTasklet(@Value("#{jobParameters['nome']}") String nome,
//			@Value("#{jobParameters['casa']}") String casa) {
//		return new Tasklet() {
//			
//			@Override
//			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//				System.out.println("############## INICIO step    ########################");
//				System.out.println(String.format("STEP Olá Mundo: %s!!", nome));
//				System.out.println(String.format("CASA: %s!!", casa));
//				System.out.println("############## FIM step    ########################");
//				return RepeatStatus.FINISHED;
//			}
//		};
//	}
}
