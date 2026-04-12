package model;

import java.util.ArrayList;

// Representa uma tela de menu com título e lista de opções.
public class Opcao {
    private String titulo;
    private ArrayList<String> opcoes;
    public Opcao(String titulo, ArrayList<String> opcoes){
        this.titulo = titulo;
        this.opcoes = new ArrayList<>(opcoes);

    }

    // Retorna o título exibido na opção atual.
    public String getTitulo(){
        return titulo;
    }

    // Retorna todas as entradas configuradas nesta opção.
    public ArrayList<String> getOpcoes() {
        return opcoes;
    }

    // Retorna uma entrada específica pelo índice informado.
    public String getOpcao(int index){

        return opcoes.get(index);

    }

    // Adiciona uma nova entrada ao final da lista de opções.
    public void addOpcao(String opcao){

        opcoes.add(opcao);
    }


}
