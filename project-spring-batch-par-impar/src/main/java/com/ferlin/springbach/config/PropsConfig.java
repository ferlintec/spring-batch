package com.ferlin.springbach.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

/**
 * Classe de configuração para externalização do arquivo de properties.
 * Objetivo é garantir segurança, e não deixar as credenciais de acesso ao banco dentro
 * do código.
 * 
 * @author adferlin
 *
 */
@Configuration
public class PropsConfig {

	/**
	 * Com esta implementação, o SpringBoot vai buscar informações de configuração neste diretório também.
	 * 
	 * @return
	 */
	@Bean
	public PropertySourcesPlaceholderConfigurer config() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer ();
		
		FileSystemResource f = new FileSystemResource("C:/test/git/spring-batch/config/project-spring-batch-par-impar/application.properties");
		configurer.setLocation(f);
		
		return configurer;
	}
	
}
