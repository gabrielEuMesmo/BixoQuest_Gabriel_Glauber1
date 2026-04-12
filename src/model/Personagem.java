package model;

// Base para personagens com quem o jogador pode interagir.
public abstract class Personagem extends Entidade{

    // Inicializa o personagem com um nome.
    public Personagem(String nome){

        super(nome);
    }

    // Define como cada personagem responde a uma conversa.
    public abstract Acao conversar();
}
