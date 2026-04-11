package model;

public class Atributos {

    public int saldoSaude;
    public int saldoMotivacao;
    public double saldoDinheiro;
    public int saldoDes_acad_m1;
    public int saldoDes_acad_m2;
    public int saldoDes_acad_m3;

    public Atributos(int saldoSaude, int saldoMotivacao, double saldoDinheiro, int saldoDes_acad_m1, int saldoDes_acad_m2, int saldoDes_acad_m3){

        this.saldoSaude = saldoSaude;
        this.saldoMotivacao = saldoMotivacao;
        this.saldoDinheiro = saldoDinheiro;
        this.saldoDes_acad_m1= saldoDes_acad_m1;
        this.saldoDes_acad_m2= saldoDes_acad_m2;
        this.saldoDes_acad_m3= saldoDes_acad_m3;

    }

    public Atributos(Atributos outroAtributos){

        this.saldoSaude = outroAtributos.getSaldoSaude();
        this.saldoMotivacao =outroAtributos.getSaldoMotivacao();
        this.saldoDinheiro = outroAtributos.getSaldoDinheiro();
        this.saldoDes_acad_m1= outroAtributos.getSaldoDes_acad_m1();
        this.saldoDes_acad_m2= outroAtributos.getSaldoDes_acad_m2();
        this.saldoDes_acad_m3= outroAtributos.getSaldoDes_acad_m3();

    }

    public Atributos(){}

    public int getSaldoSaude(){return saldoSaude; }

    public int getSaldoMotivacao(){return saldoMotivacao; }

    public int getSaldoDes_acad_m1(){return saldoDes_acad_m1; }

    public int getSaldoDes_acad_m2(){return saldoDes_acad_m2; }

    public int getSaldoDes_acad_m3(){return saldoDes_acad_m3; }

    public double getSaldoDinheiro(){return saldoDinheiro; }

    public void setSaldoSaude(int valor){saldoSaude = valor; }

    public void setSaldoMotivacao(int valor){saldoMotivacao = valor; }

    public void setSaldoDinheiro(double valor){saldoDinheiro = valor; }

    public void setSaldoDes_acad_m1(int valor){saldoDes_acad_m1= valor; }

    public void setSaldoDes_acad_m2(int valor){saldoDes_acad_m2= valor; }

    public void setSaldoDes_acad_m3(int valor){saldoDes_acad_m3= valor; }

    public void somaAtributos(Atributos atributos){
        this.saldoSaude +=   atributos.getSaldoSaude();
        this.saldoMotivacao += atributos.getSaldoMotivacao();
        this.saldoDinheiro +=  atributos.getSaldoDinheiro();
        this.saldoDes_acad_m1+= atributos.getSaldoDes_acad_m1();
        this.saldoDes_acad_m2+= atributos.getSaldoDes_acad_m2();
        this.saldoDes_acad_m3+= atributos.getSaldoDes_acad_m3();
    }
}

