package br.com.cesarschool.poo.titulos.repositorios;


import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.gov.cesarschool.poo.daogenerico.Entidade;

/*
 * Deve gravar em e ler de um arquivo texto chamado Acao.txt os dados dos objetos do tipo
 * Acao. Seguem abaixo exemplos de linhas.
 *
    1;PETROBRAS;2024-12-12;30.33
    2;BANCO DO BRASIL;2026-01-01;21.21
    3;CORREIOS;2027-11-11;6.12 
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
public class RepositorioEntidadeOperadora extends RepositorioGeral {

    public RepositorioEntidadeOperadora(){
        super();
    }

    protected Class<?> getClasseEntidade(){
        return EntidadeOperadora.class;
    }


    public boolean incluir(EntidadeOperadora entidadeOperadora) {
        if (buscar(entidadeOperadora.getIdentificador()) != null) {
            return false;
        }
        return dao.incluir(entidadeOperadora);
    }



    public boolean alterar(EntidadeOperadora entidadeOperadora) {
        if(buscar(entidadeOperadora.getIdentificador())==null){
            return false;
        }
        return dao.alterar(entidadeOperadora);
    }



    public boolean excluir(long identificador) {
        if(buscar(identificador)==null){
            return false;
        }
        return dao.excluir(String.valueOf(identificador));
    }


    public EntidadeOperadora buscar(long identificador) {
        Entidade entidade = dao.buscar(String.valueOf(identificador));
        if(entidade instanceof EntidadeOperadora){
            return (EntidadeOperadora) entidade;
        }
        return null;
    }
}
