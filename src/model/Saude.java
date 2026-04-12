package model;

public class Saude extends Menu {

    public Saude(Jogador jogador){
        super(jogador, CriarRandom.opcoesSaude(), CriarRandom.opcoesAcoesVariaveisSaude(), CriarRandom.opcoesAcoesFixasSaude());
    }
}
