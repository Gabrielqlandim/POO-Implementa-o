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

    JLabel lblNome = new JLabel();
    JLabel lblAutorizadoAcao = new JLabel();
    JLabel lblSaldoAcao = new JLabel();
    JLabel lblSaldoTituloDivida = new JLabel();

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
        
        btnBuscar.setBounds(75, 400, 100, 25);
        frame.add(btnBuscar);
        btnBuscar.addActionListener(e -> buscar(e));

        //btnVoltar
        
        btnVoltar.setBounds(200, 400, 100, 25);
        frame.add(btnVoltar);
        btnVoltar.addActionListener(e -> voltar(e));

        //btnLimpar
        
        btnLimpar.setBounds(325, 400, 100, 25);
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

                frame.remove(lblNome);
                frame.remove(lblAutorizadoAcao);
                frame.remove(lblSaldoAcao);
                frame.remove(lblSaldoTituloDivida);

                
                lblNome.setText("Nome: " + entidadeOperadora.getNome());
                lblNome.setBounds(75, 100, 200, 25);
                frame.add(lblNome);
                
                lblAutorizadoAcao.setText("Autorizado Ação: " + entidadeOperadora.getAutorizadoAcao());
                lblAutorizadoAcao.setBounds(75, 150, 200, 25);
                frame.add(lblAutorizadoAcao);
                
                lblSaldoAcao.setText("Saldo Ação: " + entidadeOperadora.getSaldoAcao());
                lblSaldoAcao.setBounds(75, 200, 200, 25);
                frame.add(lblSaldoAcao);
                
                lblSaldoTituloDivida.setText("Saldo Título Dívida: " + entidadeOperadora.getSaldoTituloDivida());
                lblSaldoTituloDivida.setBounds(75, 250, 200, 25);
                frame.add(lblSaldoTituloDivida);

                frame.revalidate();
                frame.repaint();
            
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

    private void voltar(ActionEvent actionEvent){
        frame.dispose();
    }
}