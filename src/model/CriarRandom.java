package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

// Centraliza a criação de colegas e das estruturas de menus/ações.
public class CriarRandom {

    // Lista base de nomes possíveis para colegas.
    private static final String[] nomesColegas = {"Gbriel"};

    // Lista base de cargos possíveis para colegas.
    private static final String[] cargosColegas = {"EXA", "TEC", "ALG"};

    // Gerador pseudoaleatório usado nas variações do jogo.
    private static final Random random = new Random();

    // Cria um colega com dados aleatórios.
    public static Colega criarColega(){
        boolean cargo = random.nextBoolean();
        String cargoTipo = "";
        if(cargo){
            cargoTipo = cargosColegas[random.nextInt(2)];
        }
        return new Colega(nomesColegas[random.nextInt(nomesColegas.length)], random.nextInt(1,10), cargo, cargoTipo, random.nextInt(10), random.nextInt(10), random.nextInt(10));
    }

    // Monta a árvore de opções do menu social.
    public static HashMap<String,Opcao> opcoesSocial(){

        HashMap<String,Opcao> retorno = new HashMap<>();

        ArrayList<String> opcoes = new ArrayList<>(Arrays.asList("Conversar"));
        Opcao opcao = new Opcao("Social", opcoes);
        retorno.put("",opcao);

        opcoes.clear();
        opcoes.add("Colegas");

        opcao = new Opcao("Conversar", opcoes);
        retorno.put("1",opcao);


        opcoes.clear();
        opcao = new Opcao("Colegas", opcoes);
        retorno.put("11",opcao);

        return retorno;
    }

    // Monta as ações variáveis do menu social.
    public static HashMap<String,ArrayList<Acao>> opcoesAcoesVariaveisSocial(){
        HashMap<String, ArrayList<Acao>> retorno = new HashMap<>();
        ArrayList<Acao> acoes = new ArrayList<>(){{
            add(new Acao("Saiu para calourada", -5, 20, -30, 0, 0, 0));
        }};

        retorno.put("2",acoes);
        return retorno;
    }

    // Monta as ações fixas do menu social.
    public static HashMap<String,ArrayList<Acao>> opcoesAcoesFixasSocial(){
        HashMap<String, ArrayList<Acao>> retorno = new HashMap<>();
        ArrayList<Acao> acoes = new ArrayList<>(){{
            add(new Acao("Foi na lanchonete com amigos", 5, 10, -30, 0, 0, 0));
        }};

        retorno.put("2",acoes);
        return retorno;
    }

    // Monta a árvore de opções do menu de saúde.
    public static HashMap<String,Opcao> opcoesSaude(){
        HashMap<String,Opcao> retorno = new HashMap<>();

        ArrayList<String> opcoes = new ArrayList<>(Arrays.asList("Exercicios", "Alimentacao"));
        Opcao opcao = new Opcao("Saude", opcoes);
        retorno.put("",opcao);
        opcoes.clear();
        opcao = new Opcao("Exercicios", opcoes);
        retorno.put("1",opcao);

        opcoes.clear();
        opcoes.add("Comer comidas saudaveis");
        opcoes.add("Voltar");
        opcao = new Opcao("Alimentacao", opcoes);
        retorno.put("2",opcao);

        return retorno;
    }

    // Monta as ações variáveis do menu de saúde.
    public static HashMap<String,ArrayList<Acao>> opcoesAcoesVariaveisSaude(){
        HashMap<String, ArrayList<Acao>> retorno = new HashMap<>();
        ArrayList<Acao> acoes = new ArrayList<>(){{
            add(new Acao("Correu na UEFS com amigos", 10, 15, 0, 0, 0, 0));
        }};

        retorno.put("1",acoes);
        return retorno;
    }

    // Monta as ações fixas do menu de saúde.
    public static HashMap<String,ArrayList<Acao>> opcoesAcoesFixasSaude(){
        HashMap<String, ArrayList<Acao>> retorno = new HashMap<>();
        ArrayList<Acao> acoes = new ArrayList<>(){{
            add(new Acao("Foi para Academia", 10, 15, 0, 0, 0, 0));
        }};

        retorno.put("1",acoes);
        return retorno;
    }

    // Monta a árvore de opções do menu acadêmico.
    public static HashMap<String,Opcao> opcoesAcademico(){
        HashMap<String,Opcao> retorno = new HashMap<>();

        ArrayList<String> opcoes = new ArrayList<>(Arrays.asList("Estudar", "Estagiar"));
        Opcao opcao = new Opcao("Academico", opcoes);
        retorno.put("",opcao);

        opcoes.clear();

        opcao = new Opcao("Estudar", opcoes);
        retorno.put("1",opcao);

        opcoes.clear();

        opcao = new Opcao("Estagiar", opcoes);
        retorno.put("2",opcao);


        return retorno;
    }

    // Monta as ações variáveis do menu acadêmico.
    public static HashMap<String,ArrayList<Acao>> opcoesAcoesVariaveisAcademico(){
        HashMap<String, ArrayList<Acao>> retorno = new HashMap<>();
        ArrayList<Acao> acoes = new ArrayList<>(){{
            add(new Acao("Estagiou meio periodo", -5, -10, 400, 10, 10, 10));
        }};

        acoes.add(new Acao("Estagiou integral", -10, -20, 800, 20, 20, 20));
        retorno.put("2",acoes);

        return  retorno;
    }

    // Monta as ações fixas do menu acadêmico.
    public static HashMap<String,ArrayList<Acao>> opcoesAcoesFixasAcademico(){
        HashMap<String, ArrayList<Acao>> retorno = new HashMap<>();
        ArrayList<Acao> acoes = new ArrayList<>(){{
            add(new Acao("Estudou para EXA", 0, -10, 0, 20, 0, 0));
        }};

        acoes.add(new Acao("Estudou para TEC", 0, -10, 0, 0, 20, 0));


        acoes.add(new Acao("Estudou para ALG", 0, -10, 0, 0, 0, 20));

        retorno.put("1",acoes);
        return retorno;
    }

    // Monta a árvore de opções do menu financeiro.
    public static HashMap<String,Opcao> opcoesFinanceiro(){

        HashMap<String,Opcao> retorno = new HashMap<>();

        ArrayList<String> opcoes = new ArrayList<>(Arrays.asList("Fazer Freelancer"));
        Opcao opcao = new Opcao("Financeiro", opcoes);
        retorno.put("",opcao);

        opcoes.clear();

        opcao = new Opcao("Fazer Freelancer", opcoes);
        retorno.put("1",opcao);

        return retorno;
    }

    // Monta as ações variáveis do menu financeiro.
    public static HashMap<String,ArrayList<Acao>> opcoesAcoesVariaveisFinanceiro(){
        HashMap<String, ArrayList<Acao>> retorno = new HashMap<>();
        ArrayList<Acao> acoes = new ArrayList<>(){{
            add(new Acao("Fez um projeto grande de freela", -5, -20, 1000, 10, 10, 10));
        }};
        retorno.put("1",acoes);
        return  retorno;
    }

    // Monta as ações fixas do menu financeiro.
    public static HashMap<String,ArrayList<Acao>> opcoesAcoesFixasFinanceiro(){
        HashMap<String, ArrayList<Acao>> retorno = new HashMap<>();
        ArrayList<Acao> acoes = new ArrayList<>(){{
            add(new Acao("Fez um projeto pequeno de freela", -5, -10, 500, 5, 5, 5));
        }};
        retorno.put("1",acoes);
        return  retorno;
    }

    // Recalcula as ações disponíveis combinando base fixa e eventos variáveis.
    public static void ramdomAcoesVariaveis(HashMap<String, ArrayList<Acao>> acoesFixas,HashMap<String, ArrayList<Acao>> acoesVariaveis, HashMap<String, ArrayList<Acao>> acoesDisponiveis){

        ArrayList<String> chaves = new ArrayList<>();

        acoesFixas.forEach((chave, acaoFixa) -> {
            chaves.add(chave);
        });

        acoesVariaveis.forEach((chave, acaoVariavel) -> {
            chaves.add(chave);
        });

        for(String chave : chaves){
            ArrayList<Acao> acoes;
            if (acoesFixas.containsKey(chave)){
                acoes = new ArrayList<>(acoesFixas.get(chave));
            }else {
                acoes = new ArrayList<>();
            }

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
