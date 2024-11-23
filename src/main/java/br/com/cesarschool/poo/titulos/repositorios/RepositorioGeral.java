package br.com.cesarschool.poo.titulos.repositorios;

import br.gov.cesarschool.poo.daogenerico.DAOSerializadorObjetos;

public abstract class RepositorioGeral {

    DAOSerializadorObjetos dao;

    public RepositorioGeral (Class<?> tipoEntidade) {
        this.dao = new DAOSerializadorObjetos(tipoEntidade);
    }
}
