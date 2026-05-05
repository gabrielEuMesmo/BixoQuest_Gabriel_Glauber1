package model;

// Menu responsável pelas ações financeiras do jogador.
public class Financeiro extends Menu {

    // Inicializa o menu financeiro com as opções e ações pré-configuradas.
    public Financeiro(Jogador jogador){
        super(jogador, CriarRandom.opcoesFinanceiro(), CriarRandom.opcoesAcoesVariaveisFinanceiro(), CriarRandom.opcoesAcoesFixasFinanceiro());
    }
}
