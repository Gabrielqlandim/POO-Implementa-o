package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.entidades.Transacao;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
 * Deve gravar em e ler de um arquivo texto chamado Transacao.txt os dados dos objetos do tipo
 * Transacao. Seguem abaixo exemplos de linhas 
 * De entidadeCredito: identificador, nome, autorizadoAcao, saldoAcao, saldoTituloDivida.
 * De entidadeDebito: identificador, nome, autorizadoAcao, saldoAcao, saldoTituloDivida.
 * De acao: identificador, nome, dataValidade, valorUnitario OU null
 * De tituloDivida: identificador, nome, dataValidade, taxaJuros OU null. 
 * valorOperacao, dataHoraOperacao
 * 
 *   002192;BCB;true;0.00;1890220034.0;001112;BOFA;true;12900000210.00;3564234127.0;1;PETROBRAS;2024-12-12;30.33;null;100000.0;2024-01-01 12:22:21 
 *   002192;BCB;false;0.00;1890220034.0;001112;BOFA;true;12900000210.00;3564234127.0;null;3;FRANCA;2027-11-11;2.5;100000.0;2024-01-01 12:22:21
 *
 * A inclus�o deve adicionar uma nova linha ao arquivo. 
 * 
 * A busca deve retornar um array de transa��es cuja entidadeCredito tenha identificador igual ao
 * recebido como par�metro.  
 */
public class RepositorioTransacao {
	Path arquivo = Paths.get("Transacao.txt");

	private RepositorioAcao repositorioAcao = new RepositorioAcao();
	private RepositorioTituloDivida repositorioTituloDivida = new RepositorioTituloDivida();
	private RepositorioEntidadeOperadora repositorioEntidadeOperadora = new RepositorioEntidadeOperadora();

	public boolean incluir(Transacao transacao) {
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo.toFile(), true))){
			writer.write(transacao.getEntidadeCredito().getIdentificador() + ";" + transacao.getEntidadeCredito().getNome() + ";" + transacao.getEntidadeCredito().getAutorizadoAcao() + ";" + transacao.getEntidadeCredito().getSaldoAcao() + ";" + transacao.getEntidadeCredito().getSaldoTituloDivida() + ";" + transacao.getEntidadeDebito().getIdentificador() + ";" + transacao.getEntidadeDebito().getNome() + ";" + transacao.getEntidadeDebito().getAutorizadoAcao() + ";" + transacao.getEntidadeDebito().getSaldoAcao() + ";" + transacao.getEntidadeDebito().getSaldoTituloDivida() + ";" + transacao.getAcao().getIdentificador() + ";" + transacao.getAcao().getNome() + ";" + transacao.getAcao().getDataDeValidade() + ";" + transacao.getAcao().getValorUnitario() + ";" + transacao.getTituloDivida().getIdentificador() + ";" + transacao.getTituloDivida().getNome() + ";" + transacao.getTituloDivida().getDataDeValidade() + ";" + transacao.getTituloDivida().getTaxaJuros() + ";" + transacao.getValorOperacao() + ";" + transacao.getDataHoraOperacao());
			writer.newLine();
			return  true;
			
		} catch (IOException e) {
			return false;
		}
	}
	public Transacao[] buscarPorEntidadeCredora(int identificadorEntidadeCredito) {
		Transacao[] transacoes = new Transacao[1000];
		int cont = 0;
		try(BufferedReader reader = new BufferedReader((new FileReader(arquivo.toFile())))){
			String linha;
			while((linha = reader.readLine()) != null){

				String[] dados = linha.split(";");

				Acao acao = repositorioAcao.buscar(Integer.parseInt(dados[10]));
				TituloDivida tituloDivida = repositorioTituloDivida.buscar(Integer.parseInt(dados[14]));
				EntidadeOperadora entidadeDebito = repositorioEntidadeOperadora.buscar(Long.parseLong(dados[5]));
				EntidadeOperadora entidadeCredito = repositorioEntidadeOperadora.buscar(Long.parseLong(dados[0]));
				Transacao transacao = new Transacao(entidadeCredito, entidadeDebito,acao, tituloDivida, Double.parseDouble(dados[18]), LocalDateTime.parse(dados[19]));


				if(transacao.getEntidadeCredito().getIdentificador() == identificadorEntidadeCredito){
					transacoes[cont] = transacao;
					cont++;
				}
			}
			return transacoes;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Transacao[] buscarPorEntidadeDevedora(int identificadorEntidadeDebito){
		Transacao[] transacoes = new Transacao[1000];
		int cont = 0;
		try(BufferedReader reader = new BufferedReader((new FileReader(arquivo.toFile())))){
			String linha;
			while((linha = reader.readLine()) != null){

				String[] dados = linha.split(";");

				Acao acao = repositorioAcao.buscar(Integer.parseInt(dados[10]));
				TituloDivida tituloDivida = repositorioTituloDivida.buscar(Integer.parseInt(dados[14]));
				EntidadeOperadora entidadeDebito = repositorioEntidadeOperadora.buscar(Long.parseLong(dados[5]));
				EntidadeOperadora entidadeCredito = repositorioEntidadeOperadora.buscar(Long.parseLong(dados[0]));
				Transacao transacao = new Transacao(entidadeCredito, entidadeDebito,acao, tituloDivida, Double.parseDouble(dados[18]), LocalDateTime.parse(dados[19]));


				if(transacao.getEntidadeCredito().getIdentificador() == identificadorEntidadeDebito){
					transacoes[cont] = transacao;
					cont++;
				}
			}
			return transacoes;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
