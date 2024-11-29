package br.com.cesarschool.poo.titulos.repositorios;

import br.gov.cesarschool.poo.daogenerico.DAOSerializadorObjetos;

public abstract class RepositorioGeral {

    protected DAOSerializadorObjetos dao;

    Class<?> classeEntidade;
    public RepositorioGeral (Class<?> classeEntidade) {
        this.classeEntidade = classeEntidade;
        this.dao = new DAOSerializadorObjetos(getClasseEntidade());
    }

    public abstract Class<?> getClasseEntidade();

    public DAOSerializadorObjetos getDao () {
        return dao;
    }

}