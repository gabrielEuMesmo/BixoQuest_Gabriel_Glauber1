package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FinanceiroTest {

    @Test
    void mostrarInicialDeveTrazerTituloEOpcoesEsperadas() {
        Financeiro financeiro = new Financeiro(new Jogador("Bixo", 10, 10, 10, 100));

        assertEquals("Financeiro", financeiro.mostrar().getTitulo());
        assertEquals(List.of("Fazer Freelancer"), financeiro.mostrar().getOpcoes());
        assertTrue(financeiro.getOpcao("1").getOpcoes().isEmpty());
    }

    @Test
    void executarAcaoFinanceiraViaMenuDeveAdicionarAcao() {
        Financeiro financeiro = new Financeiro(new Jogador("Bixo", 10, 10, 10, 100));

        boolean continua = financeiro.escolher(1);

        assertFalse(continua);
        assertEquals(1, financeiro.getAcoes().size());
    }
}

