package model;
import java.util.ArrayList;

public class Opcao {
    private final String titulo;
    private ArrayList<String> opcoes;

    public Opcao(String titulo){
        this.titulo = titulo;
        opcoes = new ArrayList<>();
    }

    public String getTitulo(){
        return titulo;
    }

    public ArrayList<String> getOpcoes() {
        return opcoes;
    }

    public String getOpcao(int i){
        return opcoes.get(i);
    }

    public void addOpcao(String opcao){
        opcoes.add(opcao);
    }
}