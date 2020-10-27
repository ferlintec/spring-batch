package com.springbatch.demonstrativoorcamentario.writer;

import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;
import java.util.List;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.stereotype.Component;

import com.springbatch.demonstrativoorcamentario.dominio.GrupoLancamento;

@Component //Como ele está sendo usado no Bean do String, é necessário injetá-lo como componente.
public class DemonstrativoOrcamentarioRodape implements FlatFileFooterCallback {

	private Double totalGeral = 0.0d;
	
	@Override
	public void writeFooter(Writer writer) throws IOException {
		writer.append("\n");
		writer.append(String.format("\t\t\t\t\t\t\t  Total: %s", NumberFormat.getCurrencyInstance().format(totalGeral)));
		writer.append("\n");
		writer.append(String.format("\t\t\t\t\t\t\t  Código de Autenticação: %s", "fkyew6868fewjfhjjewf"));
		
	}
	
	/**
	 * Este evento será acionado sempre antes da escrita ocorrer, esse método sera chamado.
	 * Porém, é necessário registrar o listener no step.
	 * 
	 * @param grupos
	 */
	@BeforeWrite
	public void beforeWrite(List<GrupoLancamento> grupos) {
		
		for (GrupoLancamento grupoLancamento : grupos) {
			this.totalGeral += grupoLancamento.getTotal();
		}
	}
	
	
	/**
	 * Objetivo é que, para cada chunk, o total seja zerado. 
	 * Isso para que o total seja calculado para cada arqvuido, sem pegar 
	 * o total do arquivo anterior.
	 * ,
	 * @param context
	 */
	@AfterChunk
	public void afterChunk(ChunkContext context) {
		this.totalGeral = 0.0;
	}

}
