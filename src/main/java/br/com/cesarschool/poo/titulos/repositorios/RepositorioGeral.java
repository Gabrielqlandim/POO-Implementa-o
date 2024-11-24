package br.com.cesarschool.poo.titulos.repositorios;

import br.gov.cesarschool.poo.daogenerico.DAOSerializadorObjetos;

public abstract class RepositorioGeral {

    protected DAOSerializadorObjetos dao;

    public RepositorioGeral () {
        this.dao = new DAOSerializadorObjetos(getClasseEntidade());
    }

    protected abstract Class<?> getClasseEntidade();
    
}