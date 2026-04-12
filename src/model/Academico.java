package model;

public class Academico extends Menu {

    public Academico(Jogador jogador){
        super(jogador, CriarRandom.opcoesAcademico(), CriarRandom.opcoesAcoesVariaveisAcademico(), CriarRandom.opcoesAcoesFixasAcademico());
    }
}