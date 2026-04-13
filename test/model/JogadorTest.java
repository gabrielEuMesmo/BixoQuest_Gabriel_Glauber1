package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JogadorTest {

    @Test
    void construtorEGettersDevemRefletirEstadoInicial() {
        Jogador jogador = new Jogador("Gabriel", 10, 8, 7, 500.0);

        assertEquals("Gabriel", jogador.getNome());
        assertEquals(10, jogador.getSaude());
        assertEquals(8, jogador.getEnergia());
        assertEquals(7, jogador.getMotivacao());
        assertEquals(500.0, jogador.getDinheiro());
        assertEquals(0.0, jogador.getPorcentagem());
        assertEquals(0, jogador.getDesempenho_m1());
        assertEquals(0, jogador.getDesempenho_m2());
        assertEquals(0, jogador.getDesempenho_m3());
    }

    @Test
    void mutadoresDeRecursosDevemFuncionar() {
        Jogador jogador = new Jogador("Bixo", 5, 5, 5, 100.0);

        jogador.somarDinheiro(50.0);
        jogador.subtrairDinheiro(20.0);
        jogador.somarEnergia(2);
        jogador.subtrairEnergia(1);
        jogador.somarMotivacao(3);
        jogador.subtrairMotivacao(2);
        jogador.somarSaude(4);
        jogador.subtrairSaude(1);

        assertEquals(130.0, jogador.getDinheiro());
        assertEquals(6, jogador.getEnergia());
        assertEquals(6, jogador.getMotivacao());
        assertEquals(8, jogador.getSaude());

        jogador.zerarDinheiro();
        assertEquals(0.0, jogador.getDinheiro());
    }

    @Test
    void mutadoresDeDesempenhoEMaximizacoesDevemFuncionar() {
        Jogador jogador = new Jogador("Bixo", 1, 2, 3, 10.0);

        jogador.somarDes_acad_m1(2);
        jogador.somarDes_acad_m2(3);
        jogador.somarDes_acad_m3(4);
        jogador.maximizarEnergia();
        jogador.maximizarMotivacao();
        jogador.maximizarSaude();

        assertEquals(2, jogador.getDesempenho_m1());
        assertEquals(3, jogador.getDesempenho_m2());
        assertEquals(4, jogador.getDesempenho_m3());
        assertEquals(10, jogador.getEnergia());
        assertEquals(10, jogador.getMotivacao());
        assertEquals(10, jogador.getSaude());
    }

    @Test
    void mostrarOpcaoNaRaizDeveExibirQuatroAreas() {
        Jogador jogador = new Jogador("Bixo", 10, 10, 10, 800.0);

        Opcao opcao = jogador.mostrarOpcao();

        assertEquals("Opcoes", opcao.getTitulo());
        assertEquals(List.of("Social", "Academico", "Financeiro", "Saude"), opcao.getOpcoes());
    }

    @Test
    void escolherOpcaoUmAQuatroDeveAlterarPaginaDoJogador() {
        Jogador jogador = new Jogador("Bixo", 10, 10, 10, 800.0);

        jogador.escolherOpcao(1);
        assertEquals("1", jogador.getPag());
        assertEquals("Social", jogador.mostrarOpcao().getTitulo());

        jogador = new Jogador("Bixo", 10, 10, 10, 800.0);
        jogador.escolherOpcao(2);
        assertEquals("2", jogador.getPag());
        assertEquals("Academico", jogador.mostrarOpcao().getTitulo());

        jogador = new Jogador("Bixo", 10, 10, 10, 800.0);
        jogador.escolherOpcao(3);
        assertEquals("3", jogador.getPag());
        assertEquals("Financeiro", jogador.mostrarOpcao().getTitulo());

        jogador = new Jogador("Bixo", 10, 10, 10, 800.0);
        jogador.escolherOpcao(4);
        assertEquals("4", jogador.getPag());
        assertEquals("Saude", jogador.mostrarOpcao().getTitulo());
    }

    @Test
    void entradasInvalidasEmEscolherOpcaoNoComportamentoAtualRetornamTrueESemAlterarPagina() {
        Jogador jogador = new Jogador("Bixo", 10, 10, 10, 800.0);

        assertTrue(jogador.escolherOpcao(0));
        assertEquals("", jogador.getPag());
        assertTrue(jogador.escolherOpcao(-1));
        assertEquals("", jogador.getPag());
        assertTrue(jogador.escolherOpcao(6));
        assertEquals("", jogador.getPag());
    }

    @Test
    void fluxoAposAcaoDeSubmenuAcademicoDeveVoltarParaRaiz() {
        Jogador jogador = new Jogador("Bixo", 10, 10, 10, 800.0);

        jogador.escolherOpcao(2);
        jogador.escolherOpcao(1);

        assertEquals("", jogador.getPag());
        assertEquals("Opcoes", jogador.mostrarOpcao().getTitulo());
    }

    @Test
    void atualizarDeveAplicarSaldosERegistrarAcoesNoTurno() {
        Jogador jogador = new Jogador("Bixo", 5, 5, 5, 100.0);
        jogador.getSocial().addAcao(new Acao("Passeio", 2, -1, 25.0, 1, 2, 3));

        jogador.atualizar();

        assertEquals(7, jogador.getSaude());
        assertEquals(4, jogador.getMotivacao());
        assertEquals(125.0, jogador.getDinheiro());
        assertEquals(1, jogador.getDesempenho_m1());
        assertEquals(2, jogador.getDesempenho_m2());
        assertEquals(3, jogador.getDesempenho_m3());
        assertEquals(1, jogador.getAcoesFeitas().size());
        assertEquals("Passeio", jogador.getAcoesFeitas().get(0).getTipo());

        jogador.limparAcoes();
        assertTrue(jogador.getAcoesFeitas().isEmpty());
    }

    @Test
    void toStringDeveRefletirEstadoAtual() {
        Jogador jogador = new Jogador("Bixo", 10, 9, 8, 700.0);
        jogador.somarDes_acad_m1(2);

        String texto = jogador.toString();

        assertTrue(texto.contains("Nome:Bixo"));
        assertTrue(texto.contains("Energia: 9"));
        assertTrue(texto.contains("Motivacao: 8"));
        assertTrue(texto.contains("Dinheiro: 700.0"));
        assertTrue(texto.contains("Desempenho Materia 1: 2"));
    }
}