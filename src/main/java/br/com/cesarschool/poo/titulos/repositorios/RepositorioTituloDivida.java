package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.entidades.Acao;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 * Deve gravar em e ler de um arquivo texto chamado TituloDivida.txt os dados dos objetos do tipo
 * TituloDivida. Seguem abaixo exemplos de linhas (identificador, nome, dataValidade, taxaJuros).
 *
    1;BRASIL;2024-12-12;10.5
    2;EUA;2026-01-01;1.5
    3;FRANCA;2027-11-11;2.5 
 * 
 * A inclus�o deve adicionar uma nova linha ao arquivo. N�o � permitido incluir 
 * identificador repetido. Neste caso, o m�todo deve retornar false. Inclus�o com 
 * sucesso, retorno true.
 * 
 * A altera��o deve substituir a linha atual por uma nova linha. A linha deve ser 
 * localizada por identificador que, quando n�o encontrado, enseja retorno false. 
 * Altera��o com sucesso, retorno true.  
 *   
 * A exclus�o deve apagar a linha atual do arquivo. A linha deve ser 
 * localizada por identificador que, quando n�o encontrado, enseja retorno false. 
 * Exclus�o com sucesso, retorno true.
 * 
 * A busca deve localizar uma linha por identificador, materializar e retornar um 
 * objeto. Caso o identificador n�o seja encontrado no arquivo, retornar null.   
 */
public class RepositorioTituloDivida {

	Path arquivo = Paths.get("TituloDivida.txt");
	public boolean incluir(TituloDivida tituloDivida) {
		try(BufferedReader reader = new BufferedReader(new FileReader(arquivo.toFile()))){
			String linha;
			while((linha = reader.readLine()) != null){
				String[] dados = linha.split(";");
				if(dados[0].equals(String.valueOf(tituloDivida.getIdentificador()))){
					return false;
				}
			}
		}catch (IOException e){
			return false;
		}

		try(BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo.toFile(), true))){
			writer.write(tituloDivida.getIdentificador() + ";" + tituloDivida.getNome() + ";" + tituloDivida.getDataDeValidade() + ";" + tituloDivida.getTaxaJuros());
			writer.newLine();
			return  true;
		} catch (IOException e) {
			return false;
		}

	}
	public boolean alterar(TituloDivida tituloDivida) {
		List<String> linhasNovas = new ArrayList<>();
		boolean troca = false;
		try(BufferedReader reader = new BufferedReader(new FileReader(arquivo.toFile()))){
			String linha;
			while((linha = reader.readLine()) != null){

				String[] dados = linha.split(";");
				if(dados[0].equals(String.valueOf(tituloDivida.getIdentificador()))){
					linhasNovas.add(tituloDivida.getIdentificador() + ";" + tituloDivida.getNome() + ";" + tituloDivida.getDataDeValidade() + ";" + tituloDivida.getTaxaJuros());
					troca = true;
				}else{
					linhasNovas.add(linha);
				}
			}
		} catch (Exception e) {
			return false;
		}
		if(troca == true){
			try(BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivo.toFile()))){
				for(String linha : linhasNovas){
					escritor.write(linha);
					escritor.newLine();
				}
				return true;
			} catch (Exception e) {
				return false;
			}
		}else{
			return false;
		}
	}
	public boolean excluir(int identificador) {
		List<String> linhasNovas = new ArrayList<>();
		boolean apagado = false;
		try (BufferedReader reader = new BufferedReader(new FileReader(arquivo.toFile()))) {
			String linha;
			while ((linha = reader.readLine()) != null) {
				String[] dados = linha.split(";");
				if (dados[0].equals(String.valueOf(identificador)) == false) {
					linhasNovas.add(linha);
				}
				else{
					apagado = true;
				}
			}
		} catch (IOException e) {
			return false;
		}
		if(apagado == true){
			try(BufferedWriter escrever = new BufferedWriter(new FileWriter(arquivo.toFile()))) {
				for(String linha : linhasNovas){
					escrever.write(linha);
					escrever.newLine();
				}
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		else{
			return false;
		}
	}
	public Acao buscar(int identificador) {
		try(BufferedReader reader = new BufferedReader((new FileReader(arquivo.toFile())))){
			String linha;
			while((linha = reader.readLine()) != null){
				String[] dados = linha.split(";");
				if(dados[0].equals(String.valueOf(identificador))==true){
					return new Acao(Integer.parseInt(dados[0]), dados[1], LocalDate.parse(dados[2]), Double.parseDouble(dados[3]));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
