package br.com.cesarschool.poo.titulos.telas;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TelaMain extends JPanel{
    JFrame frame = new JFrame();

    JButton alterar = new JButton("alterar");
    JButton buscar = new JButton("buscar");
    JButton excluir = new JButton("excluir");
    JButton incluir = new JButton("incluir");

    private void initialize(){
        setLayout(null);
        //buscar
        buscar.setBounds(75, 200, 100, 25);
        frame.add(buscar);
        buscar.addActionListener(e -> buscar(e));

        //excluir
        excluir.setBounds(75, 200, 100, 25);
        frame.add(excluir);
        excluir.addActionListener(e -> excluir(e));

        //incluir
        incluir.setBounds(75, 200, 100, 25);
        frame.add(incluir);
        incluir.addActionListener(e -> incluir(e));

        //alterar
        alterar.setBounds(75, 200, 100, 25);
        frame.add(alterar);
        alterar.addActionListener(e -> alterar(e));
    }

    public TelaMain(){
        initialize();

        frame.setTitle("Tela Principal");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.add(this.buscar);
        frame.add(this.excluir);
        frame.add(this.alterar);
        frame.add(this.incluir);



        frame.setVisible(true);
    }


    private void buscar(ActionEvent actionEvent){
        TelaBuscarEntidadeOperadora telaBuscarEntidadeOperadora = new TelaBuscarEntidadeOperadora();

    }

    private void excluir(ActionEvent actionEvent){
        TelaExcluirEntidadeOperadora telaExcluirEntidadeOperadora = new TelaExcluirEntidadeOperadora();
    }

    private void incluir(ActionEvent actionEvent){
        TelaIncluirEntidadeOperacao telaIncluirEntidadeOperacao = new TelaIncluirEntidadeOperacao();
    }

    private void alterar(ActionEvent actionEvent){
        TelaAlterarEntidadeOperadora telaAlterarEntidadeOperadora = new TelaAlterarEntidadeOperadora();
    }
}

