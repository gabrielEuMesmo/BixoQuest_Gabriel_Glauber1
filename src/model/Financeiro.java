package model;

public class Financeiro extends Menu {

    public Financeiro(Jogador jogador){
        super(jogador, CriarRandom.opcoesFinanceiro(), CriarRandom.opcoesAcoesVariaveisFinanceiro(), CriarRandom.opcoesAcoesFixasFinanceiro());
    }
}
