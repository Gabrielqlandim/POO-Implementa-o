package br.com.cesarschool.poo.titulos.telas;

import javax.swing.*;
import java.awt.event.ActionEvent;


import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioEntidadeOperadora;

public class TelaIncluirEntidadeOperacao {

    JFrame frame = new JFrame();

    JLabel lblId = new JLabel("Id: ");
    JTextField textFieldId = new JTextField();

    JLabel lblNome = new JLabel("Nome: ");
    JTextField textFieldNome = new JTextField();

    JLabel lblAutorizadoAcao = new JLabel("Autorizado Ação: ");
    String[] tipos = { "true", "false"};
    JComboBox<String> comboBoxTipo = new JComboBox<>(tipos);


    JButton btnIncluir = new JButton("Incluir");
    JButton btnVoltar = new JButton("Voltar");
    JButton btnLimpar = new JButton("Limpar");

    public TelaIncluirEntidadeOperacao(){

        initialize();
    }

    private void initialize (){
        frame.setTitle("Incluir Entidade Operação");
        frame.setSize(600, 500);
        frame.setLayout(null);

        //id
        lblId.setBounds(10, 20, 80, 25);
        frame.add(lblId);
        textFieldId.setBounds(150, 20, 165, 25);
        frame.add(textFieldId);

        //nome
        lblNome.setBounds(10, 50, 80, 25);
        frame.add(lblNome);
        textFieldNome.setBounds(150, 50, 165, 25);
        frame.add(textFieldNome);

        //Autorizadoação
        lblAutorizadoAcao.setBounds(10, 80, 80, 25);
        frame.add(lblAutorizadoAcao);
        comboBoxTipo.setBounds(150, 80, 80, 25);
        frame.add(comboBoxTipo);


        //btnIncluir
        btnIncluir.setBounds(75, 200, 100, 25);
        frame.add(btnIncluir);
        btnIncluir.addActionListener(e -> incluir(e));

        //btnVoltar
        btnVoltar.setBounds(200, 200, 100, 25);
        frame.add(btnVoltar);
        btnVoltar.addActionListener(e -> voltar(e));

        //btnLimpar
        btnLimpar.setBounds(325, 200, 100, 25);
        frame.add(btnLimpar);
        btnLimpar.addActionListener(e -> limpar(e));

        frame.setVisible(true);
    }

    private void incluir(ActionEvent actionEvent){
        try{
            String id = textFieldId.getText();
            String nome = textFieldNome.getText();
            String autorizadoAcao = (String) comboBoxTipo.getSelectedItem();

            boolean autorizacao = Boolean.parseBoolean(autorizadoAcao);

            EntidadeOperadora entidadeOperadora = new EntidadeOperadora(Long.parseLong(id), nome, autorizacao);
            RepositorioEntidadeOperadora repositorioEntidadeOperadora = new RepositorioEntidadeOperadora();

            boolean verificacao = repositorioEntidadeOperadora.incluir(entidadeOperadora);

            System.out.println(verificacao);

            if (verificacao){
                JOptionPane.showMessageDialog(frame, "Entidade Operação Incluida com Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(frame, "Erro ao incluir entidade operação: ", "Erro", JOptionPane.ERROR_MESSAGE);
            }

            

        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(frame, "Erro ao converter valores numéricos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    private void limpar(ActionEvent actionEvent){
        textFieldId.setText("");
        textFieldNome.setText("");
    }

    private void voltar(ActionEvent actionEvent){
        frame.dispose();
    }
}
