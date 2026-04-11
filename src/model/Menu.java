package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Menu extends Atributos{

    private final Jogador jogador;

    private String pag = "";

    private final Map<String, Opcao> opcoes;

    //Ações que podem ser feitas (exceto conversar);
    private final Map<String, Acao[]> opcoesAcoesVariaveis;

    //Ações que podem ser feitas (exceto conversar);
    private final Map<String, Acao[]> acoesDisponiveis;


    //Ações realizadas no turno;
    private final ArrayList<Acao> acoesFeitas;

    public Menu(Jogador jogador, HashMap<String, Opcao> opcoes, HashMap<String,Acao[]> acoesVariaveis, HashMap<String,Acao[]> acoesFixas){
        super();
        this.jogador = jogador;
        acoesFeitas = new ArrayList<>();
        this.opcoes = opcoes;
        opcoesAcoesVariaveis = acoesVariaveis;
        acoesDisponiveis =new HashMap<>(acoesFixas);
    }

    public boolean realizar(String pag){

        if(acoesDisponiveis.containsKey(pag.substring(0, pag.length()-1))){

            addAcao(new Acao(acoesDisponiveis.get(pag.substring(0, pag.length()-1))[pag.charAt(pag.length()-1) - '0']));
            this.pag = "";

            return true;
        }
        return false;
    }

    public void addAcao(Acao acao){

            acoesFeitas.add(acao);
            somaAtributos(acao);

    }

    public boolean escolher(int escolha){

        if(opcoes.containsKey(pag + escolha)){

            if(opcoes.get(pag).getOpcao(escolha).equals("sair")){
                pag = pag.substring(0, pag.length()-1);
            }else {
                pag += escolha;
            }

        }



        return !realizar(pag);

    }

    public Opcao getOpcao(String pag){
        return opcoes.get(pag);
    }


    public Opcao mostrar(){

        return opcoes.get(pag);

    }

    public ArrayList<Acao> getAcoes() {

        return acoesFeitas;

    }

    public void atualizar(){

        jogador.somarSaude(getSaldoSaude());
        jogador.somarMotivacao(getSaldoMotivacao());
        jogador.somarDinheiro(getSaldoDinheiro());
        jogador.somarDes_acad_m1(getSaldoDes_acad_m1());
        jogador.somarDes_acad_m2(getSaldoDes_acad_m2());
        jogador.somarDes_acad_m3(getSaldoDes_acad_m3());
        setSaldoSaude(0);
        setSaldoMotivacao(0);
        setSaldoDinheiro(0);
        setSaldoDes_acad_m1(0);
        setSaldoDes_acad_m2(0);
        setSaldoDes_acad_m3(0);

        acoesFeitas.clear();

    }


}
