package model;

public class AcaoDialogo extends Acao {

    private String textoPlayer;
    private String reacaoNPC;

    //CONSTRUTOR
    public AcaoDialogo(String tipo, String personagem,
                       int saldoEnergia, int saldoSaude, double saldoDinheiro, int saldoMotivacao,
                       int saldoDes_acad_EXA, int saldoDes_acad_TEC, int saldoDes_acad_ALG,
                       String textoPlayer, String reacaoNPC) {

        //COMO VAI SER A BASE USADA NO VISUAL NOVEL, ENVIA O PRONTO PARA A CLASSE MÃE
        super(tipo, personagem,
                saldoEnergia, saldoSaude, saldoDinheiro, saldoMotivacao,
                saldoDes_acad_EXA, saldoDes_acad_TEC, saldoDes_acad_ALG);

        //O RESTO COLOCA NO ESPECIFICO (POLIMORFISMO UAAAAAAU)
        this.textoPlayer = textoPlayer;
        this.reacaoNPC   = reacaoNPC;
    }

    //GET ESPECIFICO DA CLASSE (O RESTO DA GET NO MÂE)
    public String getTextoPlayer() { return textoPlayer; }
    public String getReacaoNPC()   { return reacaoNPC; }

    //TOSTRING
    @Override
    public String toString() {
        //REUTILIZAR O TOSTRINGO DO PAI e ADICIONA O CASO ESPECIFICO DO VISUAL NOVEL!
        String base = super.toString();
        return base
                + "\n   Voce disse : \"" + textoPlayer + "\""
                + "\n   " + getPersonagem() + " respondeu: \"" + reacaoNPC + "\"";
    }
}
