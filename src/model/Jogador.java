package model;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
    public String nome;
    public int saude;
    public int energia; //ESSA ENERGIA É SEMANAL
    public int motivacao;
    public double dinheiro;
    public int desempenho_academico_EXA;
    public int desempenho_academico_TEC;
    public int desempenho_academico_ALG;
    public double porcentagem_curso;
    public int energiaDia; //ESSA ENERGIA É DIARIA

    //FUTURAMENTE SEPARAR CADA DESSES ATRIBUTOS PARA COMENTAR ESPECIFICANDO CADA UM

    //HISTORICO DE AÇÕES DO TURNO, PARA EXIBIR AO FIM DE CADA TURNO O QUE O JOGADOR FEZ E SEU IMPACTO!
    private List<Acao> acoesDoTurno;

    //CONSTANTES PARA GARANTIR TRATAMENTO CORRETO DOS STATUS DO JOGADOR!
    private static final int MAX_STATUS   = 100;
    private static final int MIN_STATUS   = 0;
    private static final int ENERGIA_BASE = 70;

    //CONSTRUTOR
    public Jogador(String nome, int saude, int energiaInicio, int motivacao, double dinheiro) {
        this.nome                    = nome;
        this.saude                   = clamp(saude, MIN_STATUS, MAX_STATUS);
        this.energia                 = clamp(energiaInicio, MIN_STATUS, ENERGIA_BASE);
        this.motivacao               = clamp(motivacao, MIN_STATUS, MAX_STATUS);
        this.dinheiro                = dinheiro;
        this.desempenho_academico_EXA = 0;
        this.desempenho_academico_TEC = 0;
        this.desempenho_academico_ALG = 0;
        this.porcentagem_curso       = 0.0;
        this.energiaDia                = clamp(energiaInicio, MIN_STATUS, ENERGIA_BASE);
        this.acoesDoTurno            = new ArrayList<>();
    }

    //UTILITARIO PARA ADIANTAR E AGILIZAR PARA VALIDAÇÂO!
    private int clamp(int valor, int min, int max) {
        if (valor < min) return min;
        if (valor > max) return max;
        return valor;
    }

    //ENERGIA

    public int getEnergia() { return energia; }
    public int getEnergiaDia() { return energiaDia; }
    public void somarEnergia(int valor) {
        this.energia = clamp(this.energia + valor, MIN_STATUS, ENERGIA_BASE);
    }
    public void somarEnergiaDia(int valor) { this.energiaDia = clamp(this.energiaDia + valor, MIN_STATUS, ENERGIA_BASE); }
    public void subtrairEnergia(int valor) {
        this.energia = clamp(this.energia - valor, MIN_STATUS, ENERGIA_BASE);
    }
    public void subtrairEnergiaDia(int valor) { this.energiaDia = clamp(this.energiaDia - valor, MIN_STATUS, ENERGIA_BASE); }
    //RESTAURAR PARA VIRADA DE TURNO!
    public void restaurarEnergia() {
        this.energia = ENERGIA_BASE;
    }
    public void restaurarEnergiaDia() { this.energiaDia = ENERGIA_BASE;}

    //PARA VERIFICAÇÃO DE CASO CONTENHA ENERGIA (SE NA VERIFICAÇAO DE AÇÃO O JOGADOR NÃO TENHA ENERGIA, A AÇÃO NÃO VAI
    //SER REALIZADA E VAI EXIBIR UMA MENSAGEM DE FALTA DE ENERGIA!)
    public boolean temEnergia() {
        return this.energia > 0;
    }
    public boolean temEnergiaDia() { return this.energiaDia > 0; }

    //SAUDE
    public int getSaude() { return saude; }
    public void somarSaude(int valor) {
        this.saude = clamp(this.saude + valor, MIN_STATUS, MAX_STATUS);
    }
    public void subtrairSaude(int valor) {
        this.saude = clamp(this.saude - valor, MIN_STATUS, MAX_STATUS);
    }
    public void maximizarSaude() {
        this.saude = MAX_STATUS;
    }

    //MOTIVACAO
    public int getMotivacao() { return motivacao; }
    public void somarMotivacao(int valor) {
        this.motivacao = clamp(this.motivacao + valor, MIN_STATUS, MAX_STATUS);
    }
    public void subtrairMotivacao(int valor) {
        this.motivacao = clamp(this.motivacao - valor, MIN_STATUS, MAX_STATUS);
    }
    public void maximizarMotivacao() {
        this.motivacao = MAX_STATUS;
    }

    //DINHEIRO
    public double getDinheiro() { return dinheiro; }
    public void somarDinheiro(double valor) {
        this.dinheiro += valor;
    }
    public void zerarDinheiro() {
        this.dinheiro = 0;
    }
    public void subtrairDinheiro(double valor) {
        // Nao deixa o saldo ficar negativo
        if (this.dinheiro >= valor) {
            this.dinheiro -= valor;
        } else {
            this.dinheiro = 0;
        }
    }

    // DESEMPENHO ACADEMICO
    //GETs
    public int getDesempenho_EXA() { return desempenho_academico_EXA; }
    public int somarDesempenhoAcademicoEXA(int valor) {
        if (valor > 0) this.desempenho_academico_EXA += valor;
        return this.desempenho_academico_EXA;
    }
    public int getDesempenho_TEC() { return desempenho_academico_TEC; }
    public int somarDesempenhoAcademicoTEC(int valor) {
        if (valor > 0) this.desempenho_academico_TEC += valor;
        return this.desempenho_academico_TEC;
    }
    public int getDesempenho_ALG() { return desempenho_academico_ALG; }
    public int somarDesempenhoAcademicoALG(int valor) {
        if (valor > 0) this.desempenho_academico_ALG += valor;
        return this.desempenho_academico_ALG;
    }

    //INCREMENTOS ou SOMAS
    public void somarDesempenhoEXA(int valor) {
        if (valor > 0) this.desempenho_academico_EXA += valor;
    }
    public void somarDesempenhoTEC(int valor) {
        if (valor > 0) this.desempenho_academico_TEC += valor;
    }
    public void somarDesempenhoALG(int valor) {
        if (valor > 0) this.desempenho_academico_ALG += valor;
    }

    //PORCENTAGEM DO CURSO
    public double getPorcentagem() { return porcentagem_curso; }

    //AVANÇA PORCENTAGEM DO CURSO NA MEDIDA QUE nÂO ULTRAPASSE 100%
    public void avancarPorcentagem(double valor) {
        this.porcentagem_curso = Math.min(100.0, this.porcentagem_curso + valor);
    }

    //VALIDAÇÃO PARA CASO O JOGADOR ALCANCE 100% DE CONCLUSÃO DO CURSO!
    public boolean isFormado() {
        return this.porcentagem_curso >= 100.0;
    }

    //HISTORICO DO TURNO
    public void registrarAcao(Acao a) {
        acoesDoTurno.add(a);
    }
    public List<Acao> getAcoesDoTurno() {
        return new ArrayList<>(acoesDoTurno);
    }
    public void limparAcoesDoTurno() {
        acoesDoTurno.clear();
    }

    //CONDICOES DE FIM DE JOGO
    public boolean isGameOver() {
        return saude <= 0 || motivacao <= 0 || dinheiro <= 0;
    }

    //A CLASSE QUE INICIAR VAI VERIFICAR SEMPRE ESSES STATUS, ASSIM QUE "MORRER" VAI EXIBIR O MOTIVO DO GAME OVER!
    public String motivoGameOver() {
        if (saude     <= 0) return "Sua saude chegou a zero. Voce foi internado e nao conseguiu continuar.";
        if (motivacao <= 0) return "Sua motivacao chegou a zero. Voce desistiu do curso.";
        if (dinheiro  <= 0) return "Seu dinheiro acabou. Voce nao conseguiu se manter na cidade.";
        return "";
    }

    //GET DO NOME DO JOGADOR
    public String getNome() { return nome; }

    //TOSTRING DO JOGADOR
    @Override
    public String toString() {
        return  "Nome       : " + nome + "\n" +
                "Energia    : " + energia + "/" + ENERGIA_BASE + "\n" +
                "EnergiaDia : " + energiaDia + "/" + ENERGIA_BASE + "\n" +
                "Saude      : " + saude + "/100\n" +
                "Motivacao  : " + motivacao + "/100\n" +
                "Dinheiro   : R$ " + String.format("%.2f", dinheiro) + "\n" +
                "Conclusao  : " + String.format("%.1f", porcentagem_curso) + "%\n" +
                "Exatas     : " + desempenho_academico_EXA + " pts\n" +
                "Tecnicas   : " + desempenho_academico_TEC + " pts\n" +
                "Algoritmos : " + desempenho_academico_ALG + " pts";
    }
}
