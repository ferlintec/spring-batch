package com.ferlin.springbach.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Quando é necessário criar um Batch com conexão com vários bancos de dados,
 * pode ser utilizado um DataSource.
 * 
 * @author adferlin
 *
 */
@Configuration
public class DataSourceConfig {

	@Primary //Para identificar que este é prioritário.
	@ConfigurationProperties(prefix="spring.datasource")
	@Bean
	public DataSource springDataSource() {
		
		System.out.println("#####################");
		System.out.println("#### DataSourceConfig #################");
		
		
		return DataSourceBuilder.create().build();
	}
	

	@ConfigurationProperties(prefix="app.datasource")
	@Bean
	public DataSource appDataSource() {
		
		System.out.println("#####################");
		System.out.println("#### DataSourceConfig #################");
		
		return DataSourceBuilder.create().build();
	}
	
}
