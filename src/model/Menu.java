package model;

public abstract class Menu extends Atributos{

    private int pag;

    public Menu(){
        super();
    }
    public abstract boolean escolher(int escolha);

    public abstract Opcao mostrar();

    public void atualizar(Jogador jogador){
        jogador.somarSaude(getSaldoSaude());
        jogador.somarMotivacao(getSaldoMotivacao());
        jogador.somarDinheiro(getSaldoDinheiro());
        jogador.somarDes_acad_m1(getSaldoDes_acad_m1());
        jogador.somarDes_acad_m2(getSaldoDes_acad_m2());
        jogador.somarDes_acad_m3(getSaldoDes_acad_m3());

    }


}
