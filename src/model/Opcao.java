package model;

import java.util.ArrayList;

public record  Opcao(String titulo, ArrayList<String> opcoes) {


    public String getTitulo(){
        return titulo;
    }

    public ArrayList<String> getOpcoes() {
        return opcoes;
    }

    public String getOpcao(int index){

        return opcoes.get(index);

    }

    public void addOpcao(String opcao){

        opcoes.add(opcao);
    }


}
