package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BancoDialogoProfessorTest {

    @Test
    void criarAnfranseraiRetornaProfessorValidoComRoteiro() {
        Professor professor = BancoDialogoProfessor.criarAnfranserai();

        assertNotNull(professor);
        assertEquals("Anfranserai", professor.getNome());
        assertEquals("TEC", professor.getArea());
        assertEquals(0, professor.getNivelRelacionamento());

        CenaDialogo cenaAtual = professor.getCenaAtual();
        assertNotNull(cenaAtual);
        assertNotNull(cenaAtual.getFalaPrincipal());
        assertFalse(cenaAtual.getOpcoes().isEmpty());
    }

    @Test
    void criarRogerioRetornaProfessorValidoComRoteiro() {
        Professor professor = BancoDialogoProfessor.criarRogerio();

        assertNotNull(professor);
        assertEquals("Rogerio", professor.getNome());
        assertEquals("EXA", professor.getArea());
        assertEquals(0, professor.getNivelRelacionamento());

        CenaDialogo cenaAtual = professor.getCenaAtual();
        assertNotNull(cenaAtual);
        assertNotNull(cenaAtual.getFalaPrincipal());
        assertFalse(cenaAtual.getOpcoes().isEmpty());
    }
}