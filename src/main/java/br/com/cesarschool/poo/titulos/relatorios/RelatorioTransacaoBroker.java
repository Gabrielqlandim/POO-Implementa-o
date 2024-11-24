package br.com.cesarschool.poo.titulos.relatorios;

import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTransacao;
import br.com.cesarschool.poo.titulos.utils.Comparador;
import br.com.cesarschool.poo.titulos.utils.Comparavel;
import br.com.cesarschool.poo.titulos.utils.ComparadorPadrao;
import br.com.cesarschool.poo.titulos.utils.Ordenador;

public class RelatorioTransacaoBroker {
    private final RepositorioTransacao repositorioTransacao;

    public RelatorioTransacaoBroker() {
        this.repositorioTransacao = new RepositorioTransacao();
    }

    public Transacao[] gerarRelatorioPorNomeEntidadeCredora() {
        Transacao[] transacoes = repositorioTransacao.buscarTodos();
        Ordenador.ordenar(transacoes, new ComparadorPadrao());
        return transacoes;
    }

    public Transacao[] gerarRelatorioPorDataHoraOperacao() {
        Transacao[] transacoes = repositorioTransacao.buscarTodos();
        Ordenador.ordenar(transacoes, new Comparador() {
            
            public int comparar(Comparavel c1, Comparavel c2) {
                Transacao t1 = (Transacao) c1;
                Transacao t2 = (Transacao) c2;
                return t2.getDataHoraOperacao().compareTo(t1.getDataHoraOperacao()); 
            }
        });
        return transacoes;
    }
}
