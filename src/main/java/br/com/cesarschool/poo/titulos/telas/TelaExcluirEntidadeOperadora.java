package br.com.cesarschool.poo.titulos.telas;

import javax.swing.*;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioEntidadeOperadora;

import java.awt.event.ActionEvent;


public class TelaExcluirEntidadeOperadora {

    JFrame frame = new JFrame();

    JLabel lblId = new JLabel("Id: ");
    JTextField textFieldId = new JTextField();

    JButton btnExcluir = new JButton("Excluir");
    JButton btnVoltar = new JButton("Voltar");
    JButton btnLimpar = new JButton("Limpar");

    public TelaExcluirEntidadeOperadora(){

        initialize();
    }

    private void initialize (){
        
        frame.setTitle("Excluir Entidade Operação");
        frame.setSize(600, 500);
        frame.setLayout(null);

        //id
        
        lblId.setBounds(10, 20, 80, 25);
        frame.add(lblId);
        
        textFieldId.setBounds(150, 20, 165, 25);
        frame.add(textFieldId);

        //btnExcluir
        
        btnExcluir.setBounds(75, 200, 100, 25);
        frame.add(btnExcluir);
        btnExcluir.addActionListener(e -> excluir(e));

        //btnVoltar
        
        btnVoltar.setBounds(200, 200, 100, 25);
        frame.add(btnVoltar);

        //btnLimpar
        
        btnLimpar.setBounds(325, 200, 100, 25);
        frame.add(btnLimpar);
        btnLimpar.addActionListener(e -> limpar(e));

        frame.setVisible(true);
    }

    private void excluir(ActionEvent actionEvent){
        try{
            String id = textFieldId.getText();

            RepositorioEntidadeOperadora repositorioEntidadeOperadora = new RepositorioEntidadeOperadora();

            if (repositorioEntidadeOperadora.excluir(Long.parseLong(id))){
                JOptionPane.showMessageDialog(frame, "Entidade Operação excluida com Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(frame, "Erro ao excluir entidade operação: Inexistente", "Erro", JOptionPane.ERROR_MESSAGE);
            }

            

        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(frame, "Erro ao converter valores numéricos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    private void limpar(ActionEvent actionEvent){
        textFieldId.setText("");
    }

}
