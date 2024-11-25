package br.com.cesarschool.poo.titulos.utils;

public class Ordenador {

    public static void ordenar(Comparavel[] ents, Comparador comp){

        boolean troca = true;
        int i = 0;

        while (i < ents.length && troca) {
            troca = false;
            for (int j = 0; j < ents.length-1-i; j++) {
                if ((comp.comparar(ents[j], ents[j+1]) > 0)) {
                    Comparavel aux = ents[j];
                    ents[j] = ents[j+1];
                    ents[j+1] = aux;
                    troca = true;
                }
            }
        }
    }

    public static void ordenar(Comparavel[] comps){
        boolean troca = true;
        int i = 0;

        while (i < comps.length && troca) {
            troca = false;
            for (int j = 0; j < comps.length-1-i; j++) {
                if ((comps[j].comparar(comps[j+1]) > 0)) {
                    Comparavel aux = comps[j];
                    comps[j] = comps[j+1];
                    comps[j+1] = aux;
                    troca = true;
                }
            }
        }
    }
}
