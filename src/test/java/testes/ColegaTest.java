package testes;

import model.Acao;
import model.Colega;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ColegaTest {

    @Test
    void conversarDeveAplicarBonusDeCargoEAtualizarRelacionamento() {
        Colega colega = new Colega("Lia", 2, true, "EXA", 3, 4, 5);

        Acao acao = colega.conversar();

        assertEquals("Conversou", acao.getTipo());
        assertEquals(0, acao.getSaldoSaude());
        assertEquals(0, acao.getSaldoMotivacao());
        assertEquals(6, acao.getSaldoDes_acad_m1());
        assertEquals(4, acao.getSaldoDes_acad_m2());
        assertEquals(5, acao.getSaldoDes_acad_m3());
        assertEquals(1, colega.getRelacionamento());
        assertTrue(colega.getConversou());
    }

    @Test
    void atualizarDeveDiminuirRelacionamentoQuandoNaoConversou() {
        Colega colega = new Colega("Nina", 1, false, "", 1, 1, 1);

        colega.atualizar();

        assertEquals(-1, colega.getRelacionamento());
        assertFalse(colega.getConversou());
    }

    @Test
    void atualizarNaoDevePunirDepoisDeConversaEDeveResetarEstado() {
        Colega colega = new Colega("Teo", 3, false, "", 2, 2, 2);
        colega.conversar();

        colega.atualizar();

        assertEquals(1, colega.getRelacionamento());
        assertFalse(colega.getConversou());
    }

    @Test
    void atualizarSemestreDeveRefletirNoToString() {
        Colega colega = new Colega("Iris", 4, false, "", 1, 2, 3);

        colega.atualizarSemestre();

        assertTrue(colega.toString().contains("Semestre: 5"));
        assertTrue(colega.toString().contains("Conhecimento EXA: 1"));
    }
}
