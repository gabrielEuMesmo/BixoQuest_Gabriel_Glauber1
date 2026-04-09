package model;

public abstract class Menu {
    private int saldoSaude;
    private int saldoMotivacao;
    private double saldoDinheiro;
    private int saldoDes_acad_m1;
    private int saldoDes_acad_m2;
    private int saldoDes_acad_m3;
    private int pag;


    public abstract boolean escolher(int escolha);
    public abstract Opcao mostrar(int escolha);

    public void atualizar(Jogador jogador){
        jogador.somarSaude(saldoSaude);
        jogador.somarMotivacao(saldoMotivacao);
        jogador.somarDinheiro(saldoDinheiro);
        jogador.somarDes_acad_m1(saldoDes_acad_m1);
        jogador.somarDes_acad_m2(saldoDes_acad_m2);
        jogador.somarDes_acad_m3(saldoDes_acad_m3);

    }

}
