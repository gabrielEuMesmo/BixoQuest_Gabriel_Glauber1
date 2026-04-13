package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AcademicoTest {

    @Test
    void mostrarInicialDeveTrazerTituloEOpcoesEsperadas() {
        Academico academico = new Academico(new Jogador("Bixo", 10, 10, 10, 100));

        assertEquals("Academico", academico.mostrar().getTitulo());
        assertEquals(List.of("Estudar", "Estagiar"), academico.mostrar().getOpcoes());
        assertTrue(academico.getOpcao("1").getOpcoes().isEmpty());
        assertTrue(academico.getOpcao("2").getOpcoes().isEmpty());
    }

    @Test
    void executarAcaoAcademicaViaMenuDeveAlterarSaldos() {
        Academico academico = new Academico(new Jogador("Bixo", 10, 10, 10, 100));

        boolean continua = academico.escolher(1);

        assertFalse(continua);
        assertEquals(1, academico.getAcoes().size());
    }
}
