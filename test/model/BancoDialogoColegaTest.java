package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BancoDialogoColegaTest {

    @Test
    void criarSucoRetornaColegaValidoComRoteiro() {
        ColegaDia colega = BancoDialogoColega.criarSuco();

        assertNotNull(colega);
        assertEquals("Suco", colega.getNome());
        assertEquals("ALG", colega.getArea());
        assertEquals(3, colega.getSemestre());
        assertEquals(0, colega.getNivelRelacionamento());

        CenaDialogo cenaAtual = colega.getCenaAtual();
        assertNotNull(cenaAtual);
        assertNotNull(cenaAtual.getFalaPrincipal());
        assertFalse(cenaAtual.getOpcoes().isEmpty());
    }

    @Test
    void criarMaeliRetornaColegaValidoComRoteiro() {
        ColegaDia colega = BancoDialogoColega.criarMaeli();

        assertNotNull(colega);
        assertEquals("Maeli", colega.getNome());
        assertEquals("EXA", colega.getArea());
        assertEquals(1, colega.getSemestre());
        assertEquals(0, colega.getNivelRelacionamento());

        CenaDialogo cenaAtual = colega.getCenaAtual();
        assertNotNull(cenaAtual);
        assertNotNull(cenaAtual.getFalaPrincipal());
        assertFalse(cenaAtual.getOpcoes().isEmpty());
    }

    @Test
    void criarCarameloRetornaColegaValidoComRoteiro() {
        ColegaDia colega = BancoDialogoColega.criarCaramelo();

        assertNotNull(colega);
        assertEquals("Caramelo", colega.getNome());
        assertEquals("GERAL", colega.getArea());
        assertEquals(0, colega.getSemestre());
        assertEquals(0, colega.getNivelRelacionamento());

        CenaDialogo cenaAtual = colega.getCenaAtual();
        assertNotNull(cenaAtual);
        assertNotNull(cenaAtual.getFalaPrincipal());
        assertFalse(cenaAtual.getOpcoes().isEmpty());
    }
}