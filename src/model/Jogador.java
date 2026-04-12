package model;

import java.util.ArrayList;
import java.util.Arrays;

// Representa o personagem principal controlado pelo usuário.
public class Jogador extends Entidade{

    // Saúde atual do jogador.
    private int saude;

    // Energia disponível no turno.
    private int energia;

    // Porcentagem de conclusão do curso.
    private double porcentagem_curso = 0;

    // Motivação atual do jogador.
    private int motivacao;

    // Dinheiro disponível.
    private double dinheiro;

    // Desempenho acadêmico na matéria 1.
    private int desempenho_academico_m1 = 0;

    // Desempenho acadêmico na matéria 2.
    private int desempenho_academico_m2 = 0;

    // Desempenho acadêmico na matéria 3.
    private int desempenho_academico_m3 = 0;

    // Página atual do menu principal.
    private String pag = "";

    // Menu social do jogador.
    Social social = new Social(this);

    // Menu acadêmico do jogador.
    public Academico academico = new Academico(this);

    // Menu de saúde do jogador.
    Saude saudeClasse = new Saude(this);

    // Menu financeiro do jogador.
    Financeiro financeiro = new Financeiro(this);

    ArrayList<Acao> acoesFeitas = new ArrayList<>();

    // Inicializa o jogador com os atributos de começo de jogo.
    public Jogador(String nomeNovo, int saudeInicio, int energiaInicio, int motivacaoInicio,
                   double dinheiroInicio){

        super(nomeNovo);
        this.saude = saudeInicio;
        this.energia = energiaInicio;
        this.motivacao = motivacaoInicio;
        this.dinheiro = dinheiroInicio;

    }

    // Retorna a energia atual.
    public int getEnergia (){

        return energia;

    }

    // Retorna a saúde atual.
    public int getSaude (){

        return saude;

    }

    // Retorna a porcentagem atual de conclusão do curso.
    public double getPorcentagem (){

        return porcentagem_curso;

    }

    // Retorna a motivação atual.
    public int getMotivacao (){

        return motivacao;

    }

    // Retorna o dinheiro atual.
    public double getDinheiro (){

        return dinheiro;

    }

    // Retorna o desempenho da matéria 1.
    public int getDesempenho_m1 (){

        return desempenho_academico_m1;

    }

    // Retorna o desempenho da matéria 2.
    public int getDesempenho_m2 (){

        return desempenho_academico_m2;

    }

    // Retorna o desempenho da matéria 3.
    public int getDesempenho_m3 (){

        return desempenho_academico_m3;

    }

    // Gera um resumo textual do estado do jogador.
    public String toString(){

        return ( "Nome:" + getNome() + "\n" +
                "Energia: " + energia + "\n" +
                "Saude: " + saude + "\n" +
                "Porcentagem de completude do curso: " + porcentagem_curso + "\n" +
                "Motivacao: " + motivacao + "\n" +
                "Dinheiro: " + dinheiro + "\n" +
                "Desempenho Materia 1: " + desempenho_academico_m1 + "\n" +
                "Desempenho Materia 2: " + desempenho_academico_m2 + "\n" +
                "Desempenho Materia 3: " + desempenho_academico_m3 + "\n");
    }

    // Soma progresso à matéria 1.
    public void somarDes_acad_m1(int valor){
        desempenho_academico_m1+= valor;
    }

    // Soma progresso à matéria 2.
    public void somarDes_acad_m2(int valor){
        desempenho_academico_m2+= valor;
    }

    // Soma progresso à matéria 3.
    public void somarDes_acad_m3(int valor){
        desempenho_academico_m3+= valor;
    }

    // Adiciona dinheiro ao saldo atual.
    public void somarDinheiro(double valor){
        this.dinheiro += valor;
    }

    // Remove dinheiro do saldo atual.
    public void subtrairDinheiro(double valor){
        this.dinheiro -= valor;
    }

    // Zera completamente o dinheiro do jogador.
    public void zerarDinheiro(){
        this.dinheiro = 0;
    }

    // Adiciona energia ao jogador.
    public void somarEnergia(int valor){
        this.energia += valor;
    }

    // Remove energia do jogador.
    public void subtrairEnergia(int valor) {
        this.energia -= valor;
    }

    // Restaura a energia ao valor máximo usado no jogo.
    public void maximizarEnergia() {
        this.energia = 10;
    }

    // Adiciona motivação ao jogador.
    public void somarMotivacao(int valor) {
        this.motivacao += valor;
    }

    // Remove motivação do jogador.
    public void subtrairMotivacao(int valor) {
        this.motivacao -= valor;
    }

    // Restaura a motivação ao valor máximo usado no jogo.
    public void maximizarMotivacao() {
        this.motivacao = 10;
    }

    // Remove saúde do jogador.
    public void subtrairSaude(int valor) {
        this.saude -= valor;
    }

    // Adiciona saúde ao jogador.
    public void somarSaude(int valor) {
        this.saude += valor;
    }

    // Restaura a saúde ao valor máximo usado no jogo.
    public void maximizarSaude() {
        this.saude = 10;
    }

    public void atualizar(){
        for (Acao acao : social.atualizar()){
            acoesFeitas.add(acao);
        }
        for (Acao acao : saudeClasse.atualizar()){
            acoesFeitas.add(acao);
        }
        for (Acao acao : financeiro.atualizar()){
            acoesFeitas.add(acao);
        }
        for (Acao acao : academico.atualizar()){
            acoesFeitas.add(acao);
        }
    }

    public ArrayList<Acao> getAcoesFeitas() {
        return acoesFeitas;
    }

    public void limparAcoes(){

        acoesFeitas.clear();
    }

    // Retorna a opção atualmente visível para o jogador.
    public Opcao mostrarOpcao(){
        social.atualizarOpcoes();
        if(pag.equals("") ){
           return new Opcao("Opcoes", new ArrayList<>(Arrays.asList("Social", "Academico", "Financeiro", "Saude")));

        }else if(pag.charAt(0) == '1' ){

            return social.mostrar();

        }else if(pag.charAt(0) == '2' ){

            return academico.mostrar();

        }else if(pag.charAt(0) == '3' ){

            return financeiro.mostrar();
        }
        return saudeClasse.mostrar();

    }

    public Social getSocial() {
        return social;
    }

    public String getPag() {
        return pag;
    }

    // Encaminha a escolha do usuário para o menu correspondente.
    public boolean escolherOpcao(int escolha){
        if (escolha>0 && escolha<= 5 && (pag.equals("") || pag.equals("1") || pag.equals("2") || pag.equals("3") || pag.equals("4"))){pag += escolha;}
        if(pag.equals("") ){
            return true;

        }else if(pag.charAt(0) == '1' && pag.length() ==2){
            if(!social.escolher(escolha)) {
                pag = "";
            }
        }else if(pag.charAt(0) == '2' && pag.length() ==2){

            if(!academico.escolher(escolha)) {
                pag = "";
            }

        }else if(pag.charAt(0) == '3' && pag.length() ==2){

            if(!financeiro.escolher(escolha)) {
                pag = "";
            }
        }else if(pag.charAt(0) == '4' && pag.length() ==2) {
            if(!saudeClasse.escolher(escolha)) {
                pag = "";
            }
        }
        return true;
    }

}
