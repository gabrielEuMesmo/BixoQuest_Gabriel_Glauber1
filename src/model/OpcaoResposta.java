package model;

public class OpcaoResposta {
    private String textoPlayer;
    private int impactoRelacionamento;
    private int ganhoConhecimento;
    private int custoEnergia;
    private int saldoMotivacao;
    private double ganhoDinheiro;
    private int saldoSaude;
    private String reacaoDoNPC;

    //CONSTRUTOR TOTAL
    public OpcaoResposta(String textoPlayer, int impactoRelacionamento, int ganhoConhecimento,
                         int custoEnergia, int saldoMotivacao, double ganhoDinheiro,
                         int saldoSaude, String reacaoDoNPC) {
        this.textoPlayer    = textoPlayer;
        this.impactoRelacionamento = impactoRelacionamento;
        this.ganhoConhecimento     = ganhoConhecimento;
        this.custoEnergia          = custoEnergia;
        this.saldoMotivacao        = saldoMotivacao;
        this.ganhoDinheiro         = ganhoDinheiro;
        this.saldoSaude            = saldoSaude;
        this.reacaoDoNPC           = reacaoDoNPC;
    }

    //CONSTRUTOR SEM SALDO DE SAUDE (CASO O VISUAL NOVEL NÃO TENHA IMPACTOS DE SAUDE!
    //CONVERSAR COM GAYBRIEL SOBRE POSSIVEIS USOS OU NÃO USOS
    public OpcaoResposta(String textoPlayer, int impactoRelacionamento, int ganhoConhecimento,
                         int custoEnergia, int saldoMotivacao, double ganhoDinheiro,
                         String reacaoDoNPC) {
        this(textoPlayer, impactoRelacionamento, ganhoConhecimento,
                custoEnergia, saldoMotivacao, ganhoDinheiro, 0, reacaoDoNPC);
    }

    //CONVERTE O OPCAORESPOSTA PARA UM OBJETO ACAODIALOGO!
    public AcaoDialogo converterParaAcao(String nomeNPC, String areaConhecimento) {
        int exa = 0;
        int tec = 0;
        int alg = 0;

        if (areaConhecimento != null) {
            switch (areaConhecimento.toUpperCase()) {
                case "EXA":
                    exa = ganhoConhecimento; break;
                case "TEC":
                    tec = ganhoConhecimento; break;
                case "ALG":
                    alg = ganhoConhecimento; break;
            }
        }

        return new AcaoDialogo(
                "FALOU",
                nomeNPC,
                -this.custoEnergia,   //CUSTO DE ENRGIA FICA NEGATIVO AQUI AO TRANSFORMAR EM OBJETO CORRETO!
                this.saldoSaude,
                this.ganhoDinheiro,
                this.saldoMotivacao,
                exa, tec, alg,
                this.textoPlayer,
                this.reacaoDoNPC
        );
    }

    //GET DE TUDO!
    //GET nÂO È REDUNDANTE POIS ESSE É RELACIONADO AO USO DE UMA ACAO E NÂO aO VALOR COMPLETO!
    public String getTextoPlayer()          { return textoPlayer; }
    public int    getImpactoRelacionamento(){ return impactoRelacionamento; }
    public int    getGanhoConhecimento()    { return ganhoConhecimento; }
    public int    getCustoEnergia()         { return custoEnergia; }
    public int    getSaldoMotivacao()       { return saldoMotivacao; }
    public double getGanhoDinheiro()        { return ganhoDinheiro; }
    public int    getSaldoSaude()           { return saldoSaude; }
    public String getReacaoDoNPC()          { return reacaoDoNPC; }
}
