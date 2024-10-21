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
        frame.setTitle("Inicio");
        frame.setSize(600, 500);
        frame.setLayout(null);

        //buscar
        buscar.setBounds(75, 200, 100, 25);
        frame.add(buscar);
        buscar.addActionListener(e -> buscar(e));

        //excluir
        excluir.setBounds(200, 200, 100, 25);
        frame.add(excluir);
        excluir.addActionListener(e -> excluir(e));

        //incluir
        incluir.setBounds(325, 200, 100, 25);
        frame.add(incluir);
        incluir.addActionListener(e -> incluir(e));

        //alterar
        alterar.setBounds(450, 200, 100, 25);
        frame.add(alterar);
        alterar.addActionListener(e -> alterar(e));
        frame.setVisible(true);

    }

    public TelaMain(){
        initialize();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

