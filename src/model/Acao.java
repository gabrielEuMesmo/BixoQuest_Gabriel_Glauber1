package model;

public class Acao {
    private String tipo;
    private String personagem;
    private int    saldoEnergia;
    private int    saldoSaude;
    private double saldoDinheiro;
    private int    saldoMotivacao;
    private int    saldoDes_acad_EXA;
    private int    saldoDes_acad_TEC;
    private int    saldoDes_acad_ALG;

    //CONSTRUTOR
    public Acao(String tipo, String personagem,
                int saldoEnergia, int saldoSaude, double saldoDinheiro, int saldoMotivacao,
                int saldoDes_acad_EXA, int saldoDes_acad_TEC, int saldoDes_acad_ALG) {
        this.tipo             = tipo;
        this.personagem       = personagem;
        this.saldoEnergia     = saldoEnergia;
        this.saldoSaude       = saldoSaude;
        this.saldoDinheiro    = saldoDinheiro;
        this.saldoMotivacao   = saldoMotivacao;
        this.saldoDes_acad_EXA = saldoDes_acad_EXA;
        this.saldoDes_acad_TEC = saldoDes_acad_TEC;
        this.saldoDes_acad_ALG = saldoDes_acad_ALG;
    }

    //MOTODO PARA APLICAR OS IMPACTOS DESTA AÇÃO DIRETAMENTE NO JOGADOR, UTILIZANDO OS METODOS DE SOMA DO JOGADOR!
    public void aplicarJogador(Jogador j) {
        j.somarEnergia(saldoEnergia);
        j.somarSaude(saldoSaude);
        j.somarDinheiro(saldoDinheiro);
        j.somarMotivacao(saldoMotivacao);
        j.somarDesempenhoEXA(saldoDes_acad_EXA);
        j.somarDesempenhoTEC(saldoDes_acad_TEC);
        j.somarDesempenhoALG(saldoDes_acad_ALG);
    }

    //TODOS OS GETS DE TODOS OS ATRIBUTOS (STATUS)
    public String getTipo()                  { return tipo; }
    public String getPersonagem()            { return personagem; }
    public int    getSaldoEnergia()          { return saldoEnergia; }
    public int    getSaldoSaude()            { return saldoSaude; }
    public double getSaldoDinheiro()         { return saldoDinheiro; }
    public int    getSaldoMotivacao()        { return saldoMotivacao; }
    public int    getSaldoDes_acad_EXA()     { return saldoDes_acad_EXA; }
    public int    getSaldoDes_acad_TEC()     { return saldoDes_acad_TEC; }
    public int    getSaldoDes_acad_ALG()     { return saldoDes_acad_ALG; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(tipo).append("] ");

        if (personagem != null && !personagem.isEmpty()) {
            sb.append("Com ").append(personagem).append(" | ");
        } else {
            sb.append("Acao Direta | ");
        }

        //EXEMPLOS DE DIALOGO:

        // [Escolha] Com NPC1 | Impactos: Energia(-10) Saude(+5) Dinheiro(0.0) Motivacao(+20) Exatas(0)
        // [Evento] Acao Direta | Impactos: Energia(-20) Saude(-10) Dinheiro(+100.0) Motivacao(-15) Exatas(+5)

        //NÃO PADRONIZAR ESSE "tipo" COMO falou ou falar, EXISTE O CASO DOS EVENTOS OBRIGATORIOS!
        sb.append("Impactos: ");
        if (saldoEnergia    != 0) sb.append("Energia(").append(saldoEnergia).append(") ");
        if (saldoSaude      != 0) sb.append("Saude(").append(saldoSaude).append(") ");
        if (saldoDinheiro   != 0) sb.append("Dinheiro(").append(String.format("%.1f", saldoDinheiro)).append(") ");
        if (saldoMotivacao  != 0) sb.append("Motivacao(").append(saldoMotivacao).append(") ");
        if (saldoDes_acad_EXA != 0) sb.append("Exatas(").append(saldoDes_acad_EXA).append(") ");
        if (saldoDes_acad_TEC != 0) sb.append("Tecnicas(").append(saldoDes_acad_TEC).append(") ");
        if (saldoDes_acad_ALG != 0) sb.append("Algoritmos(").append(saldoDes_acad_ALG).append(") ");

        return sb.toString().trim();
    }
}
