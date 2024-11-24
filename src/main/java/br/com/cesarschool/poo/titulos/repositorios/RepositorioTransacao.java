package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.gov.cesarschool.poo.daogenerico.Entidade;

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

public class RepositorioTransacao extends RepositorioGeral{
	
	public RepositorioTransacao() {
		super();
	}

	protected Class<?> getClasseEntidade(){
		return Transacao.class;
	}

	public boolean incluir(Transacao transacao) {
		if (buscar(transacao.getIdUnico()) != null) {
            return false;
        }
        return dao.incluir(transacao);
	}
	public Transacao buscar(String idUnico) {
		Transacao[] transacoes = (Transacao[]) dao.buscarTodos();
		for (Transacao transacao : transacoes) {
			if (transacao.getIdUnico().equals(idUnico)) {
				return transacao;
			}
		}
		return null;
	}

	public Transacao[] buscarPorEntidadeCredora(int identificadorEntidadeCredito) {
		List<Transacao> result = new ArrayList<>();
		Transacao[] transacoes = (Transacao[]) dao.buscarTodos();
		for (Transacao transacao : transacoes) {
			if (transacao.getEntidadeCredito().getIdentificador() == identificadorEntidadeCredito) {
				result.add(transacao);
			}
		}
		return result.toArray(new Transacao[0]);
	}
	
	public Transacao[] buscarPorEntidadeDevedora(int identificadorEntidadeDebito){
		List<Transacao> result = new ArrayList<>();
		Transacao[] transacoes = (Transacao[]) dao.buscarTodos();
		for (Transacao transacao : transacoes) {
			if(transacao.getEntidadeDebito().getIdentificador() == identificadorEntidadeDebito){
				result.add(transacao);
			}
		}
		return result.toArray(new Transacao[0]);
	}

	public Transacao[] buscarTodos(){
		Entidade[] entidades = dao.buscarTodos();

		List<Transacao> transacoes = new ArrayList<>();

		for(Entidade entidade : entidades){
			transacoes.add((Transacao)entidade);
		}
		return transacoes.isEmpty() ? new Transacao[0] : transacoes.toArray(new Transacao[0]);
	}
}
