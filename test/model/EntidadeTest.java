package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class EntidadeTest {

    @Test
    void construtorDeveSalvarNomeCorretamente() {
        Entidade entidade = new EntidadeFake("Maria");

        assertEquals("Maria", entidade.getNome());
    }

    @Test
    void deveAceitarNomeComEspacos() {
        Entidade entidade = new EntidadeFake("Ana Clara");

        assertEquals("Ana Clara", entidade.getNome());
    }

    @Test
    void deveAceitarNomeVazioNoComportamentoAtual() {
        Entidade entidade = new EntidadeFake("");

        assertEquals("", entidade.getNome());
    }

    @Test
    void deveAceitarNullNoComportamentoAtual() {
        Entidade entidade = new EntidadeFake(null);

        assertNull(entidade.getNome());
    }

    private static class EntidadeFake extends Entidade {
        private EntidadeFake(String nomeNovo) {
            super(nomeNovo);
        }
    }
}

