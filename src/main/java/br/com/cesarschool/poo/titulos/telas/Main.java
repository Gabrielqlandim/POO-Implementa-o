package br.com.cesarschool.poo.titulos.telas;


import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        TelaMain telaMain = new TelaMain();
        JFrame frame = new JFrame();
        frame.setTitle("Inicio");
        frame.setSize(600, 500);
        frame.add(telaMain);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
