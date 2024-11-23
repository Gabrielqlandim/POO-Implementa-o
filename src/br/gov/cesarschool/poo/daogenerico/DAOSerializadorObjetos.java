package br.gov.cesarschool.poo.daogenerico;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/*
 * Esta classe representa um DAO genérico, que inclui, exclui, altera, busca por identificador 
 * único e busca todos, qualquer objeto(s) cujo tipo é subtipo de Entidade.
 * 
 * Sugerimos o uso da API de serialização do JAVA, que grava e lê objetos em arquvos. 
 * Lembrar sempre de fechar (em qualquer circunstância) streams JAVA abertas.
 * 
 * As nuances mais detalhadas do funcionamento desta classe está especificada na classe de testes
 * automatizados br.gov.cesarschool.poo.testes.TestesDAOSerializador.    
 * 
 * A classe deve ter a estrutura (métodos e construtores) dada abaixo.
 */

public class DAOSerializadorObjetos {

	private String nomeDiretorio;

	public DAOSerializadorObjetos(Class<?> tipoEntidade) {
        this.nomeDiretorio = "." + File.separator + tipoEntidade.getSimpleName();
	}
    
	public boolean incluir(Entidade entidade) {
        File diretorio = new File(nomeDiretorio + File.separator);

        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        File arquivo = new File(nomeDiretorio + entidade.getIdUnico());

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))){
            oos.writeObject(entidade);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}

	public boolean alterar(Entidade entidade) {
        File diretorio = new File(nomeDiretorio + File.separator);

        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        File arquivo = new File(nomeDiretorio + File.separator + entidade.getIdUnico());;

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo, false))){
            oos.writeObject(entidade);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
	}
    
	public boolean excluir(String idUnico) {
        File diretorio = new File(nomeDiretorio + File.separator);

        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        File arquivo = new File(nomeDiretorio + File.separator + idUnico);

        if (!arquivo.exists()){
            return false;

        } else {
           arquivo.delete();
            return true;
        }
	}
    
	public Entidade buscar(String idUnico) {
        Entidade[] entidades = buscarTodos();

        for (Entidade entidade:entidades){
            if (entidade.getIdUnico().equals(idUnico)) {
                return entidade;
            }
        }
        return null;
	}
    
	public Entidade[] buscarTodos() {
        File diretorio = new File(nomeDiretorio + File.separator);

        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        List<Entidade> entidades = new ArrayList<>();
        File[] arquivos = diretorio.listFiles();

        if(arquivos != null){
            for (File arquivo : arquivos) {
                if (arquivo.isFile()) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                        Entidade entidade = (Entidade)ois.readObject();
                        entidades.add(entidade);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return entidades.toArray((Entidade[]) new Entidade[0]);
	}
}