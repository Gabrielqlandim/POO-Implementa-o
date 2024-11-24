package br.com.cesarschool.poo.titulos.repositorios;

import br.gov.cesarschool.poo.daogenerico.DAOSerializadorObjetos;

public abstract class RepositorioGeral {

    protected DAOSerializadorObjetos dao;

    public RepositorioGeral () {
        this.dao = new DAOSerializadorObjetos(getClasseEntidade());
    }

    protected abstract Class<?> getClasseEntidade();

    //fiz esse emtodo genero para buscar os objetos do dao. busca todos
    protected Object[] buscarTodos(){
        return dao.buscarTodos();
    }
}
