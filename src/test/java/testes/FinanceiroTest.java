package testes;

import model.Financeiro;
import model.Jogador;
import model.Opcao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FinanceiroTest {

    @Test
    void financeiroDeveMostrarOpcaoInicial() {
        Financeiro financeiro = new Financeiro(new Jogador("Bixo", 10, 10, 10, 100));

        Opcao opcao = financeiro.mostrar();

        assertNotNull(opcao);
        assertEquals("Financeiro", opcao.getTitulo());
        assertTrue(opcao.getOpcoes().isEmpty());
    }
}
