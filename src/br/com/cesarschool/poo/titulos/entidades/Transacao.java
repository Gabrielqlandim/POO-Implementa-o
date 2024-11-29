package br.com.cesarschool.poo.titulos.entidades;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.cesarschool.poo.titulos.utils.Comparavel;
import br.gov.cesarschool.poo.daogenerico.Entidade;

/*
 * Esta classe deve ter os seguintes atributos:
 * entidadeCredito, do tipo EntidadeOperadora
 * entidadeDebito, do tipo EntidadeOperadora
 * acao, do tipo Acao
 * tituloDivida, do tipo TituloDivida
 * valorOperacao, do tipo double
 * dataHoraOperacao, do tipo LocalDateTime
 *  
 * Deve ter um construtor p�blico que inicializa os atributos.
 * Deve ter m�todos get/set p�blicos para todos os atributos, que 
 * s�o read-only fora da classe.
 * 
 *  
 */ 
public class Transacao extends Entidade implements Comparavel {
    @Override
    public int comparar(Comparavel outro) {
        if (!(outro instanceof Transacao)) {
            throw new IllegalArgumentException("O objeto a ser comparado não é uma instância de Transacao.");
        }
        Transacao outraTransacao = (Transacao) outro;

        return outraTransacao.getDataHoraOperacao().compareTo(this.dataHoraOperacao);
    }

    private EntidadeOperadora entidadeCredito;
    private EntidadeOperadora entidadeDebito;
    private Acao acao;
    private TituloDivida tituloDivida;
    private double valorOperacao;
    private LocalDateTime dataHoraOperacao;



    public Transacao(EntidadeOperadora entidadeCredito, EntidadeOperadora entidadeDebito, Acao acao, TituloDivida tituloDivida, double valorOperacao, LocalDateTime dataHoraOperacao){
        this.acao = acao;
        this.dataHoraOperacao = dataHoraOperacao;
        this.entidadeCredito = entidadeCredito;
        this.entidadeDebito = entidadeDebito;
        this.valorOperacao = valorOperacao;
        this.tituloDivida = tituloDivida;
    }

    @Override
    public String getIdUnico() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String dataFormatada = dataHoraOperacao.format(formatter);
        return entidadeCredito.getIdentificador() + "-" 
        + entidadeDebito.getIdentificador() + "-" 
        + (acao != null ? acao.getIdentificador() : tituloDivida.getIdentificador()) + "-" 
        + dataFormatada;
    }

    //metodos get e set

    public EntidadeOperadora getEntidadeCredito(){
        return entidadeCredito;
    }
    public  EntidadeOperadora getEntidadeDebito(){
        return entidadeDebito;
    }
    public Acao getAcao(){
        return acao;
    }
    public TituloDivida getTituloDivida(){
        return tituloDivida;
    }
    public double getValorOperacao(){
        return valorOperacao;
    }
    public LocalDateTime getDataHoraOperacao(){
        return dataHoraOperacao;
    }

}

