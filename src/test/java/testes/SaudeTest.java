package testes;

import model.Jogador;
import model.Opcao;
import model.Saude;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SaudeTest {

    @Test
    void saudeDeveMostrarOpcaoInicial() {
        Saude saude = new Saude(new Jogador("Bixo", 10, 10, 10, 100));

        Opcao opcao = saude.mostrar();

        assertNotNull(opcao);
        assertEquals("Saude", opcao.getTitulo());
        assertEquals(List.of("Comer comidas saudaveis", "Voltar"), opcao.getOpcoes());
    }
}
