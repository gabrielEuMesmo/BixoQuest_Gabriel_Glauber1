package model;

import java.util.ArrayList;
import java.util.List;

// Representa uma ação executada no jogo e os saldos gerados por ela.
public class Acao extends Atributos {
    // Descreve o tipo da ação executada.
    private final String tipo;

    // Guarda os participantes vinculados à ação, quando houver.
    private final List<String> nomes;

    // Cria uma nova ação com seus respectivos impactos.
    public Acao(String novoTipo, int saldoSaude, int saldoMotivacao, double saldoDinheiro, int saldoDes_acad_m1, int saldoDes_acad_m2, int saldoDes_acad_m3){
        super(saldoSaude, saldoMotivacao, saldoDinheiro, saldoDes_acad_m1, saldoDes_acad_m2, saldoDes_acad_m3);
        tipo = novoTipo;
        nomes = new ArrayList<>();
    }

    // Cria uma cópia da ação para preservar seus dados originais.
    public Acao(Acao outraAcao){
        super(outraAcao);
        tipo = outraAcao.getTipo();
        nomes = new ArrayList<>(outraAcao.nomes);
    }

    // Adiciona um participante à descrição da ação.
    public void addNome(String nome){
        nomes.add(nome);
    }

    @Override
    // Monta uma representação textual da ação com participantes e saldos.
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
                    "Saldo de Motivacao:" + getSaldoMotivacao() + "\n"+
                    "Saldo de Dinheiro:" + getSaldoDinheiro() + "\n" +
                    "Saldo de Desempenho academico EXA:" + getSaldoDes_acad_m1() + "\n" +
                    "Saldo de Desempenho academico TEC" + getSaldoDes_acad_m2() + "\n" +
                    "Saldo de Desempenho academico ALG:" + getSaldoDes_acad_m3() + "\n";

        return resposta;

    }

    // Retorna o tipo textual da ação.
    public String getTipo() {
        return tipo;
    }
}
