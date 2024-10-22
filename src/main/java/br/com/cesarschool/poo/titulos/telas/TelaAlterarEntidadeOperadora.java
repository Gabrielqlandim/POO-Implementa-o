package br.com.cesarschool.poo.titulos.telas;

import javax.swing.*;
import java.awt.event.ActionEvent;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioAcao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioEntidadeOperadora;

public class TelaAlterarEntidadeOperadora {

    JFrame frame = new JFrame();

    JLabel lblId = new JLabel("Id: ");
    JTextField textFieldId = new JTextField();

    JLabel lblNome = new JLabel("Nome: ");
    JTextField textFieldNome = new JTextField();

    JLabel lblAutorizadoAcao = new JLabel("Autorizado Ação: ");
    String[] tipos = { "true", "false"};
    JComboBox<String> comboBoxTipo = new JComboBox<>(tipos);

    JButton btnBuscar = new JButton("Buscar");
    JButton btnVoltar = new JButton("Voltar");
    JButton btnLimpar = new JButton("Limpar");
    JButton btnAlterar = new JButton("Alterar");
    
    RepositorioEntidadeOperadora repositorioEntidadeOperadora = new RepositorioEntidadeOperadora();

    public TelaAlterarEntidadeOperadora(){

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

        //btnBuscar
        btnBuscar.setBounds(400, 20, 100, 25);
        frame.add(btnBuscar);
        btnBuscar.addActionListener(e -> buscarAlterar(e));

        //btnVoltar
        btnVoltar.setBounds(200, 425, 100, 25);
        frame.add(btnVoltar);
        btnVoltar.addActionListener(e -> voltar(e));

        //btnLimpar
        btnLimpar.setBounds(325, 425, 100, 25);
        frame.add(btnLimpar);
        btnLimpar.addActionListener(e -> limpar(e));

        btnAlterar.setBounds(75, 425, 100, 25);
        btnAlterar.addActionListener(e -> alterar(e));

        frame.setVisible(true);
    }

    private void buscarAlterar(ActionEvent actionEvent){
        try {
            String id = textFieldId.getText();

            RepositorioEntidadeOperadora repositorioEntidadeOperadora = new RepositorioEntidadeOperadora();

            EntidadeOperadora entidadeOperadora = repositorioEntidadeOperadora.buscar(Long.parseLong(id));

            if (entidadeOperadora != null) {

                //nome
                lblNome.setBounds(10, 280, 80, 25);
                frame.add(lblNome);
                textFieldNome.setBounds(150, 280, 165, 25);
                textFieldNome.setText(entidadeOperadora.getNome());
                frame.add(textFieldNome);

                //Autorizadoação
                lblAutorizadoAcao.setBounds(10, 310, 80, 25);
                frame.add(lblAutorizadoAcao);
                comboBoxTipo.setBounds(150, 310, 80, 25);
                comboBoxTipo.setSelectedItem(Boolean.toString(entidadeOperadora.getAutorizadoAcao()));
                frame.add(comboBoxTipo);

                //btnAlterar
                frame.add(btnAlterar);
                
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
        textFieldNome.setText("");
        textFieldId.setText("");
    }

    private void voltar(ActionEvent actionEvent){
        frame.dispose();
    }

    private void alterar(ActionEvent actionEvent){
        try{
            String id = textFieldId.getText();
            String nome = textFieldNome.getText();
            boolean autorizadoAcao = Boolean.parseBoolean((String) comboBoxTipo.getSelectedItem());

            EntidadeOperadora entidadeOperadora = new EntidadeOperadora(Long.parseLong(id), nome, autorizadoAcao);

            RepositorioEntidadeOperadora repositorioEntidadeOperadora = new RepositorioEntidadeOperadora();

            if (repositorioEntidadeOperadora.alterar(entidadeOperadora)){
                JOptionPane.showMessageDialog(frame, "Entidade Operação alterada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(frame, "Erro ao alterar entidade operação: ", "Erro", JOptionPane.ERROR_MESSAGE);
            }

            

        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(frame, "Erro ao converter valores numéricos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
    }
}
