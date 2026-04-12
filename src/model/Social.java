package model;

import java.util.ArrayList;


public class Social extends Menu{

    private final ArrayList<Colega> amigos;
    private final ArrayList<Colega> colegas;
    private final ArrayList<Colega> colegasDisponiveis;

    public Social(Jogador jogador){

        super(jogador,CriarRandom.opcoesSocial(),CriarRandom.opcoesAcoesVariaveisSocial(),CriarRandom.opcoesAcoesFixasSocial());
        colegas = new ArrayList<>();
        amigos = new ArrayList<>();
        colegasDisponiveis = new ArrayList<>();
    }

    public void adicionarAmigos(){

        for(Colega colega : colegas){
            if(colega.getRelacionamento() == 5){
                amigos.add(colega);
            }
        }
    }

    @Override
    public boolean realizar(String pag) {

        if(pag.length()> 3 && !super.realizar(pag)){
            if(pag.charAt(0) == '1' && pag.charAt(1) == '1' && Character.getNumericValue(pag.charAt(2)) < colegasDisponiveis.size()){
                    addAcao(colegasDisponiveis.get(Character.getNumericValue(pag.charAt(2))).conversar());
                    atualizarOpcoes();
                    return true;
            }
        }

        return false;
    }

    public void atualizarOpcoes(){

        getOpcao("11").getOpcoes().clear();
        colegasDisponiveis.clear();
        adicionarAmigos();

        for(Colega colega : colegas){

            if(!colega.getConversou()){
                colegasDisponiveis.add(colega);
                getOpcao("11").addOpcao(colega.toString());

            }
            getOpcao("11").addOpcao("sair");
        }

    }


    public void addColega(){

        colegas.add(CriarRandom.criarColega());

    }

}
