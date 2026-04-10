package model;

import java.util.ArrayList;
import java.util.List;

public class Opcao {
    private String titulo;
    private List<String> opcoes;

    public Opcao(){
        opcoes = new ArrayList<>();
    }

    public String getTitulo(){
        return titulo;
    }

    public List<String> getOpcoes() {
        return opcoes;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void addOpcao(String opcao){

        opcoes.add(opcao);
    }

    public void removerOpcao(int num){

        opcoes.remove(num);

    }
}
