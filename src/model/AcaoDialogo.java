package model;

public class AcaoDialogo extends Acao {
    private String textoPlayer;
    private String reacaoNPC;
    private int saldoEnergiaDia;

    //CONSTRUTOR
    public AcaoDialogo(String tipo, String personagem,
                       int saldoEnergiaDia, int saldoSaude, double saldoDinheiro, int saldoMotivacao,
                       int saldoDes_acad_EXA, int saldoDes_acad_TEC, int saldoDes_acad_ALG,
                       String textoPlayer, String reacaoNPC) {

        //COMO VAI SER A BASE USADA NO VISUAL NOVEL, ENVIA O PRONTO PARA A CLASSE MÃE
        super(tipo, saldoSaude,  saldoMotivacao,saldoDinheiro,
                saldoDes_acad_EXA, saldoDes_acad_TEC, saldoDes_acad_ALG);
        addNome(personagem);
        this.saldoEnergiaDia = saldoEnergiaDia;

        //O RESTO COLOCA NO ESPECIFICO (POLIMORFISMO UAAAAAAU)
        this.textoPlayer = textoPlayer;
        this.reacaoNPC   = reacaoNPC;
    }

    //GET ESPECIFICO DA CLASSE (O RESTO DA GET NO MÂE)
    public String getTextoPlayer() { return textoPlayer; }
    public String getReacaoNPC()   { return reacaoNPC; }
    public int getSaldoEnergiaDia() {return saldoEnergiaDia    ;}

    public void aplicarJogador(Jogador jogador) {
        jogador.somarEnergiaDia(saldoEnergiaDia);
        jogador.somarSaude(saldoSaude);
        jogador.somarMotivacao(saldoMotivacao);
        jogador.somarDinheiro(saldoDinheiro);
        jogador.somarDesempenhoAcademicoEXA(saldoDes_acad_m1);
        jogador.somarDesempenhoAcademicoTEC(saldoDes_acad_m2);
        jogador.somarDesempenhoAcademicoALG(saldoDes_acad_m3);
    }

    //TOSTRING
    public String toString() {
        //REUTILIZAR O TOSTRINGO DO PAI e ADICIONA O CASO ESPECIFICO DO VISUAL NOVEL!
        String base = super.toString();
        return base
                + "\n   Voce disse : \"" + textoPlayer + "\""
                + "\n   " + getPersonagem() + " respondeu: \"" + reacaoNPC + "\"";
    }
}