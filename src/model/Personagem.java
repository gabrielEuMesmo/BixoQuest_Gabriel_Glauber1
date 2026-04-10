package model;

public abstract class Personagem extends Entidade{

    public Personagem(String nome){

        super(nome);
    }

    public abstract Acao conversar();
}
