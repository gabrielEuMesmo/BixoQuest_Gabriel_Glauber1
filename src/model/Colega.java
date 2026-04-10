package model;

public class Colega extends Personagem{

    private int semestre;
    private boolean cargo;
    private String tipoCargo;
    private int relacionamento;
    private int conhecimento_EXA;
    private int conhecimento_TEC;
    private int conhecimento_ALG;
    private boolean conversou;

    public Colega(String nome, int semestre, boolean cargo, String tipoCargo, int conhecimento_EXA, int conhecimento_TEC, int conhecimento_ALG){

        super(nome);
        this.semestre = semestre;
        this.cargo = cargo;
        this.tipoCargo = tipoCargo;
        this.conhecimento_EXA = conhecimento_EXA;
        this.conhecimento_TEC = conhecimento_TEC;
        this.conhecimento_ALG = conhecimento_ALG;

    }

    @Override
    public String toString() {
        return getNome() + "\n"+
                "   Cargo: " + tipoCargo+ "\n"+
                "   Relacionamento: " + relacionamento + "\n"+
                "   Conhecimento EXA: " + conhecimento_EXA + "\n" +
                "   Conhecimento TEC: " + conhecimento_TEC + "\n" +
                "   Conhecimento ALG: " + conhecimento_ALG + "\n";
    }

    public boolean getConversou(){
        return conversou;
    }
    @Override
    public Acao conversar() {

        int bonus_EXA = 1;
        int bonus_TEC = 1;
        int bonus_ALG = 1;
        if(cargo){


            if(tipoCargo.equals("EXA")){
                bonus_EXA = 2;

            }

            if(tipoCargo.equals("TEC")){
                bonus_TEC = 2;

            }

            if(tipoCargo.equals("ALG")){
                bonus_ALG = 2;

            }
        }
        Acao retorno = new Acao("Conversou", 0, relacionamento, 0 ,
                conhecimento_EXA * bonus_EXA,conhecimento_TEC * bonus_TEC, conhecimento_ALG * bonus_ALG );

        retorno.addNome(getNome());
        conversou = true;
        relacionamento++;
        return retorno;

    }
    public void atualizar(){

        if (!conversou){
            relacionamento--;
        }
        conversou = false;
    }

    public void atualizarSemestre(){
        semestre++;
    }
}
