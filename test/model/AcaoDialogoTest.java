package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AcaoDialogoTest {

    @Test
    void construtorArmazenaCamposEHeranca() {
        AcaoDialogo acao = new AcaoDialogo(
                "FALOU", "Suco", -5, 10, 100.0, -3,
                15, 12, 8, "Oi", "Salve"
        );

        assertEquals("Oi", acao.getTextoPlayer());
        assertEquals("Salve", acao.getReacaoNPC());
        assertEquals(-5, acao.getSaldoEnergiaDia());
        assertEquals("Suco", acao.getPersonagem());

        assertEquals(10, acao.getSaldoSaude());
        assertEquals(100.0, acao.getSaldoDinheiro());
        assertEquals(-3, acao.getSaldoMotivacao());
        assertEquals(15, acao.getSaldoDes_acad_m1());
        assertEquals(12, acao.getSaldoDes_acad_m2());
        assertEquals(8, acao.getSaldoDes_acad_m3());
    }

    @Test
    void aplicarJogadorAplicaTodosEfeitosSemInterferencia() {
        Jogador jogador = new Jogador("Gabriel", 70, 50, 60, 200.0);

        AcaoDialogo acao = new AcaoDialogo(
                "FALOU", "Suco", -5, 10, 100.0, -3,
                15, 12, 8, "Oi", "Salve"
        );

        acao.aplicarJogador(jogador);

        assertEquals(45, jogador.getEnergiaDia());
        assertEquals(80, jogador.getSaude());
        assertEquals(57, jogador.getMotivacao());
        assertEquals(300.0, jogador.getDinheiro());
        assertEquals(15, jogador.getDesempenho_EXA());
        assertEquals(12, jogador.getDesempenho_TEC());
        assertEquals(8, jogador.getDesempenho_ALG());
    }

    @Test
    void aplicarJogadorRespeitaLimitesDeSaudeEEnergiaDia() {
        Jogador jogador = new Jogador("Ana", 95, 2, 80, 100.0);

        AcaoDialogo acao = new AcaoDialogo(
                "FALOU", "Maeli", -10, 20, 0.0, 0,
                0, 0, 0, "texto", "reacao"
        );

        acao.aplicarJogador(jogador);

        assertEquals(0, jogador.getEnergiaDia());
        assertEquals(100, jogador.getSaude());
    }

    @Test
    void toStringContemBaseTextoReacaoENomeNpc() {
        AcaoDialogo acao = new AcaoDialogo(
                "FALOU", "Caramelo", -3, 0, 0.0, 0,
                0, 0, 0, "Fazer carinho", "*late feliz*"
        );

        String texto = acao.toString();

        assertTrue(texto.contains("FALOU com Caramelo."));
        assertTrue(texto.contains("\"Fazer carinho\""));
        assertTrue(texto.contains("Caramelo respondeu"));
        assertTrue(texto.contains("\"*late feliz*\""));
    }
}