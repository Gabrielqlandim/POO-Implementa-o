package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Transacao;
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
	
	public boolean incluir(Transacao transacao) {
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo.toFile(), true))){
			writer.write(transacao.getEntidadeCredito().getIdentificador() + ";" + transacao.getEntidadeCredito().getNome() + ";" + transacao.getEntidadeCredito().getAutorizadoAcao() + ";" + transacao.getEntidadeCredito().getSaldoAcao() + ";" + transacao.getEntidadeCredito().getTituloDivida() + ";" + transacao.getEntidadeCredito().getIdentificador() + ";" + transacao.getEntidadeCredito().getNome() + ";" + transacao.getEntidadeCredito().getAutorizadoAcao() + ";" + transacao.getEntidadeCredito().getSaldoAcao() + ";" + transacao.getEntidadeCredito().getSaldoTituloDivida() + ";" + transacao.getacao().getIdentificador() + ";" + transacao.getacao().getNome() + ";" + transacao.getacao().getDataValidade() + ";" + transacao.getacao().getValorUnitario() + ";" + transacao.getTituloDivida().getIdentificador() + ";" + transacao.getTituloDivida().getNome() + ";" + transacao.getTituloDivida().getDataValidade() + ";" + transacao.getTituloDivida().getTaxaJuros() + ";" + transacao.getValorOperacao() + ";" + transacao.getDataHoraOperacao());
			writer.newLine();
			return  true;
			
		} catch (IOException e) {
			return false;
		}
	}
	public Transacao[] buscarPorEntidadeCredora(int identificadorEntidadeCredito) {

		return null;
	}
}
