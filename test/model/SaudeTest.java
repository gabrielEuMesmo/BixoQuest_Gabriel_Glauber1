package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SaudeTest {

    @Test
    void mostrarInicialDeveTrazerTituloEOpcoesEsperadas() {
        Saude saude = new Saude(new Jogador("Bixo", 10, 10, 10, 100));

        assertEquals("Saude", saude.mostrar().getTitulo());
        assertEquals(List.of("Exercicios", "Alimentacao"), saude.mostrar().getOpcoes());
        assertTrue(saude.getOpcao("1").getOpcoes().isEmpty());
        assertEquals(List.of("Comer comidas saudaveis", "Voltar"), saude.getOpcao("2").getOpcoes());
    }

    @Test
    void executarAcaoDeSaudeViaMenuDeveAdicionarAcao() {
        Saude saude = new Saude(new Jogador("Bixo", 10, 10, 10, 100));

        boolean continua = saude.escolher(1);

        assertFalse(continua);
        assertEquals(1, saude.getAcoes().size());
    }
}

