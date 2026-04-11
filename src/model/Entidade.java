package model;

public abstract class Entidade {
    private final String nome;

    public Entidade(String nomeNovo){

        nome = nomeNovo;

    }
    public String getNome(){

        return nome;

    }
}
