package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OpcaoTest {

    @Test
    void construtorDefineTituloEListaVazia() {
        Opcao opcao = new Opcao("Locais");

        assertEquals("Locais", opcao.getTitulo());
        assertNotNull(opcao.getOpcoes());
        assertTrue(opcao.getOpcoes().isEmpty());
    }

    @Test
    void addOpcaoEGettersFuncionamEIndiceInvalidoLancaExcecao() {
        Opcao opcao = new Opcao("Modulos");

        opcao.addOpcao("Modulo 5");
        opcao.addOpcao("Modulo 1");
        assertEquals(2, opcao.getOpcoes().size());
        assertEquals("Modulo 5", opcao.getOpcoes().get(0));
        assertEquals("Modulo 1", opcao.getOpcoes().get(1));
        assertEquals("Modulo 5", opcao.getOpcao(0));
        assertThrows(IndexOutOfBoundsException.class, () -> opcao.getOpcao(10));
    }

    @Test
    void construtorComListaMantemCopiadaAListaOriginal() {
        ArrayList<String> opcoes = new ArrayList<>();
        opcoes.add("Social");
        opcoes.add("Saude");

        Opcao opcao = new Opcao("Menu", opcoes);

        opcoes.add("Academico");

        assertEquals("Menu", opcao.getTitulo());
        assertEquals(2, opcao.getOpcoes().size());
        assertEquals("Social", opcao.getOpcao(0));
        assertEquals("Saude", opcao.getOpcao(1));
    }
}