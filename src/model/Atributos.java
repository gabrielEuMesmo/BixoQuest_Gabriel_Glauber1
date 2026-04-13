package model;

// Agrupa os saldos acumulados por ações e menus.
public class Atributos {
    // Variação de saúde acumulada.
    private int saldoSaude;

    // Variação de motivação acumulada.
    private int saldoMotivacao;

    // Variação de dinheiro acumulada.
    private double saldoDinheiro;

    // Variação de desempenho na matéria 1.
    private int saldoDes_acad_m1;

    // Variação de desempenho na matéria 2.
    private int saldoDes_acad_m2;

    // Variação de desempenho na matéria 3.
    private int saldoDes_acad_m3;

    // Cria um conjunto de saldos com valores definidos.
    public Atributos(int saldoSaude, int saldoMotivacao, double saldoDinheiro, int saldoDes_acad_m1, int saldoDes_acad_m2, int saldoDes_acad_m3){

        this.saldoSaude = saldoSaude;
        this.saldoMotivacao = saldoMotivacao;
        this.saldoDinheiro = saldoDinheiro;
        this.saldoDes_acad_m1= saldoDes_acad_m1;
        this.saldoDes_acad_m2= saldoDes_acad_m2;
        this.saldoDes_acad_m3= saldoDes_acad_m3;

    }

    // Copia os saldos de outro objeto do mesmo tipo.
    public Atributos(Atributos outroAtributos){

            this.saldoSaude = outroAtributos.getSaldoSaude();
            this.saldoMotivacao =outroAtributos.getSaldoMotivacao();
            this.saldoDinheiro = outroAtributos.getSaldoDinheiro();
            this.saldoDes_acad_m1= outroAtributos.getSaldoDes_acad_m1();
            this.saldoDes_acad_m2= outroAtributos.getSaldoDes_acad_m2();
            this.saldoDes_acad_m3= outroAtributos.getSaldoDes_acad_m3();

    }

    // Cria um conjunto vazio de saldos.
    public Atributos(){}

    // Retorna o saldo acumulado de saúde.
    public int getSaldoSaude(){return saldoSaude; }

    // Retorna o saldo acumulado de motivação.
    public int getSaldoMotivacao(){return saldoMotivacao; }

    // Retorna o saldo acumulado de desempenho na matéria 1.
    public int getSaldoDes_acad_m1(){return saldoDes_acad_m1; }

    // Retorna o saldo acumulado de desempenho na matéria 2.
    public int getSaldoDes_acad_m2(){return saldoDes_acad_m2; }

    // Retorna o saldo acumulado de desempenho na matéria 3.
    public int getSaldoDes_acad_m3(){return saldoDes_acad_m3; }

    // Retorna o saldo acumulado de dinheiro.
    public double getSaldoDinheiro(){return saldoDinheiro; }

    // Define o saldo de saúde.
    public void setSaldoSaude(int valor){saldoSaude = valor; }

    // Define o saldo de motivação.
    public void setSaldoMotivacao(int valor){saldoMotivacao = valor; }

    // Define o saldo de dinheiro.
    public void setSaldoDinheiro(double valor){saldoDinheiro = valor; }

    // Define o saldo de desempenho da matéria 1.
    public void setSaldoDes_acad_m1(int valor){saldoDes_acad_m1= valor; }

    // Define o saldo de desempenho da matéria 2.
    public void setSaldoDes_acad_m2(int valor){saldoDes_acad_m2= valor; }

    // Define o saldo de desempenho da matéria 3.
    public void setSaldoDes_acad_m3(int valor){saldoDes_acad_m3= valor; }

    // Soma ao objeto atual todos os saldos vindos de outro conjunto.
    public void somaAtributos(Atributos atributos){
            this.saldoSaude +=   atributos.getSaldoSaude();
            this.saldoMotivacao += atributos.getSaldoMotivacao();
            this.saldoDinheiro +=  atributos.getSaldoDinheiro();
            this.saldoDes_acad_m1+= atributos.getSaldoDes_acad_m1();
            this.saldoDes_acad_m2+= atributos.getSaldoDes_acad_m2();
            this.saldoDes_acad_m3+= atributos.getSaldoDes_acad_m3();
    }
}
