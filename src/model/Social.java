package model;

import java.util.ArrayList;

public class Social extends Menu{

    private ArrayList<Colega> colegas;

    public Social(Jogador jogador){

        super(jogador);
        colegas = new ArrayList<>();
    }

    @Override
    public boolean realizar(String pag) {
        if(pag.length()> 3){
            if(pag.charAt(0) == '1' && pag.charAt(1) == '1' && Character.getNumericValue(pag.charAt(2)) < colegas.size()){
                    addAcao(colegas.get(Character.getNumericValue(pag.charAt(2))).conversar());
                    return true;
            }
        }
        return false;
    }

    public void addColega(){
        colegas.add(CriarRandom.criarColega());

    }

}
