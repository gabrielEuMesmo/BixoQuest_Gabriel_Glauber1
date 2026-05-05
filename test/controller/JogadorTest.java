package controller;

import model.Acao;
import model.Jogador;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JogadorTest {

    @Test
    void construtorAplicaRegrasIniciais() {
        Jogador jogador = new Jogador("Leo", 150, 90, -5, 300.0);

        assertEquals("Leo", jogador.getNome());
        assertEquals(100, jogador.getSaude());
        assertEquals(70, jogador.getEnergia());
        assertEquals(70, jogador.getEnergiaDia());
        assertEquals(0, jogador.getMotivacao());
        assertEquals(0, jogador.getDesempenho_EXA());
        assertEquals(0, jogador.getDesempenho_TEC());
        assertEquals(0, jogador.getDesempenho_ALG());
        assertEquals(0.0, jogador.getPorcentagem());
        assertTrue(jogador.getAcoesDoTurno().isEmpty());
    }

    @Test
    void energiaSemanalFuncionaComClampERestauracao() {
        Jogador jogador = new Jogador("Leo", 80, 60, 80, 100.0);

        jogador.somarEnergia(10);
        assertEquals(70, jogador.getEnergia());

        jogador.somarEnergia(5);
        assertEquals(70, jogador.getEnergia());

        jogador.subtrairEnergia(80);
        assertEquals(0, jogador.getEnergia());
        assertFalse(jogador.temEnergia());

        jogador.subtrairEnergia(5);
        assertEquals(0, jogador.getEnergia());

        jogador.restaurarEnergia();
        assertEquals(70, jogador.getEnergia());
        assertTrue(jogador.temEnergia());
    }

    @Test
    void energiaDiariaFuncionaComClampERestauracao() {
        Jogador jogador = new Jogador("Leo", 80, 60, 80, 100.0);

        jogador.somarEnergiaDia(10);
        assertEquals(70, jogador.getEnergiaDia());

        jogador.somarEnergiaDia(5);
        assertEquals(70, jogador.getEnergiaDia());

        jogador.subtrairEnergiaDia(80);
        assertEquals(0, jogador.getEnergiaDia());
        assertFalse(jogador.temEnergiaDia());

        jogador.subtrairEnergiaDia(5);
        assertEquals(0, jogador.getEnergiaDia());

        jogador.restaurarEnergiaDia();
        assertEquals(70, jogador.getEnergiaDia());
        assertTrue(jogador.temEnergiaDia());
    }

    @Test
    void saudeMotivacaoEDinheiroRespeitamRegras() {
        Jogador jogador = new Jogador("Leo", 80, 50, 80, 100.0);

        jogador.somarSaude(30);
        assertEquals(100, jogador.getSaude());
        jogador.somarSaude(0);
        assertEquals(100, jogador.getSaude());
        jogador.subtrairSaude(200);
        assertEquals(0, jogador.getSaude());
        jogador.maximizarSaude();
        assertEquals(100, jogador.getSaude());

        jogador.somarMotivacao(50);
        assertEquals(100, jogador.getMotivacao());
        jogador.subtrairMotivacao(200);
        assertEquals(0, jogador.getMotivacao());
        jogador.maximizarMotivacao();
        assertEquals(100, jogador.getMotivacao());

        jogador.somarDinheiro(100.0);
        assertEquals(200.0, jogador.getDinheiro());
        jogador.subtrairDinheiro(50.0);
        assertEquals(150.0, jogador.getDinheiro());
        jogador.subtrairDinheiro(200.0);
        assertEquals(0.0, jogador.getDinheiro());
        jogador.somarDinheiro(30.0);
        jogador.zerarDinheiro();
        assertEquals(0.0, jogador.getDinheiro());
    }

    @Test
    void desempenhoAcademicoSoAceitaPositivosERetornosCorretos() {
        Jogador jogador = new Jogador("Leo", 80, 50, 80, 100.0);

        jogador.somarDesempenhoEXA(15);
        jogador.somarDesempenhoEXA(-5);
        jogador.somarDesempenhoEXA(0);
        assertEquals(15, jogador.getDesempenho_EXA());

        jogador.somarDesempenhoTEC(11);
        jogador.somarDesempenhoTEC(-1);
        jogador.somarDesempenhoTEC(0);
        assertEquals(11, jogador.getDesempenho_TEC());

        jogador.somarDesempenhoALG(8);
        jogador.somarDesempenhoALG(-3);
        jogador.somarDesempenhoALG(0);
        assertEquals(8, jogador.getDesempenho_ALG());

        assertEquals(20, jogador.somarDesempenhoAcademicoEXA(5));
        assertEquals(17, jogador.somarDesempenhoAcademicoTEC(6));
        assertEquals(12, jogador.somarDesempenhoAcademicoALG(4));

        assertEquals(20, jogador.somarDesempenhoAcademicoEXA(-10));
        assertEquals(17, jogador.somarDesempenhoAcademicoTEC(0));
        assertEquals(12, jogador.somarDesempenhoAcademicoALG(-1));
    }

    @Test
    void porcentagemDoCursoEFormatura() {
        Jogador jogador = new Jogador("Leo", 80, 50, 80, 100.0);

        jogador.avancarPorcentagem(20.0);
        assertEquals(20.0, jogador.getPorcentagem());

        jogador.avancarPorcentagem(60.0);
        assertEquals(80.0, jogador.getPorcentagem());
        assertFalse(jogador.isFormado());

        jogador.avancarPorcentagem(30.0);
        assertEquals(100.0, jogador.getPorcentagem());
        assertTrue(jogador.isFormado());

        jogador.avancarPorcentagem(0.0);
        assertEquals(100.0, jogador.getPorcentagem());
    }

    @Test
    void historicoDeAcoesRetornaCopiaELimpaCorretamente() {
        Jogador jogador = new Jogador("Leo", 80, 50, 80, 100.0);
        Acao acao1 = new Acao("ESTUDOU", 1, 1, 1.0, 1, 1, 1);
        Acao acao2 = new Acao("FALOU", 0, 0, 0.0, 0, 0, 0);

        jogador.registrarAcao(acao1);
        List<Acao> copia = jogador.getAcoesDoTurno();

        assertEquals(1, copia.size());
        copia.clear();
        assertEquals(1, jogador.getAcoesDoTurno().size());

        jogador.limparAcoesDoTurno();
        assertTrue(jogador.getAcoesDoTurno().isEmpty());

        jogador.registrarAcao(acao2);
        assertEquals(1, jogador.getAcoesDoTurno().size());
        assertEquals("FALOU", jogador.getAcoesDoTurno().get(0).getTipo());
    }

    @Test
    void gameOverEMotivos() {
        Jogador porSaude = new Jogador("A", 0, 50, 10, 10.0);
        assertTrue(porSaude.isGameOver());
        assertTrue(porSaude.motivoGameOver().contains("saude"));

        Jogador porMotivacao = new Jogador("B", 10, 50, 0, 10.0);
        assertTrue(porMotivacao.isGameOver());
        assertTrue(porMotivacao.motivoGameOver().contains("motivacao"));

        Jogador porDinheiro = new Jogador("C", 10, 50, 10, 0.0);
        assertTrue(porDinheiro.isGameOver());
        assertTrue(porDinheiro.motivoGameOver().contains("dinheiro"));

        Jogador ok = new Jogador("D", 10, 50, 10, 1.0);
        assertFalse(ok.isGameOver());
        assertEquals("", ok.motivoGameOver());
    }

    @Test
    void toStringContemCamposPrincipaisEFormatacao() {
        Jogador jogador = new Jogador("Leo", 80, 50, 70, 123.456);
        jogador.avancarPorcentagem(12.34);

        String texto = jogador.toString();

        assertTrue(texto.contains("Nome       : Leo"));
        assertTrue(texto.contains("Energia    : 50/70"));
        assertTrue(texto.contains("EnergiaDia : 50/70"));
        assertTrue(texto.contains("Saude      : 80/100"));
        assertTrue(texto.contains("Motivacao  : 70/100"));
        assertTrue(texto.matches("(?s).*Dinheiro\\s+: R\\$ \\d+[\\.,]\\d{2}.*"));
        assertTrue(texto.matches("(?s).*Conclusao\\s+: \\d+[\\.,]\\d%.*"));
    }

    @Test
    void isFormadoRetornaTrueQuandoPorcentagemAcimaDeCem() {
        Jogador jogador = new Jogador("Leo", 80, 50, 80, 100.0);
        jogador.porcentagem_curso = 120.0;

        assertTrue(jogador.isFormado());
    }
}