package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Menu base para navegação e execução de ações em uma área do jogo.
public class Menu extends Atributos{

    // Jogador que recebe os efeitos do menu.
    private final Jogador jogador;

    // Página atual dentro da árvore de opções do menu.
    public String pag = "";

    // Mapa de páginas e opções navegáveis.
    private final HashMap<String, Opcao> opcoes;

    // Ações variáveis que podem aparecer neste menu.
    private final HashMap<String, ArrayList<Acao>> opcoesAcoesVariaveis;

    // Ações atualmente disponíveis para execução.
    private final HashMap<String, ArrayList<Acao>> acoesDisponiveis;

    // Ações fixas sempre associadas ao menu.
    private final HashMap<String, ArrayList<Acao>> acoesFixas;

    // Ações realizadas no turno corrente.
    private final ArrayList<Acao> acoesFeitas;

    // Inicializa a estrutura do menu com opções e ações de uma área do jogo.
    public Menu(Jogador jogador, HashMap<String, Opcao> opcoes, HashMap<String,ArrayList<Acao>> acoesVariaveis, HashMap<String,ArrayList<Acao>> acoesFixas){
        super();
        this.jogador = jogador;
        acoesFeitas = new ArrayList<>();
        this.opcoes = new HashMap<String, Opcao>(opcoes);
        opcoesAcoesVariaveis = new HashMap<>(acoesVariaveis);

        this.acoesFixas =new HashMap<>(acoesFixas);

        acoesDisponiveis =new HashMap<>(acoesFixas);
    }

    // Tenta executar uma ação a partir do código da página selecionada.
    public boolean realizar(String pag){

        if(acoesDisponiveis.containsKey(pag.substring(0,pag.length() - 1))){
            if(acoesDisponiveis.get(pag.substring(0, pag.length()-1)).size() > pag.charAt( pag.length()-1) - '0' - 1){
                addAcao(new Acao(acoesDisponiveis.get(pag.substring(0, pag.length()-1)).get(pag.charAt(pag.length()-1) - '0' -1)));
                this.pag = "";

                return true;
            }

        }

        return false;
    }

    // Registra a ação realizada e acumula seus efeitos.
    public void addAcao(Acao acao){

        acoesFeitas.add(acao);
        somaAtributos(acao);

    }

    // Processa a escolha do usuário, navegando ou executando ações.
    public boolean escolher(int escolha){

        if(opcoes.containsKey(pag + escolha)){


            pag += escolha;
            return !realizar(pag);

        }
        if(opcoes.get(pag).getOpcoes().get(escolha - 1).equals("Voltar") ){
            pag = "";
            return false;

        }

        return !realizar(pag + escolha);

    }

    // Retorna a opção associada a uma página específica.
    public Opcao getOpcao(String pag){
        return opcoes.get(pag);
    }


    // Retorna a opção correspondente à página atual.
    public Opcao mostrar(){

        return opcoes.get(pag);

    }

    // Retorna a lista de ações feitas no turno.
    public ArrayList<Acao> getAcoes() {

        return acoesFeitas;

    }

    // Aplica os saldos acumulados ao jogador e recalcula as opções do turno seguinte.
    public ArrayList<Acao> atualizar(){

        jogador.somarSaude(getSaldoSaude());
        jogador.somarMotivacao(getSaldoMotivacao());
        jogador.somarDinheiro(getSaldoDinheiro());
        jogador.somarDesempenhoEXA(getSaldoDes_acad_m1());
        jogador.somarDesempenhoTEC(getSaldoDes_acad_m2());
        jogador.somarDesempenhoALG(getSaldoDes_acad_m3());
        setSaldoSaude(0);
        setSaldoMotivacao(0);
        setSaldoDinheiro(0);
        setSaldoDes_acad_m1(0);
        setSaldoDes_acad_m2(0);
        setSaldoDes_acad_m3(0);
        CriarRandom.ramdomAcoesVariaveis(acoesFixas, opcoesAcoesVariaveis, acoesDisponiveis);

        opcoes.forEach((chave, opcao) ->{
            if (acoesDisponiveis.containsKey(chave)) {
                for (Acao acao : acoesDisponiveis.get(chave)) {
                    opcao.getOpcoes().add(acao.toString());
                }


            }
            opcao.getOpcoes().add("Voltar");
        });
        ArrayList<Acao> temp = new ArrayList<>(acoesFeitas);
        acoesFeitas.clear();
        return temp;
    }


}
