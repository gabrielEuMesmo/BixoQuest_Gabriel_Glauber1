package model;

// Menu responsável pelas ações relacionadas à saúde e bem-estar.
public class Saude extends Menu {

    // Inicializa o menu de saúde com as opções e ações pré-configuradas.
    public Saude(Jogador jogador){
        super(jogador, CriarRandom.opcoesSaude(), CriarRandom.opcoesAcoesVariaveisSaude(), CriarRandom.opcoesAcoesFixasSaude());
    }
}
