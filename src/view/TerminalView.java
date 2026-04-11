package view;
import model.Acao;
import model.CenaDialogo;
import model.Jogador;
import model.NPC;
import model.Opcao;
import model.OpcaoResposta;
import model.enums.BlocoTempo;
import model.enums.SemanaEnum;
import java.util.List;
import java.util.Scanner;

public class TerminalView {
    private final Scanner scanner;
    private static final String LINHA_DUPLA   = "============================================================";
    private static final String LINHA_SIMPLES = "------------------------------------------------------------";

    public TerminalView() {
        this.scanner = new Scanner(System.in);
    }
    // TELA INICIAL
    //ESSE METODO: EXIBE A TELA DE BOAS VINDAS COM O NOME DO JOGO, UMA BREVE DESCRICAO
    // E O OBJETIVO PARA O JOGADOR ENTENDER O CONTEXTO DA AVENTURA!
    public void exibirBoasVindas() {
        limparTela();
        System.out.println(LINHA_DUPLA);
        System.out.println("        BIXO QUEST: Da Matricula a Formatura        ");
        System.out.println(LINHA_DUPLA);
        System.out.println("  Um simulador da vida universitaria na UEFS.");
        System.out.println("  Gerencie seu tempo, sua saude e seus estudos.");
        System.out.println("  Objetivo: sobreviver e se FORMAR!");
        System.out.println(LINHA_DUPLA);
        System.out.println();
    }

    //ESSE METODO: PEDE PARA O JOGADOR DIGITAR O NOME DO SEU PERSONAGEM, E GARANTE QUE SEJA UM NOME VÁLIDO (NÃO VAZIO),
    public String pedirNomeJogador() {
        System.out.print("  Digite o nome do seu personagem: ");
        String nome = scanner.nextLine().trim();
        if (nome.isEmpty()) {
            nome = "Calouro";
        }
        return nome;
    }

    //PAINEL DE STATUS DO JOGADOR
    public void exibirStatusJogador(Jogador j, int semestre, int semana, SemanaEnum tipoSemana, BlocoTempo bloco) {
        System.out.println();
        System.out.println(LINHA_DUPLA);
        System.out.printf("  BIXO QUEST  |  Semestre %d  |  Semana %d — %s%n",
                semestre, semana, tipoSemana.exibir());
        System.out.printf("  Turno: %-20s  Conclusao: %.1f%%%n",
                bloco.exibir(), j.getPorcentagem());
        System.out.println(LINHA_SIMPLES);
        System.out.printf("  Nome: %-30s%n", j.getNome());
        System.out.println(LINHA_SIMPLES);
        System.out.printf("  Energia    : %s  [%d pts]%n",  barraStatus(j.getEnergia(), 70),  j.getEnergia());
        System.out.printf("  Saude      : %s  [%d pts]%n",  barraStatus(j.getSaude(), 100),   j.getSaude());
        System.out.printf("  Motivacao  : %s  [%d pts]%n",  barraStatus(j.getMotivacao(), 100), j.getMotivacao());
        System.out.println(LINHA_SIMPLES);
        System.out.printf("  Dinheiro   : R$ %.2f%n", j.getDinheiro());
        System.out.println(LINHA_SIMPLES);
        System.out.printf("  Exatas     : %d pts   |   Tecnicas : %d pts   |   Algoritmos : %d pts%n",
                j.getDesempenho_EXA(), j.getDesempenho_TEC(), j.getDesempenho_ALG());
        System.out.println(LINHA_DUPLA);
    }

    //MENU DE NAVEGACAO (SistemaDeRotas)

    //ESSE METODO: EXIBE O MENU DE NAVEGACAO PARA O JOGADOR, COM O TITULO DO LOCAL ATUAL E AS OPÇÕES DE ESCOLHA DISPONIVEIS!
    public void exibirMenuNavegacao(Opcao opcao, boolean podevoltar) {
        System.out.println();
        System.out.println("  [ " + opcao.getTituloLocal() + " ]");
        System.out.println(LINHA_SIMPLES);
        List<String> escolhas = opcao.getTextosEscolhas();
        for (int i = 0; i < escolhas.size(); i++) {
            System.out.printf("  [%d] %s%n", i + 1, escolhas.get(i));
        }
        if (podevoltar) {
            System.out.println("  [0] Voltar");
        }
        System.out.print("  > ");
    }

    //LISTA DE NPCs DISPONIVEIS

    //ESSE METODO: EXIBE A LISTA DE NPCs DISPONIVEIS PARA INTERAGIR EM UM LOCAL,
    //COM O NOME DE CADA NPC E SEU NIVEL DE RELACIONAMENTO ATUAL,
    public void exibirListaNPCs(String nomeLocal, List<NPC> npcs) {
        System.out.println();
        System.out.println("  [ " + nomeLocal + " ]");
        System.out.println(LINHA_SIMPLES);
        if (npcs.isEmpty()) {
            System.out.println("  Nao ha ninguem aqui neste momento.");
        } else {
            for (int i = 0; i < npcs.size(); i++) {
                NPC npc = npcs.get(i);
                System.out.printf("  [%d] Falar com %s  (relacionamento: %d)%n",
                        i + 1, npc.getNome(), npc.getNivelRelacionamento());
            }
        }
        System.out.println("  [0] Voltar");
        System.out.print("  > ");
    }

    //CENA DE DIALOGO COM NPC
    public void exibirFalaNPC(String nomeNPC, String fala) {
        System.out.println();
        System.out.println(LINHA_SIMPLES);
        System.out.println("  " + nomeNPC + " diz:");
        System.out.println("  \"" + fala + "\"");
        System.out.println(LINHA_SIMPLES);
    }

    //ESSE METODO: EXIBE AS OPÇÕES DE RESPOSTA QUE O JOGADOR TEM PARA ESCOLHER DURANTE UM DIALOGO COM NPC,
    //INCLUINDO O TEXTO DE CADA OPÇÃO E OS IMPACTOS
    public void exibirOpcoesResposta(List<OpcaoResposta> opcoes) {
        System.out.println("  Como voce responde?");
        for (int i = 0; i < opcoes.size(); i++) {
            OpcaoResposta op = opcoes.get(i);
            System.out.printf("  [%d] %s%n", i + 1, op.getTextoPlayer());
            // Mostra os impactos para o jogador decidir com consciencia
            System.out.printf("      Impactos: %s%n", resumirImpactos(op));
        }
        System.out.print("  > ");
    }

    //ESSE METODO: EXIBE A REAÇÃO DO NPC APOS O JOGADOR ESCOLHER UMA OPÇÃO DE RESPOSTA, COM O TEXTO DA REAÇÃO E
    //OS IMPACTOS QUE A RESPOSTA TEVE NO NPC, PARA DAR FEEDBACK AO JOGADOR SOBRE AS CONSEQUENCIAS DE SUAS ESCOLHAS!
    public void exibirReacaoNPC(String nomeNPC, String reacao) {
        System.out.println();
        System.out.println("  " + nomeNPC + " respondeu:");
        System.out.println("  \"" + reacao + "\"");
        System.out.println(LINHA_SIMPLES);
        pausar();
    }

    //RESULTADO DA ACAO APLICADA
    public void exibirResultadoAcao(Acao acao) {
        System.out.println();
        System.out.println("  >> " + acao.toString());
        System.out.println();
    }

    //RESUMO DO TURNO (FIM DO DIA)
    public void exibirResumoTurno(Jogador j, List<Acao> acoes) {
        System.out.println();
        System.out.println(LINHA_DUPLA);
        System.out.println("               RESUMO DO DIA                ");
        System.out.println(LINHA_DUPLA);
        if (acoes.isEmpty()) {
            System.out.println("  Voce nao realizou nenhuma interacao hoje.");
        } else {
            System.out.println("  Acoes realizadas:");
            for (Acao a : acoes) {
                System.out.println("   * " + a.toString());
            }
        }
        System.out.println(LINHA_SIMPLES);
        System.out.println("  Status atual:");
        System.out.printf("  Energia: %d  |  Saude: %d  |  Motivacao: %d  |  Dinheiro: R$ %.2f%n",
                j.getEnergia(), j.getSaude(), j.getMotivacao(), j.getDinheiro());
        System.out.println(LINHA_DUPLA);
        pausar();
    }

    //AVANCO DE BLOCO DE TEMPO
    public void exibirAvancoBloco(BlocoTempo bloco) {
        System.out.println();
        System.out.println("  [ " + bloco.exibir() + " ]  O tempo passou...");
        System.out.println();
    }

    //ESSE METODO: EXIBE A TELA DE FIM DE DIA, COM UMA MENSAGEM DE DESCANSO PARA O JOGADOR,
    public void exibirFimDoDia() {
        System.out.println();
        System.out.println(LINHA_SIMPLES);
        System.out.println("  A noite caiu. Voce volta para casa e descansa.");
        System.out.println("  Sua energia sera restaurada para amanha.");
        System.out.println(LINHA_SIMPLES);
        pausar();
    }

    //AVISO DE LOCAL VAZIO
    public void exibirLocalVazio(String nomeLocal, BlocoTempo bloco) {
        System.out.println();
        System.out.println("  Nao ha ninguem em " + nomeLocal + " no momento (" + bloco.exibir() + ").");
        System.out.println("  Tente outro local ou aguarde o proximo turno.");
        pausar();
    }

    // GAME OVER E FORMATURA
    //ESSE METODO: EXIBE A TELA DE GAME OVER COM O MOTIVO DO FIM DE JOGO,
    // QUANDO O JOGADOR PERDER POR FALTA DE SAUDE, MOTIVACAO OU DINHEIRO!
    public void exibirGameOver(String motivo) {
        System.out.println();
        System.out.println(LINHA_DUPLA);
        System.out.println("                    GAME OVER                    ");
        System.out.println(LINHA_DUPLA);
        System.out.println("  " + motivo);
        System.out.println(LINHA_DUPLA);
    }

    //ESSE METODO: EXIBE A TELA DE FORMATURA COM UMA MENSAGEM DE PARABENS
    //E UM RESUMO DA JORNADA DO JOGADOR, QUANDO ELE CONCLUIR 100% DO CURSO!
    public void exibirFormatura(Jogador j) {
        System.out.println();
        System.out.println(LINHA_DUPLA);
        System.out.println("              PARABENS, ENGENHEIRO(A)!            ");
        System.out.println(LINHA_DUPLA);
        System.out.printf("  %s concluiu o curso de Engenharia de Computacao!%n", j.getNome());
        System.out.println("  Voce sobreviveu as provas, as filas do RU,");
        System.out.println("  aos trabalhos de PBL e ao Anfranserai.");
        System.out.println("  Bem-vindo ao mundo real. Boa sorte!");
        System.out.println(LINHA_DUPLA);
    }

    //LEITURA DE ENTRADA
    //LÊ A OPÇÃO DO USUÁRIO E GARANTE QUE SEJA UM NÚMERO VÁLIDO ENTRE 0 E O MÁXIMO PERMITIDO (INCLUSIVE)
    public int lerOpcao(int max) {
        while (true) {
            try {
                String linha = scanner.nextLine().trim();
                int valor = Integer.parseInt(linha);
                if (valor >= 0 && valor <= max) {
                    return valor;
                }
                System.out.print("  Opcao invalida. Digite entre 0 e " + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print("  Digite um numero: ");
            }
        }
    }

    //UTILITARIOS INTERNOS

    //LITERAL UMA BARRA DE STATUS PARA NÃO SER APENAS NUMERICO!
    private String barraStatus(int valor, int maximo) {
        int blocos = 10;
        int cheios = (int) Math.round(((double) valor / maximo) * blocos);
        StringBuilder barra = new StringBuilder("[");
        for (int i = 0; i < blocos; i++) {
            barra.append(i < cheios ? "=" : ".");
        }
        barra.append("]");
        return barra.toString();
    }

    //ESSE METODO: RECEBE UMA OPCAO DE RESPOSTA E RESUME OS IMPACTOS QUE ELA VAI TER NO JOGADOR,
    //PARA EXIBIR JUNTO COM O TEXTO DA RESPOSTA, ASSIM O JOGADOR DECIDE COM CONSCIENCIA!
    private String resumirImpactos(OpcaoResposta op) {
        StringBuilder sb = new StringBuilder();
        if (op.getCustoEnergia() != 0)
            sb.append("Energia(").append(-op.getCustoEnergia()).append(") ");
        if (op.getSaldoSaude() != 0)
            sb.append("Saude(").append(op.getSaldoSaude()).append(") ");
        if (op.getSaldoMotivacao() != 0)
            sb.append("Motivacao(").append(op.getSaldoMotivacao()).append(") ");
        if (op.getGanhoDinheiro() != 0)
            sb.append("Dinheiro(").append(String.format("%.0f", op.getGanhoDinheiro())).append(") ");
        if (op.getGanhoConhecimento() != 0)
            sb.append("Conhecimento(+").append(op.getGanhoConhecimento()).append(") ");
        String resultado = sb.toString().trim();
        return resultado.isEmpty() ? "nenhum impacto" : resultado;
    }

    //LINHAS EM BRANCO PARA SIMULAR LIMPEZA DE TELA NO TERMINAL!
    private void limparTela() {
        // Adiciona linhas em branco para simular limpeza de tela no terminal
        for (int i = 0; i < 3; i++) System.out.println();
    }

    //SIMULAR PAUSA PARA O USUÁRIO LER AS MENSAGENS ANTES DE PROSSEGUIR!
    public void pausar() {
        System.out.println("  [ENTER para continuar]");
        scanner.nextLine();
    }

    //ESSE METODO É PARA EXIBIR MENSAGENS DE AVISO OU INFORMAÇÃO DURANTE O JOGO, SEM QUE SEJAM IMPACTOS DE AÇÕES!
    public void exibirMensagem(String msg) {
        System.out.println("  " + msg);
    }
}
