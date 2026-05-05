package model;

import java.util.ArrayList;

// Menu responsável pelas interações sociais do jogador.
public class Social extends Menu{

    // Colegas que já atingiram relacionamento máximo.
    private final ArrayList<Colega> amigos;

    // Todos os colegas conhecidos pelo jogador.
    private final ArrayList<Colega> colegas;

    // Colegas disponíveis para conversar no turno atual.
    private final ArrayList<Colega> colegasDisponiveis;

    // Inicializa o menu social e suas listas de relacionamento.
    public Social(Jogador jogador){

        super(jogador, CriarRandom.opcoesSocial(),CriarRandom.opcoesAcoesVariaveisSocial(),CriarRandom.opcoesAcoesFixasSocial());
        colegas = new ArrayList<>();
        amigos = new ArrayList<>();
        colegasDisponiveis = new ArrayList<>();
    }

    // Move para a lista de amigos os colegas com relacionamento máximo.
    public void adicionarAmigos(){

        for(Colega colega : colegas){
            if(colega.getRelacionamento() == 5){
                amigos.add(colega);
            }
        }
    }

    @Override
    // Trata páginas de conversa com colegas antes de delegar ao menu base.
    public boolean realizar(String pag) {
        boolean temp = super.realizar(pag);
        if(pag.length()> 3 && !temp){
            if(pag.charAt(0) == '1' && pag.charAt(1) == '1' && Character.getNumericValue(pag.charAt(2)) < colegasDisponiveis.size()){
                addAcao(colegasDisponiveis.get(Character.getNumericValue(pag.charAt(2))).conversar());
                atualizarOpcoes();
                return true;
            }
        }

        return temp;
    }

    // Reconstrói a lista de colegas disponíveis exibida no submenu social.
    public void atualizarOpcoes(){

        getOpcao("11").getOpcoes().clear();
        colegasDisponiveis.clear();
        adicionarAmigos();

        for(Colega colega : colegas){

            if(!colega.getConversou()){
                colegasDisponiveis.add(colega);
                getOpcao("11").addOpcao(colega.toString());

            }

        }
        getOpcao("11").addOpcao("Voltar");
    }

    // Cria e adiciona um novo colega à lista conhecida.
    public void addColega(){

        colegas.add(CriarRandom.criarColega());

    }

}
