package model;

import java.util.ArrayList;
import java.util.List;

public class Acao extends Atributos {
    private final String tipo;
    private final List<String> nomes;

    //Construtor Normal;
    public  Acao (String novoTipo, int saldoSaude, int saldoMotivacao, double saldoDinheiro, int saldoDes_acad_m1, int saldoDes_acad_m2, int saldoDes_acad_m3){
        super(saldoSaude, saldoMotivacao, saldoDinheiro, saldoDes_acad_m1, saldoDes_acad_m2, saldoDes_acad_m3);
        tipo = novoTipo;
        nomes = new ArrayList<>();
    }
    //Contrutor de cópia
    public  Acao (Acao outraAcao){
        super(outraAcao);
        tipo = outraAcao.getTipo();
        nomes = new ArrayList<>(outraAcao.nomes);
    }

    public void addNome(String nome){
        nomes.add(nome);
    }

    @Override
    public String toString(){
        String resposta = tipo;
        if(!nomes.isEmpty()){

            resposta += " com ";

            for (String nome : nomes){
                resposta += nome + ", " ;
            }
            resposta = resposta.substring(0, resposta.length() - 2);
            resposta += ".";
        }
        resposta += "\n" +
                    "Saldo de Saude:" + getSaldoSaude() + "\n" +
                    "Saldo de Motivação:" + getSaldoMotivacao() + "\n"+
                    "Saldo de Dinheiro:" + getSaldoDinheiro() + "\n" +
                    "Saldo de Desempenho academico EXA:" + getSaldoDes_acad_m1() + "\n" +
                    "Saldo de Desempenho academico TEC" + getSaldoDes_acad_m2() + "\n" +
                    "Saldo de Desempenho academico ALG:" + getSaldoDes_acad_m3() + "\n";

        return resposta;

    }

    public String getTipo() {
        return tipo;
    }
}
