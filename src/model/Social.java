package model;

import java.util.ArrayList;

public class Social extends Menu{

    private ArrayList<Colega> colegas;
    private int pagina;

    public Social(){
        colegas = new ArrayList<Colega>();
    }

    public boolean escolher(int escolha){

    }
    public Opcao mostrar(){
        if(pagina == 0){

            return new Opcao();
        }

    }
    public void addColega(){
        colegas.add(CriarRandom.criarColega());

    }

}
