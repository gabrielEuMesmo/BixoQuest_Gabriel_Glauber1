package model;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ConstrutorDeMapaTest {

    @Test
    void gerarMapaUefsRetornaMapaComLocaisEsperados() {
        Map<String, Local> mapa = ConstrutorDeMapa.gerarMapaUefs();

        assertNotNull(mapa);
        assertFalse(mapa.isEmpty());
        assertTrue(mapa.containsKey("Cantina do M5"));
        assertTrue(mapa.containsKey("PATs do M5"));
        assertTrue(mapa.containsKey("DA de Computacao"));
        assertTrue(mapa.containsKey("Biblioteca Central"));
        assertTrue(mapa.containsKey("Colegiado de Computacao"));
        assertTrue(mapa.containsKey("Salas do M1"));
        assertTrue(mapa.containsKey("Praca Central"));
    }

    @Test
    void nomesDosLocaisBatemComChavesDoMapaEBuscaPorString() {
        Map<String, Local> mapa = ConstrutorDeMapa.gerarMapaUefs();

        Local cantina = mapa.get("Cantina do M5");
        assertNotNull(cantina);
        assertEquals("Cantina do M5", cantina.getNome());

        Local pats = mapa.get("PATs do M5");
        assertNotNull(pats);
        assertEquals("PATs do M5", pats.getNome());

        assertNull(mapa.get("cantina do m5"));
    }
}