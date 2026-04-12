package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpcaoRespostaTest {

    @Test
    void construtorCompletoArmazenaTudo() {
        OpcaoResposta opcao = new OpcaoResposta("oi", 2, 15, 5, -3, 100.0, 8, "blz");

        assertEquals("oi", opcao.getTextoPlayer());
        assertEquals(2, opcao.getImpactoRelacionamento());
        assertEquals(15, opcao.getGanhoConhecimento());
        assertEquals(5, opcao.getCustoEnergia());
        assertEquals(-3, opcao.getSaldoMotivacao());
        assertEquals(100.0, opcao.getGanhoDinheiro());
        assertEquals(8, opcao.getSaldoSaude());
        assertEquals("blz", opcao.getReacaoDoNPC());
    }

    @Test
    void construtorSemSaldoSaudeDefineZero() {
        OpcaoResposta opcao = new OpcaoResposta("oi", 1, 10, 4, 2, 7.5, "ok");

        assertEquals(0, opcao.getSaldoSaude());
        assertEquals("oi", opcao.getTextoPlayer());
        assertEquals(1, opcao.getImpactoRelacionamento());
        assertEquals(10, opcao.getGanhoConhecimento());
        assertEquals(4, opcao.getCustoEnergia());
        assertEquals(2, opcao.getSaldoMotivacao());
        assertEquals(7.5, opcao.getGanhoDinheiro());
        assertEquals("ok", opcao.getReacaoDoNPC());
    }

    @Test
    void converterParaAcaoMapeiaAreaECamposBasicos() {
        OpcaoResposta base = new OpcaoResposta("fala", 1, 15, 5, 2, 100.0, 3, "reacao");

        AcaoDialogo exa = base.converterParaAcao("Rogerio", "EXA");
        assertEquals("FALOU", exa.getTipo());
        assertEquals("Rogerio", exa.getPersonagem());
        assertEquals("fala", exa.getTextoPlayer());
        assertEquals("reacao", exa.getReacaoNPC());
        assertEquals(-5, exa.getSaldoEnergiaDia());
        assertEquals(15, exa.getSaldoDes_acad_m1());
        assertEquals(0, exa.getSaldoDes_acad_m2());
        assertEquals(0, exa.getSaldoDes_acad_m3());

        AcaoDialogo tec = base.converterParaAcao("Anfranserai", "TEC");
        assertEquals(0, tec.getSaldoDes_acad_m1());
        assertEquals(15, tec.getSaldoDes_acad_m2());
        assertEquals(0, tec.getSaldoDes_acad_m3());

        AcaoDialogo alg = base.converterParaAcao("Suco", "ALG");
        assertEquals(0, alg.getSaldoDes_acad_m1());
        assertEquals(0, alg.getSaldoDes_acad_m2());
        assertEquals(15, alg.getSaldoDes_acad_m3());

        AcaoDialogo geral = base.converterParaAcao("Caramelo", "GERAL");
        assertEquals(0, geral.getSaldoDes_acad_m1());
        assertEquals(0, geral.getSaldoDes_acad_m2());
        assertEquals(0, geral.getSaldoDes_acad_m3());

        AcaoDialogo nulo = base.converterParaAcao("NPC", null);
        assertEquals(0, nulo.getSaldoDes_acad_m1());
        assertEquals(0, nulo.getSaldoDes_acad_m2());
        assertEquals(0, nulo.getSaldoDes_acad_m3());

        AcaoDialogo minusculo = base.converterParaAcao("Rogerio", "exa");
        assertEquals(15, minusculo.getSaldoDes_acad_m1());
    }

    @Test
    void integracaoOpcaoRespostaAcaoDialogoJogador() {
        Jogador jogador = new Jogador("Leo", 80, 70, 80, 100.0);

        OpcaoResposta opTec = new OpcaoResposta("resp", 1, 15, 5, 0, 0.0, 0, "ok");
        AcaoDialogo acaoTec = opTec.converterParaAcao("Anfranserai", "TEC");
        acaoTec.aplicarJogador(jogador);

        assertEquals(15, jogador.getDesempenho_TEC());
        assertEquals(0, jogador.getDesempenho_EXA());
        assertEquals(0, jogador.getDesempenho_ALG());
        assertEquals(65, jogador.getEnergiaDia());

        OpcaoResposta opExa = new OpcaoResposta("resp", 1, 10, 3, 0, 0.0, 0, "ok");
        opExa.converterParaAcao("Rogerio", "EXA").aplicarJogador(jogador);
        assertEquals(10, jogador.getDesempenho_EXA());

        OpcaoResposta opAlg = new OpcaoResposta("resp", 1, 8, 2, 0, 0.0, 0, "ok");
        opAlg.converterParaAcao("Suco", "ALG").aplicarJogador(jogador);
        assertEquals(8, jogador.getDesempenho_ALG());
    }
}