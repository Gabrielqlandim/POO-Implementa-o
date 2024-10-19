package br.com.cesarschool.poo.titulos.mediators;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioEntidadeOperadora;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTransacao;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;

/*
 * Deve ser um singleton.
 *
 * Deve ter os seguites atributos:
 *
 * mediatorAcao, do tipo MediatorAcao
 * mediatorTituloDivida, do tipo MediatorTituloDivida
 * mediatorEntidadeOperadora, do tipo MediatorEntidadeOperadora
 * repositorioTransacao, do tipo RepositorioTransacao
 *
 * Estes atributos devem ser inicializados nas suas declarações.
 *
 * Estes atributos serão usados exclusivamente pela classe, não tendo, portanto, métodos set e get.
 *
 * Métodos:
 *
 * public String realizarOperacao(boolean ehAcao, int entidadeCredito,
 * int idEntidadeDebito, int idAcaoOuTitulo, double valor): segue o
 * passo a passo deste método.
 *
 * 1- Validar o valor, que deve ser maior que zero. Se o valor for inválido,
 * retornar a mensagem "Valor inválido".
 *
 * 2- Buscar a entidade de crédito no mediator de entidade operadora.
 * Se não encontrar, retornar a mensagem "Entidade crédito inexistente".
 *
 * 3- Buscar a entidade de débito no mediator de entidade operadora.
 * Se não encontrar, retornar a mensagem "Entidade débito inexistente".
 *
 * 4- Se ehAcao for true e a entidade de crédito não for autorizada para
 * ação, retornar a mensagem "Entidade de crédito não autorizada para ação"
 *
 * 5- Se ehAcao for true e a entidade de débito não for autorizada para
 * ação, retornar a mensagem "Entidade de débito não autorizada para ação"
 *
 * 6- Buscar a ação (se ehAcao for true) ou o título (se ehAcao for false)
 * no mediator de ação ou de título. "NAO ENTENDI COMO FAZ"
 *
 * 7- A depender de ehAcao, validar o saldoAcao ou o saldoTituloDivida da
 * entidade de débito. O saldo deve ser maior ou igual a valor. Se não for,
 * retornar a mensagem "Saldo da entidade débito insuficiente".
 *
 * 8- Se ehAcao for true, verificar se valorUnitario da ação é maior do que
 * valor. Se for, retornar a mensagem
 * "Valor da operação e menor do que o valor unitário da ação"
 *
 * 9- Calcular o valor da operação. Se ehAcao for true, o valor da operação
 * é igual a valor. Se ehAcao for false, o valor da operação é igual ao
 * retorno do método calcularPrecoTransacao, invocado a partir do título da
 * dívida buscado. valor deve ser passado como parâmetro na invocação deste
 * método.
 *
 *  10- Invocar o método creditarSaldoAcao ou creditarSaldoTituloDivida da
 *  entidade de crédito, passando o valor da operação.
 *
 *  11- Invocar o método debitarSaldoAcao ou debitarSaldoTituloDivida da
 *  entidade de débito, passando o valor da operação.
 *
 *  12- Alterar entidade de crédito no mediator de entidade operadora. Se
 *  o retorno do alterar for uma mensagem diferente de null, retornar a
 *  mensagem.
 *
 *  13- Alterar entidade de débito no mediator de entidade operadora. Se
 *  o retorno do alterar for uma mensagem diferente de null, retornar a
 *  mensagem.
 *
 *  14- Criar um objeto do tipo Transacao, com os seguintes valores de atributos:
 *
 * entidadeCredito - a entidade de crédito buscada
 * entidadeDebito - a entidade de débito buscada
 * acao - se ehAcao for true, a ação buscada, caso contrário, null
 * tituloDivida - se ehAcao for false, o título buscado, caso contrário, null
 * valorOperacao - o valor da operação calculado no item 9
 * dataHoraOperacao - a data e a hora atuais
 *
 * 15- Incluir a transação criada no repositório de transação.
 *
 * public Transacao gerarExtrato(int entidade):
 *
 * 1- para este método funcionar, deve-se acrescentar no repositório de
 * transação o método
 * public Transacao[] buscarPorEntidadeCredora(int identificadorEntidadeDebito)
 * A busca deve retornar um array de transações cuja entidadeDebito tenha identificador igual ao
 * recebido como parâmetro.
 *
 * 2- Buscar as transações onde entidade é credora.
 *
 * 3- Buscar as transações onde entidade é devedora.
 *
 * 4- Colocar as transações buscadas nos itens 2 e 3 em um único novo array.
 *
 * 5- Ordenar este novo array por dataHoraOperacao decrescente.
 *
 * 6- Retornar o novo array.
 **/
public class MediatorOperacao {

    //singleton
    private static MediatorOperacao instanciaUnica;


    private MediatorOperacao(){
    }

    public static  MediatorOperacao getInstance(){
        if(instanciaUnica == null){
            instanciaUnica = new MediatorOperacao();
        }
        return instanciaUnica;
    }


    //atributos

    private MediatorAcao mediatorAcao = new MediatorAcao();
    private MediatorTituloDivida mediatorTituloDivida = new MediatorTituloDivida();
    private MediatorEntdadeOperadora mediatorEntdadeOperadora = new MediatorEntdadeOperadora();
    private RepositorioTransacao repositorioTransacao = new RepositorioTransacao();

    //metodos
    public String realizarOperacao(boolean ehAcao, int entidadeCredito, int idEntidadeDebito, int idAcaoOuTitulo, double valor){
        if(valor <= 0.0){
            return "Valor inválido";
        }
        EntidadeOperadora entidadeCreditoOBJ = MediatorEntdadeOperadora.buscar(entidadeCredito);
        if(entidadeCreditoOBJ == null){
            return "Entidade crédito inexistente";
        }
        EntidadeOperadora entidadeDebito = MediatorEntdadeOperadora.buscar(idEntidadeDebito);
        if(entidadeDebito == null){
            return "Entidade débito inexistente";
        }
        if(ehAcao == true && entidadeCreditoOBJ.getAutorizadoAcao() == false){
            return "Entidade de crédito não autorizada para ação";
        }
        if(ehAcao == true && entidadeDebito.getAutorizadoAcao() == false){
            return "Entidade de débito não autorizada para ação";
        }

        //ainda tem outras coisas para fazer que eu nao entendi

        LocalDateTime data = LocalDateTime.now();

        if (ehAcao) {
            Acao acao = mediatorAcao.buscar(idAcaoOuTitulo);
            double saldo = entidadeDebito.getSaldoAcao();

            if (saldo < valor) {
                return "Saldo da entidade débito insuficiente";
            }

            else if (acao.getValorUnitario() > valor) {
                return "Valor da operação e menor do que o valor unitário da ação";
            }

            double valorOperacao = valor;

            entidadeCreditoOBJ.creditarSaldoAcao(valorOperacao);
            entidadeDebito.debitarSaldoAcao(valorOperacao);

            Transacao transacao = new Transacao(entidadeCreditoOBJ, entidadeDebito, acao, null, valorOperacao, data);
            repositorioTransacao.incluir(transacao);

        }else{
            TituloDivida titulo = mediatorTituloDivida.buscar(idAcaoOuTitulo);
            double saldo = entidadeDebito.getSaldoTituloDivida();

            if (saldo < valor) {
                return "Saldo da entidade débito insuficiente";
            }

            double valorOperacao = titulo.calcularPrecoTransacao(valor);

            entidadeCreditoOBJ.creditarSaldoTituloDivida(valorOperacao);
            entidadeDebito.debitarSaldoTituloDivida(valorOperacao);

            Transacao transacao = new Transacao(entidadeCreditoOBJ, entidadeDebito, null, titulo, valorOperacao, data);
            repositorioTransacao.incluir(transacao);

        }

        String mensagem = mediatorEntdadeOperadora.alterar(entidadeCreditoOBJ);
        
        if (mensagem != null) {
            return mensagem;
        }

        mensagem = mediatorEntdadeOperadora.alterar(entidadeDebito);

        if (mensagem != null) {
            return mensagem;
        }



    }


    public Transacao[] gerarExtrato(int entidade){

        Transacao[] transCredora = repositorioTransacao.buscarPorEntidadeCredora(entidade);
        Transacao[] transDevedora = repositorioTransacao.buscarPorEntidadeDevedora(entidade);

        Transacao[] transacoes = new Transacao[transCredora.length + transDevedora.length];
        System.arraycopy(transCredora,0,transacoes,0, transCredora.length);
        System.arraycopy(transDevedora,0,transacoes,0,transDevedora.length);

        Arrays.sort(transacoes, Comparator.comparing(Transacao::getDataHoraOperacao).reversed());

        return transacoes;
    }
}