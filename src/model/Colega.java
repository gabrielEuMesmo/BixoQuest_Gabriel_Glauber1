package model;

// Representa um colega com quem o jogador pode interagir.
public class Colega extends Personagem{

    // Semestre atual do colega.
    private int semestre;

    // Indica se o colega ocupa algum cargo acadêmico.
    private final boolean cargo;

    // Tipo de cargo ocupado pelo colega.
    private final String tipoCargo;

    // Nível de relacionamento com o jogador.
    private int relacionamento;

    // Conhecimento do colega em EXA.
    private final int conhecimento_EXA;

    // Conhecimento do colega em TEC.
    private final int conhecimento_TEC;

    // Conhecimento do colega em ALG.
    private  final int conhecimento_ALG;

    // Marca se o colega conversou no turno atual.
    private boolean conversou;

    // Inicializa um colega com seus dados fixos e estado social.
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
    // Exibe um resumo textual do colega.
    public String toString() {
        return getNome() + "\n"+
                "   Semestre: " + semestre + "\n" +
                "   Cargo: " + tipoCargo+ "\n"+
                "   Relacionamento: " + relacionamento + "\n"+
                "   Conhecimento EXA: " + conhecimento_EXA + "\n" +
                "   Conhecimento TEC: " + conhecimento_TEC + "\n" +
                "   Conhecimento ALG: " + conhecimento_ALG + "\n";
    }

    // Informa se o colega já conversou no turno atual.
    public boolean getConversou(){
        return conversou;
    }

    // Retorna o nível de relacionamento atual.
    public int getRelacionamento() {return relacionamento;}

    @Override
    // Gera a ação de conversa considerando bônus de cargo.
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
        if(relacionamento <5){relacionamento++;}
        return retorno;

    }

    // Atualiza o estado social do colega ao fim do turno.
    public void atualizar(){

        if (!conversou){
            relacionamento--;
        }
        conversou = false;
    }

    // Avança o semestre atual do colega.
    public void atualizarSemestre(){
        semestre++;
    }
}
