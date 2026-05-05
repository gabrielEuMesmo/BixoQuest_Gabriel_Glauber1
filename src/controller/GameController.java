package controller;
import model.Acao;
import model.AcaoDialogo;
import model.CenaDialogo;
import model.ConstrutorDeMapa;
import model.Jogador;
import model.Local;
import model.NPC;
import model.Opcao;
import model.OpcaoVisualNovel;
import model.OpcaoResposta;
import model.enums.BlocoTempo;
import model.enums.SemanaEnum;
import model.persistencia.Captura;
import model.persistencia.Load;
import view.TerminalView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameController {

    // ── Dependencias
    private final TerminalView    view;
    private final SistemaDeRotas  rotas;
    private final Map<String, Local> mapaMundo;

    // ── Estado do jogo
    private Jogador jogador;
    private int     semestreAtual;
    private int     semanaAtual;
    private BlocoTempo blocoTempo;

    // Sequencia fixa de tipos de semana por semestre (6 semanas cada)
    private static final SemanaEnum[] SEMANAS_POR_SEMESTRE = {
            SemanaEnum.LIVRE,
            SemanaEnum.LIVRE,
            SemanaEnum.PROVA_M1,
            SemanaEnum.LIVRE,
            SemanaEnum.PROVA_M2,
            SemanaEnum.PROVA_M3
    };

    private BlocoTempo blocoTempoAtual;

    private List<Acao> acoesDoTurno = new ArrayList<>();

    // Numero total de semestres no jogo
    private static final int TOTAL_SEMESTRES = 5;

    // CONSTRUTOR
    public GameController() {
        this.view       = new TerminalView();
        this.rotas      = new SistemaDeRotas();
        this.mapaMundo  = ConstrutorDeMapa.gerarMapaUefs();
    }

    // PONTO DE ENTRADA
    public void iniciar() {
        view.exibirBoasVindas();
        String nome = view.pedirNomeJogador();

        // Cria o jogador com os atributos iniciais
        jogador = new Jogador(nome, 80, 70, 80, 800.0);

        semestreAtual = 1;
        semanaAtual   = 1;
        blocoTempo    = BlocoTempo.MANHA_1;

        //TRUXBIRAUNDAUM

        view.exibirMensagem("\n  " + nome + " se matriculou em Engenharia de Computacao na UEFS.");
        view.exibirMensagem("  Seu Mais Futuro caiu. A jornada comeca agora!\n");
        view.pausar();

        loopPrincipal();
    }

    // LOOP PRINCIPAL
    private void loopPrincipal() {
        while (semestreAtual <= TOTAL_SEMESTRES && !jogador.isGameOver() && !jogador.isFormado()) {

            for (semanaAtual = 1; semanaAtual <= 6; semanaAtual++) {

                SemanaEnum tipoSemana = SEMANAS_POR_SEMESTRE[semanaAtual - 1];

                // Restaura energia no inicio de cada semana
                jogador.restaurarEnergiaDia();
                jogador.limparAcoesDoTurno();
                blocoTempo = BlocoTempo.MANHA_1;

                executarSemana(tipoSemana);

                if (jogador.isGameOver()) break;
            }

            if (!jogador.isGameOver()) {
                fecharSemestre();
                semestreAtual++;
            }
        }

        //RESULTADO FINAL
        if (jogador.isFormado()) {
            view.exibirFormatura(jogador);
        } else if (jogador.isGameOver()) {
            view.exibirGameOver(jogador.motivoGameOver());
        }
    }

    // EXECUCAO DE UMA SEMANA
    private void executarSemana(SemanaEnum tipoSemana) {

        // Loop de blocos de tempo (MANHA_1 ate NOITE)
        while (blocoTempo != null && jogador.temEnergiaDia() && !jogador.isGameOver()) {

            // Exibe status no inicio de cada bloco
            view.exibirStatusJogador(jogador, semestreAtual, semanaAtual, tipoSemana, blocoTempo);

            // Loop de menu de navegacao para este bloco
            executarMenuNavegacao();

            // Avanca para o proximo bloco de tempo
            BlocoTempo proximo = blocoTempo.proximo();
            if (proximo != null && jogador.temEnergiaDia()) {
                blocoTempo = proximo;
                view.exibirAvancoBloco(blocoTempo);
            } else {
                break; // Fim do dia ou sem energia
            }
        }

        view.exibirFimDoDia();
        view.exibirResumoTurno(jogador, jogador.getAcoesDoTurno());

        // Se for semana de prova, calcula e registra a nota
        if (tipoSemana.isProva()) {
            executarProva(tipoSemana);
        }
    }

    // LOOP DE MENU DE NAVEGACAO
    private void executarMenuNavegacao() {

        rotas.resetarParaRaiz();
        boolean continuarNavegando = true;

        while (continuarNavegando && jogador.temEnergiaDia() && !jogador.isGameOver()) {

            OpcaoVisualNovel menuAtual = rotas.getMenuAtual();
            boolean podeVoltar = !rotas.isNaRaiz();
            //opcao
            // Exibe o menu de navegacao (lista de locais)
            view.exibirMenuNavegacao(menuAtual, podeVoltar);
            int escolha = view.lerOpcao(menuAtual.getTextosEscolhas().size());

            // Processa a navegacao
            boolean apenasNavegou = rotas.processarEscolha(escolha);

            if (!apenasNavegou) {
                // Chegou num local de acao critica: exibe NPCs e executa interacao
                boolean encerrouBloco = executarInteracaoNPC();

                // Sempre reseta a navegacao para a raiz apos tentar uma acao
                rotas.resetarParaRaiz();

                // Se houve interacao real ou local vazio, encerra o bloco atual
                if (encerrouBloco) {
                    continuarNavegando = false;
                }
            }
        }
    }

    // INTERACAO COM NPC
    private boolean executarInteracaoNPC() {

        // Pega o Local real a partir do nomeLocalReal da Opcao atual
        OpcaoVisualNovel opcaoAtual     = rotas.getMenuAtual();
        String nomeLocalReal = opcaoAtual.getNomeLocalReal();
        Local  localAtual    = mapaMundo.get(nomeLocalReal);

        if (localAtual == null) {
            view.exibirMensagem("Local nao encontrado no mapa: " + nomeLocalReal);
            return false;
        }

        // Busca os NPCs presentes neste local no bloco de tempo atual
        List<NPC> npcsPresentes = localAtual.getPersonagensNoTurno(blocoTempo);

        if (npcsPresentes.isEmpty()) {
            view.exibirLocalVazio(nomeLocalReal, blocoTempo);
            // Local vazio: ainda conta como turno gasto (o jogador perdeu tempo)
            // mas nao aplica nenhum efeito. Retorna true para o bloco avancar.
            return true;
        }

        // Exibe a lista de NPCs e pede escolha
        view.exibirListaNPCs(nomeLocalReal, npcsPresentes);
        int escolhaNPC = view.lerOpcao(npcsPresentes.size());

        // 0 = Voltar sem interagir
        if (escolhaNPC == 0) {
            return false;
        }

        // Pega o NPC escolhido (indice = escolha - 1)
        NPC npcEscolhido = npcsPresentes.get(escolhaNPC - 1);

        // Pega a cena atual do NPC com base no nivel de relacionamento
        CenaDialogo cena = npcEscolhido.getCenaAtual();

        // Exibe a fala do NPC
        view.exibirFalaNPC(npcEscolhido.getNome(), cena.getFalaPrincipal());

        // Exibe as opcoes de resposta
        List<OpcaoResposta> opcoes = cena.getOpcoes();
        view.exibirOpcoesResposta(opcoes);
        int escolhaResposta = view.lerOpcao(opcoes.size());

        // 0 = Voltar sem responder (jogador preferiu nao responder)
        if (escolhaResposta == 0 || escolhaResposta > opcoes.size()) {
            return false;
        }

        // Pega a resposta escolhida
        OpcaoResposta respostaEscolhida = opcoes.get(escolhaResposta - 1);

        // Converte em AcaoDialogo e aplica no jogador
        AcaoDialogo acao = respostaEscolhida.converterParaAcao(
                npcEscolhido.getNome(),
                npcEscolhido.getArea()
        );

        acao.aplicarJogador(jogador);
        jogador.registrarAcao(acao);

        // Atualiza o relacionamento do NPC
        npcEscolhido.aumentarRelacionamento(respostaEscolhida.getImpactoRelacionamento());

        // Exibe a reacao do NPC e o resultado da acao
        view.exibirReacaoNPC(npcEscolhido.getNome(), respostaEscolhida.getReacaoDoNPC());
        view.exibirResultadoAcao(acao);
        return true;
    }

    // PROVA
    private void executarProva(SemanaEnum tipoProva) {

        view.exibirMensagem("");
        view.exibirMensagem("  *** SEMANA DE PROVA — " + tipoProva.exibir() + " ***");
        view.exibirMensagem("");

        double notaEXA = calcularNota(jogador.getDesempenho_EXA());
        double notaTEC = calcularNota(jogador.getDesempenho_TEC());
        double notaALG = calcularNota(jogador.getDesempenho_ALG());

        view.exibirMensagem(String.format("  Exatas    : %.1f", notaEXA));
        view.exibirMensagem(String.format("  Tecnicas  : %.1f", notaTEC));
        view.exibirMensagem(String.format("  Algoritmos: %.1f", notaALG));
        view.exibirMensagem("");

        boolean aprovadoEXA = notaEXA >= 7.0;
        boolean aprovadoTEC = notaTEC >= 7.0;
        boolean aprovadoALG = notaALG >= 7.0;

        view.exibirMensagem("  Exatas   : " + (aprovadoEXA ? "APROVADO" : "REPROVADO"));
        view.exibirMensagem("  Tecnicas : " + (aprovadoTEC ? "APROVADO" : "REPROVADO"));
        view.exibirMensagem("  Algoritmos: " + (aprovadoALG ? "APROVADO" : "REPROVADO"));

        // Penalidade por reprovacao: perde motivacao e saude
        if (!aprovadoEXA || !aprovadoTEC || !aprovadoALG) {
            int reprovadas = (aprovadoEXA ? 0 : 1) + (aprovadoTEC ? 0 : 1) + (aprovadoALG ? 0 : 1);
            jogador.somarMotivacao(-10 * reprovadas);
            view.exibirMensagem("  Voce reprovou " + reprovadas + " materia(s). Motivacao cai.");
        } else {
            // Bonus por aprovacao total
            jogador.avancarPorcentagem(6.67); // ~100% / 15 provas no total (3 provas x 5 semestres)
            jogador.somarMotivacao(15);
            view.exibirMensagem("  Aprovado(a) em tudo! Conclusao do curso avancou.");
        }
        view.pausar();
    }

    private double calcularNota(int desempenho) {
        double nota = Math.min(10.0, desempenho / 10.0);
        return Math.max(0.0, nota);
    }

    // FECHAMENTO DE SEMESTRE
    private void fecharSemestre() {
        view.exibirMensagem("");
        view.exibirMensagem("  ===============================================");
        view.exibirMensagem("  SEMESTRE " + semestreAtual + " CONCLUIDO!");
        view.exibirMensagem("  ===============================================");
        view.exibirMensagem("  Conclusao do curso: " + String.format("%.1f", jogador.getPorcentagem()) + "%");
        view.exibirMensagem("");

        if (semestreAtual < TOTAL_SEMESTRES) {
            view.exibirMensagem("  Preparando o proximo semestre...");
        }
        view.pausar();
    }

    public void carregarJogo(String slotSelecionado) {
        // 1. Pede para a classe Load buscar o arquivo e devolver o objeto Captura
        Captura saveRecuperado = Load.carregar(slotSelecionado);

        if (saveRecuperado != null) {
            // 2. Desempacota a Captura (Aplica os dados de volta no Controller)
            // NÃO se usa construtor! Você só substitui as variáveis atuais pelas que vieram do save.

            this.jogador = saveRecuperado.getJogador(); // O Jogador inteiro, com dinheiro, saúde, volta à vida!
            this.semestreAtual = saveRecuperado.getSemestreAtual();
            this.semanaAtual = saveRecuperado.getSemanaAtual();
            this.blocoTempoAtual = saveRecuperado.getBlocoTempoAtual();

            // 3. Recria o mapa estático (Locais limpos)
            this.mapaMundo = ConstrutorDeMapa.gerarMapaUefs();

            // 4. Aplica os relacionamentos nos NPCs recém-criados usando o Map do save
            Map<String, Integer> relacoesSalvas = saveRecuperado.getRelacionamentoPorNpc();
            aplicarRelacionamentos(relacoesSalvas); // Seu metodo de varrer os NPCs

            // 5. Atualiza o histórico de ações daquele turno
            this.acoesDoTurno = saveRecuperado.getAcoesDoTurno();

            System.out.println("Jogo Carregado! Bem vindo de volta, " + this.jogador.getNome());
        }
    }

    private void aplicarRelacionamentos(Map<String, Integer> relacoesSalvas) {
        if (relacoesSalvas == null) return;

        // Para cada local do seu mapaMundo
        for (Local local : this.mapaMundo.values()) {
            // Pega todos os personagens daquele local
            for (NPC npc : local.getTodosPersonagens()) {
                String nomeNpc = npc.getNome();
                // Se o NPC existe no save, aplica o nível
                if (relacoesSalvas.containsKey(nomeNpc)) {
                    int nivelSalvo = relacoesSalvas.get(nomeNpc);
                    npc.setNivelRelacionamento(nivelSalvo); // Certifique-se que NPC tem esse metodo!
                }
            }
        }
    }
}