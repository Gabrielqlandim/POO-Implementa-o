package br.com.cesarschool.poo.titulos.telas;

import javax.swing.*;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioEntidadeOperadora;

import java.awt.event.ActionEvent;


public class TelaBuscarEntidadeOperadora {

    JFrame frame = new JFrame();
    JLabel lblId = new JLabel("Id: ");
    JTextField textFieldId = new JTextField();

    JButton btnBuscar = new JButton("Buscar");
    JButton btnVoltar = new JButton("Voltar");
    JButton btnLimpar = new JButton("Limpar");

    public TelaBuscarEntidadeOperadora(){

        initialize();
    }

    private void initialize (){
        
        frame.setTitle("Buscar Entidade Operação");
        frame.setSize(600, 500);
        frame.setLayout(null);

        //id
        
        lblId.setBounds(10, 20, 80, 25);
        frame.add(lblId);
        
        textFieldId.setBounds(150, 20, 165, 25);
        frame.add(textFieldId);

        //botao buscar
        
        btnBuscar.setBounds(75, 200, 100, 25);
        frame.add(btnBuscar);
        btnBuscar.addActionListener(e -> buscar(e));

        //btnVoltar
        
        btnVoltar.setBounds(200, 200, 100, 25);
        frame.add(btnVoltar);

        //btnLimpar
        
        btnLimpar.setBounds(325, 200, 100, 25);
        frame.add(btnLimpar);
        btnLimpar.addActionListener(e -> limpar(e));

        frame.setVisible(true);
    }

    private void buscar(ActionEvent actionEvent){
        try {
            String id = textFieldId.getText();

            RepositorioEntidadeOperadora repositorioEntidadeOperadora = new RepositorioEntidadeOperadora();

            EntidadeOperadora entidadeOperadora = repositorioEntidadeOperadora.buscar(Long.parseLong(id));

            if (entidadeOperadora != null) {
                JLabel lblnome = new JLabel("Nome: " + entidadeOperadora.getNome());
                lblnome.setBounds(75, 200, 100, 25);

                JLabel lblAutorizadoAcao = new JLabel("Autorizado Ação: " + entidadeOperadora.getAutorizadoAcao());
                lblAutorizadoAcao.setBounds(200, 200, 100, 25);

                JLabel lblSaldoAcao = new JLabel("Saldo Ação: " + entidadeOperadora.getSaldoAcao());
                lblSaldoAcao.setBounds(325, 200, 100, 25);

                JLabel lblSaldoTituloDivida = new JLabel("Saldo Título Dívida: " + entidadeOperadora.getSaldoTituloDivida());
                lblSaldoTituloDivida.setBounds(450, 200, 100, 25);
            
            }else{
                JOptionPane.showMessageDialog(frame, "Entidade operação não encontrada", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao converter valores numéricos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void limpar(ActionEvent actionEvent){
        textFieldId.setText("");
    }

}