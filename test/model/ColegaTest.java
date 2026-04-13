package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ColegaTest {

    @Test
    void conversarSemCargoDeveUsarBonusBase() {
        Colega colega = new Colega("Ana", 2, false, "", 3, 4, 5);

        Acao acao = colega.conversar();

        assertEquals("Conversou", acao.getTipo());
        assertEquals(3, acao.getSaldoDes_acad_m1());
        assertEquals(4, acao.getSaldoDes_acad_m2());
        assertEquals(5, acao.getSaldoDes_acad_m3());
        assertTrue(colega.getConversou());
        assertEquals(1, colega.getRelacionamento());
    }

    @Test
    void conversarComCargoExaDeveDobrarApenasExa() {
        Colega colega = new Colega("Bia", 2, true, "EXA", 2, 3, 4);

        Acao acao = colega.conversar();

        assertEquals(4, acao.getSaldoDes_acad_m1());
        assertEquals(3, acao.getSaldoDes_acad_m2());
        assertEquals(4, acao.getSaldoDes_acad_m3());
    }

    @Test
    void conversarComCargoTecDeveDobrarApenasTec() {
        Colega colega = new Colega("Cris", 2, true, "TEC", 2, 3, 4);

        Acao acao = colega.conversar();

        assertEquals(2, acao.getSaldoDes_acad_m1());
        assertEquals(6, acao.getSaldoDes_acad_m2());
        assertEquals(4, acao.getSaldoDes_acad_m3());
    }

    @Test
    void conversarComCargoAlgDeveDobrarApenasAlg() {
        Colega colega = new Colega("Duda", 2, true, "ALG", 2, 3, 4);

        Acao acao = colega.conversar();

        assertEquals(2, acao.getSaldoDes_acad_m1());
        assertEquals(3, acao.getSaldoDes_acad_m2());
        assertEquals(8, acao.getSaldoDes_acad_m3());
    }

    @Test
    void relacionamentoNaoDeveUltrapassarCinco() {
        Colega colega = new Colega("Eva", 2, false, "", 1, 1, 1);

        for (int i = 0; i < 8; i++) {
            colega.conversar();
            colega.atualizar();
        }

        assertEquals(5, colega.getRelacionamento());
    }

    @Test
    void atualizarDeveDiminuirRelacionamentoQuandoNaoConversou() {
        Colega colega = new Colega("Fabi", 1, false, "", 1, 1, 1);

        colega.atualizar();

        assertEquals(-1, colega.getRelacionamento());
        assertFalse(colega.getConversou());
    }

    @Test
    void atualizarNaoDeveDiminuirQuandoConversouEMesmoAssimResetaFlag() {
        Colega colega = new Colega("Gabi", 1, false, "", 1, 1, 1);
        colega.conversar();

        colega.atualizar();

        assertEquals(1, colega.getRelacionamento());
        assertFalse(colega.getConversou());
    }

    @Test
    void atualizarSemestreEToStringDevemRefletirEstado() {
        Colega colega = new Colega("Hugo", 4, true, "TEC", 7, 8, 9);
        colega.atualizarSemestre();

        String texto = colega.toString();

        assertTrue(texto.contains("Hugo"));
        assertTrue(texto.contains("Semestre: 5"));
        assertTrue(texto.contains("Cargo: TEC"));
        assertTrue(texto.contains("Relacionamento: 0"));
        assertTrue(texto.contains("Conhecimento EXA: 7"));
        assertTrue(texto.contains("Conhecimento TEC: 8"));
        assertTrue(texto.contains("Conhecimento ALG: 9"));
    }
}