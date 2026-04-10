package model;

public class Jogador extends Entidade{

    private int saude;
    private int energia;
    private double porcentagem_curso = 0;
    private int motivacao;
    private double dinheiro;
    private int desempenho_academico_m1 = 0;
    private int desempenho_academico_m2 = 0;
    private int desempenho_academico_m3 = 0;

    //Construtor
    public Jogador(String nomeNovo, int saudeInicio, int energiaInicio, int motivacaoInicio,
                   double dinheiroInicio){

        this.setNome(nomeNovo);
        this.saude = saudeInicio;
        this.energia = energiaInicio;
        this.motivacao = motivacaoInicio;
        this.dinheiro = dinheiroInicio;

    }

    public int getEnergia (){

        return energia;

    }
    public int getSaude (){

        return saude;

    }

    public double getPorcentagem (){

        return porcentagem_curso;

    }
    public int getMotivacao (){

        return motivacao;

    }
    public double getDinheiro (){

        return dinheiro;

    }
    public int getDesempenho_m1 (){

        return desempenho_academico_m1;

    }
    public int getDesempenho_m2 (){

        return desempenho_academico_m2;

    }
    public int getDesempenho_m3 (){

        return desempenho_academico_m3;

    }
    public String toString(){

        return ( "Nome:" + getNome() + "\n" +
                "Energia: " + energia + "\n" +
                "Saude: " + saude + "\n" +
                "Porcentagem de completude do curso: " + porcentagem_curso + "\n" +
                "Motivação: " + motivacao + "\n" +
                "Dinheiro: " + dinheiro + "\n" +
                "Desempenho Matéria 1: " + desempenho_academico_m1 + "\n" +
                "Desempenho Matéria 2: " + desempenho_academico_m2 + "\n" +
                "Desempenho Matéria 3: " + desempenho_academico_m3 + "\n");
    }

    public void somarDes_acad_m1(int valor){
        desempenho_academico_m1+= valor;
    }

    public void somarDes_acad_m2(int valor){
        desempenho_academico_m2+= valor;
    }

    public void somarDes_acad_m3(int valor){
        desempenho_academico_m3+= valor;
    }

    public void somarDinheiro(double valor){
        this.dinheiro += valor;
    }

    public void subtrairDinheiro(double valor){
        this.dinheiro -= valor;
    }

    public void zerarDinheiro(){
        this.dinheiro = 0;
    }
    //
    public void somarEnergia(int valor){
        this.energia += valor;
    }

    public void subtrairEnergia(int valor) {
        this.energia -= valor;
    }

    public void maximizarEnergia() {
        this.energia = 10;
    }
    //
    public void somarMotivacao(int valor) {
        this.motivacao += valor;
    }

    public void subtrairMotivacao(int valor) {
        this.motivacao -= valor;
    }

    public void maximizarMotivacao() {
        this.motivacao = 10;
    }

    public void subtrairSaude(int valor) {
        this.saude -= valor;
    }

    public void somarSaude(int valor) {
        this.saude += valor;
    }

    public void maximizarSaude() {
        this.saude = 10;
    }

    public Opcao mostrarOpcao(){


    }

    public boolean escolherOpcao(int escolha){



    }

}


