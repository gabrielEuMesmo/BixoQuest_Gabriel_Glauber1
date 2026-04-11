package model;

public interface NPC {

    // Devolve o nome do personagem
    String getNome();

    // Devolve a area de conhecimento do NPC ("EXA", "TEC", "ALG" ou "GERAL")
    String getArea();

    // Puxa a cena que corresponde ao nivel de relacionamento atual com o NPC
    CenaDialogo getCenaAtual();

    // Atualiza o nivel de amizade (positivo = cresce, negativo = cai, nunca abaixo de 0)
    void aumentarRelacionamento(int valor);

    // Devolve o nivel de relacionamento atual
    int getNivelRelacionamento();
}
