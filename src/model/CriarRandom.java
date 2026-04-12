package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class CriarRandom {

    private static final String[] nomesColegas = {"Gbriel"};
    private static final String[] cargosColegas = {"EXA", "TEC", "ALG"};


    private static final Random random = new Random();

    public static Colega criarColega(){
        boolean cargo = random.nextBoolean();
        String cargoTipo = "";
        if(cargo){
            cargoTipo = cargosColegas[random.nextInt(2)];
        }
        return new Colega(nomesColegas[random.nextInt(nomesColegas.length)], random.nextInt(1,10), cargo, cargoTipo, random.nextInt(10), random.nextInt(10), random.nextInt(10));
    }

    public static HashMap<String,Opcao> opcoesSocial(){

        HashMap<String,Opcao> retorno = new HashMap<>();

        ArrayList<String> opcoes = new ArrayList<>(Arrays.asList("Conversar", "Sair", "Voltar"));
        Opcao opcao = new Opcao("Social", opcoes);
        retorno.put("",opcao);

        opcoes.clear();
        opcoes.add("Colegas");

        opcao = new Opcao("Conversar", opcoes);
        retorno.put("1",opcao);

        opcoes.clear();
        opcoes.add("Ir para calourada");
        opcao = new Opcao("Sair", opcoes);
        retorno.put("2",opcao);

        return retorno;
    }

    public static HashMap<String,ArrayList<Acao>> opcoesAcoesVariaveisSocial(){
        HashMap<String, ArrayList<Acao>> retorno = new HashMap<>();
        ArrayList<Acao> acoes = new ArrayList<>(){{
            add(new Acao("Saiu para calourada", -5, 20, -30, 0, 0, 0));


        }};

        retorno.put("2",acoes);
        return retorno;
    }
    public static HashMap<String,ArrayList<Acao>> opcoesAcoesFixasSocial(){return null;}

    public static HashMap<String,Opcao> opcoesSaude(){return null;}

    public static HashMap<String,ArrayList<Acao>> opcoesAcoesVariaveisSaude(){return null;}

    public static HashMap<String,ArrayList<Acao>> opcoesAcoesFixasSaude(){return null;}

    public static HashMap<String,Opcao> opcoesAcademico(){return null;}

    public static HashMap<String,ArrayList<Acao>> opcoesAcoesVariaveisAcademico(){return null;}

    public static HashMap<String,ArrayList<Acao>> opcoesAcoesFixasAcademico(){return null;}

    public static HashMap<String,Opcao> opcoesFinanceiro(){return null;}

    public static HashMap<String,ArrayList<Acao>> opcoesAcoesVariaveisFinanceiro(){return null;}

    public static HashMap<String,ArrayList<Acao>> opcoesAcoesFixasFinanceiro(){return null;}

    public static void ramdomAcoesVariaveis(HashMap<String, ArrayList<Acao>> acoesFixas,HashMap<String, ArrayList<Acao>> acoesVariaveis, HashMap<String, ArrayList<Acao>> acoesDisponiveis){

        ArrayList<String> chaves = new ArrayList<>();

        acoesFixas.forEach((chave, acaoFixa) -> {

            chaves.add(chave);


                }
                );

        acoesVariaveis.forEach((chave, acaoVariavel) -> {

                    chaves.add(chave);


                }
        );
        for(String chave : chaves){
            ArrayList<Acao> acoes = new ArrayList<>(acoesFixas.get(chave));
            if (acoesVariaveis.get(chave)!= null) {
                for (Acao acao : acoesVariaveis.get(chave)) {
                    if (random.nextBoolean()) {
                        acoes.add(acao);
                    }
                }
            }
            acoesDisponiveis.put(chave, acoes);
        }


        }
    }

