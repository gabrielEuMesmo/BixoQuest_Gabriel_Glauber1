package model;

import java.util.ArrayList;
import java.util.Map;

public abstract class Menu extends Atributos{

    private Jogador jogador;
    private String pag = "";
    private Map<String, Opcao> opcoes;
    private ArrayList<Acao> acoes;
    public Menu(Jogador jogador){
        super();
        this.jogador = jogador;
        acoes = new ArrayList<>();
    }

    public abstract boolean realizar(String pag);

    public boolean escolher(int escolha){

        if(opcoes.containsKey(pag + escolha)){
            if(opcoes.get(pag).getOpcoes().get(escolha).equals("sair")){
                pag.substring(pag.length()-1);
            }else {
                pag += escolha;
            }
        }

        return !realizar(pag);

    }

    public Opcao mostrar(){

        return opcoes.get(pag);

    }

    public void addAcao(Acao acao){ acoes.add(acao); }

    public ArrayList<Acao> getAcoes() {
        return acoes;
    }

    public void atualizar(Jogador jogador){
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

    }


}
