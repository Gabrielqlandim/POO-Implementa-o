package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.gov.cesarschool.poo.daogenerico.DAOSerializadorObjetos;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
public class RepositorioEntidadeOperadora {

    DAOSerializadorObjetos dao = new DAOSerializadorObjetos(EntidadeOperadora.class);

    public boolean incluir(EntidadeOperadora entidadeOperadora) {
        if (buscar(entidadeOperadora.getIdentificador()) != null) {
            return false;
        }
        return dao.incluir(entidadeOperadora);
    }



    public boolean alterar(EntidadeOperadora entidadeOperadora) {
        return dao.alterar(entidadeOperadora);
    }



    public boolean excluir(long identificador) {
        return dao.excluir(String.valueOf(identificador));
    }


    public EntidadeOperadora buscar(long identificador) {
        return dao.buscar(String.valueOf(identificador));
    }
}
