package model;

// Entidade base identificada por nome.
public abstract class Entidade {
    // Nome da entidade.
    private final String nome;

    // Cria uma nova entidade nomeada.
    public Entidade(String nomeNovo){

        nome = nomeNovo;

    }

    // Retorna o nome da entidade.
    public String getNome(){

        return nome;

    }
}
