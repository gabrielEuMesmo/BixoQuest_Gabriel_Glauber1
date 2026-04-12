package model;

// Menu responsável pelas ações ligadas à vida acadêmica.
public class Academico extends Menu {

    // Inicializa o menu acadêmico com as opções e ações pré-configuradas.
    public Academico(Jogador jogador){
        super(jogador, CriarRandom.opcoesAcademico(), CriarRandom.opcoesAcoesVariaveisAcademico(), CriarRandom.opcoesAcoesFixasAcademico());
    }
}
