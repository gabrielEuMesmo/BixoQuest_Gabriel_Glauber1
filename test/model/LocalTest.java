package model;

import model.enums.BlocoTempo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class LocalTest {

    private Professor npc(String nome) {
        CenaDialogo cena = new CenaDialogo("fala", Collections.singletonList(
                new OpcaoResposta("resp", 0, 0, 0, 0, 0.0, 0, "ok")
        ));
        return new Professor(nome, "GERAL", Collections.singletonList(cena));
    }

    @Test
    void construtorDefineNomeEAgendaComecaVazia() {
        Local local = new Local("Cantina do M5");

        assertEquals("Cantina do M5", local.getNome());
        assertTrue(local.getPersonagensNoTurno(BlocoTempo.MANHA_1).isEmpty());
        assertTrue(local.estaVazioNoTurno(BlocoTempo.MANHA_1));
    }

    @Test
    void registrarPresencaEGetsPorTurnoFuncionam() {
        Local local = new Local("Praca Central");
        NPC suco = npc("Suco");
        NPC maeli = npc("Maeli");

        assertDoesNotThrow(() -> local.registrarPresenca(BlocoTempo.MANHA_1, suco));

        local.registrarPresenca(BlocoTempo.MANHA_1, maeli);
        local.registrarPresenca(BlocoTempo.TARDE_1, suco);

        assertEquals(2, local.getPersonagensNoTurno(BlocoTempo.MANHA_1).size());
        assertTrue(local.getPersonagensNoTurno(BlocoTempo.MANHA_1).containsAll(Arrays.asList(suco, maeli)));
        assertEquals(1, local.getPersonagensNoTurno(BlocoTempo.TARDE_1).size());
        assertTrue(local.getPersonagensNoTurno(BlocoTempo.TARDE_1).contains(suco));

        assertFalse(local.estaVazioNoTurno(BlocoTempo.MANHA_1));
        assertTrue(local.estaVazioNoTurno(BlocoTempo.NOITE));
    }

    @Test
    void getPersonagensNoTurnoSemRegistroRetornaListaVaziaENaoNula() {
        Local local = new Local("Biblioteca Central");

        assertNotNull(local.getPersonagensNoTurno(BlocoTempo.NOITE));
        assertTrue(local.getPersonagensNoTurno(BlocoTempo.NOITE).isEmpty());
    }

    @Test
    void getTodosPersonagensRetornaSemDuplicatas() {
        Local local = new Local("Area Central");
        NPC suco = npc("Suco");
        NPC caramelo = npc("Caramelo");

        local.registrarPresenca(BlocoTempo.MANHA_1, suco);
        local.registrarPresenca(BlocoTempo.TARDE_1, suco);
        local.registrarPresenca(BlocoTempo.NOITE, caramelo);

        assertEquals(2, local.getTodosPersonagens().size());
        assertTrue(local.getTodosPersonagens().contains(suco));
        assertTrue(local.getTodosPersonagens().contains(caramelo));
    }
}