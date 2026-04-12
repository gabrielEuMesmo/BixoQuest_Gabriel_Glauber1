package testes;

import model.Academico;
import model.Jogador;
import model.Opcao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AcademicoTest {

    @Test
    void academicoDeveMostrarOpcaoInicial() {
        Academico academico = new Academico(new Jogador("Bixo", 10, 10, 10, 100));

        Opcao opcao = academico.mostrar();

        assertNotNull(opcao);
        assertEquals("Academico", opcao.getTitulo());
        assertTrue(opcao.getOpcoes().isEmpty());
    }
}
