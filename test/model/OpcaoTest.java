package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OpcaoTest {

    @Test
    void construtorDeveClonarListaOriginal() {
        ArrayList<String> base = new ArrayList<>(List.of("A", "B"));
        Opcao opcao = new Opcao("Menu", base);

        base.add("C");

        assertEquals("Menu", opcao.getTitulo());
        assertEquals(List.of("A", "B"), opcao.getOpcoes());
    }

    @Test
    void getOpcoesEAddOpcaoDevemRefletirListaAtual() {
        Opcao opcao = new Opcao("Tela", new ArrayList<>());

        opcao.addOpcao("Primeira");
        opcao.addOpcao("Segunda");

        assertEquals(2, opcao.getOpcoes().size());
        assertEquals("Primeira", opcao.getOpcao(0));
        assertEquals("Segunda", opcao.getOpcao(1));
    }

    @Test
    void getOpcaoComIndiceInvalidoDeveLancarExcecao() {
        Opcao opcao = new Opcao("Tela", new ArrayList<>(List.of("A")));

        assertThrows(IndexOutOfBoundsException.class, () -> opcao.getOpcao(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> opcao.getOpcao(1));
    }

    @Test
    void devePermitirOpcaoComListaVazia() {
        Opcao opcao = new Opcao("Vazia", new ArrayList<>());

        assertEquals("Vazia", opcao.getTitulo());
        assertTrue(opcao.getOpcoes().isEmpty());
    }
}