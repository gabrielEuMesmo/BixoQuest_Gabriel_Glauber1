package controller;
import model.Opcao;
import model.OpcaoVisualNovel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SistemaDeRotas {

    private String pag;
    private int ultimaEscolha;
    private Map<String, OpcaoVisualNovel> roteador;

    public SistemaDeRotas() {
        this.pag          = "0";
        this.ultimaEscolha = -1;
        this.roteador     = new HashMap<>();
        alimentarDicionario();
    }

    // DICIONARIO DE MENUS
    // Cada chave e a String pag. Cada valor e a Opcao daquele nivel.
    // Menus de acao critica recebem o nomeLocalReal para busca no mapaMundo.
    private void alimentarDicionario() {

        //SISTEMA DE ROTAS VAI ALTERAR DE "Opcao" PARA "OpcaoVisualNovel"
        //ADAPTAÇÃO PARA GAYBRIEL

        //INICIAL: Portao da UEFS
        roteador.put("0", new OpcaoVisualNovel(
                "Portao da UEFS",
                Arrays.asList("Modulo 1", "Modulo 5", "Area Central"),
                false
        ));

        //MODULO 1
        roteador.put("01", new OpcaoVisualNovel(
                "Modulo 1",
                Arrays.asList("Salas do M1", "Biblioteca Central"),
                false
        ));

        // Salas do M1: acao critica — nomeLocalReal = "Salas do M1"
        roteador.put("011", new OpcaoVisualNovel(
                "Salas do M1",
                Arrays.asList("Interagir com personagens presentes"),
                true,
                "Salas do M1"
                //Esse tem "nomeRealLocal" pois é um menu de ação crítica, onde o jogador pode escolher qualquer local para realizar a ação,
                // e o nomeRealLocal serve para buscar o local atual no mapaMundo e aplicar os efeitos da interação com NPC naquele local específico!
        ));

        // Biblioteca: ta sem NPC
        roteador.put("012", new OpcaoVisualNovel(
                "Biblioteca Central",
                Arrays.asList("Interagir com personagens presentes"),
                true,
                "Biblioteca Central"
        ));

        //MODULO 5
        roteador.put("02", new OpcaoVisualNovel(
                "Modulo 5",
                Arrays.asList("Cantina do M5", "PATs do M5", "DA de Computacao"),
                false
        ));

        //CANTINA DO M5
        roteador.put("021", new OpcaoVisualNovel(
                "Cantina do M5",
                Arrays.asList("Interagir com personagens presentes"),
                true,
                "Cantina do M5"
        ));

        //PAT DO MODULO 5
        roteador.put("022", new OpcaoVisualNovel(
                "PATs do M5",
                Arrays.asList("Interagir com personagens presentes"),
                true,
                "PATs do M5"
        ));

        //DA DE COMPUTAÇÃO
        roteador.put("023", new OpcaoVisualNovel(
                "DA de Computacao",
                Arrays.asList("Interagir com personagens presentes"),
                true,
                "DA de Computacao"
        ));



        //AREA CENTRAL
        roteador.put("03", new OpcaoVisualNovel(
                "Area Central",
                Arrays.asList("Praca Central", "Colegiado de Computacao"),
                false
        ));

        roteador.put("031", new OpcaoVisualNovel(
                "Praca Central",
                Arrays.asList("Interagir com personagens presentes"),
                true,
                "Praca Central"
        ));

        roteador.put("032", new OpcaoVisualNovel(
                "Colegiado de Computacao",
                Arrays.asList("Interagir com personagens presentes"),
                true,
                "Colegiado de Computacao"
        ));
    }

    // CONTROLE DE NAVEGACAO

    public OpcaoVisualNovel getMenuAtual() {
        return roteador.get(pag);
    }
    //opcao
    public boolean processarEscolha(int escolha) {
        OpcaoVisualNovel menuAtual = getMenuAtual();

        //BOTÃO DE VOLTAR: sobe um nivel, removendo o ultimo digito da pag!
        if (escolha == 0) {
            if (!pag.equals("0")) {
                pag = pag.substring(0, pag.length() - 1);
            }
            // Se ja esta na raiz, nao faz nada
            return true;
        }

        //SE A ESCOLHA NAO FOR VALIDA (fora do range de opcoes), REPETE O MESMO MENU!
        if (escolha < 1 || escolha > menuAtual.getTextosEscolhas().size()) {
            return true; // Repete o menu sem alterar nada
        }

        //AÇÃO CRITICA: se o menu atual for de acao critica, a escolha leva diretamente para a interacao com NPC!
        if (menuAtual.isAcaoCritica()) {
            ultimaEscolha = escolha;
            return false; // Sinaliza para o GameController assumir
        }

        //NAVEGAÇAO NORMAL: concatena a escolha a pag para formar a nova chave do menu
        pag = pag + escolha;
        ultimaEscolha = escolha;
        return true;
    }

    public int getUltimaEscolha() {
        return ultimaEscolha;
    }

    //ESSE METODO: reseta a pag para "0", ou seja, volta para o menu raiz.
    public void resetarParaRaiz() {
        this.pag = "0";
    }

    //ESSE METODO: verifica se o jogador esta atualmente na raiz do menu, ou seja, se a pag é "0".
    // Pode ser usado para condicionar certas acoes ou mensagens que so fazem sentido na raiz.
    public boolean isNaRaiz() {
        return pag.equals("0");
    }

    //ESSE METODO: devolve a pag atual!
    public String getPagAtual() {
        return pag;
    }
}
