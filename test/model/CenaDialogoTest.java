package model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CenaDialogoTest {

    @Test
    void construtorValidaFalaNulaVaziaOuEspacos() {
        CenaDialogo nula = new CenaDialogo(null, null);
        CenaDialogo vazia = new CenaDialogo("", null);
        CenaDialogo espacos = new CenaDialogo("   ", null);

        assertEquals("... (silencio constrangedor)", nula.getFalaPrincipal());
        assertEquals("... (silencio constrangedor)", vazia.getFalaPrincipal());
        assertEquals("... (silencio constrangedor)", espacos.getFalaPrincipal());
    }

    @Test
    void construtorAceitaFalaValidaEListaNulaViraVazia() {
        CenaDialogo cena = new CenaDialogo("Ola", null);

        assertEquals("Ola", cena.getFalaPrincipal());
        assertNotNull(cena.getOpcoes());
        assertTrue(cena.getOpcoes().isEmpty());
        assertEquals(0, cena.getTotalOpcoes());
    }

    @Test
    void gettersRetornamValoresCorretos() {
        OpcaoResposta o1 = new OpcaoResposta("a", 1, 1, 1, 1, 1.0, 1, "r1");
        OpcaoResposta o2 = new OpcaoResposta("b", 0, 0, 0, 0, 0.0, 0, "r2");
        List<OpcaoResposta> opcoes = Arrays.asList(o1, o2);

        CenaDialogo cena = new CenaDialogo("Fala valida", opcoes);

        assertEquals("Fala valida", cena.getFalaPrincipal());
        assertSame(opcoes, cena.getOpcoes());
        assertEquals(2, cena.getTotalOpcoes());
    }
}