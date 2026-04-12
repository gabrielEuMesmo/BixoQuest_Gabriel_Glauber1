package model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SemanaEnumTest {

    @Test
    void isProvaRetornaValoresEsperados() {
        assertTrue(SemanaEnum.PROVA_M1.isProva());
        assertTrue(SemanaEnum.PROVA_M2.isProva());
        assertTrue(SemanaEnum.PROVA_M3.isProva());
        assertFalse(SemanaEnum.LIVRE.isProva());
        assertFalse(SemanaEnum.FECHAMENTO.isProva());
    }

    @Test
    void exibirRetornaTextoCorretoENuncaNulo() {
        assertEquals("Semana Livre", SemanaEnum.LIVRE.exibir());
        assertEquals("Semana de Prova (M1)", SemanaEnum.PROVA_M1.exibir());
        assertEquals("Semana de Prova (M2)", SemanaEnum.PROVA_M2.exibir());
        assertEquals("Semana de Prova (M3)", SemanaEnum.PROVA_M3.exibir());
        assertEquals("Fechamento do Semestre", SemanaEnum.FECHAMENTO.exibir());

        for (SemanaEnum semana : SemanaEnum.values()) {
            String texto = semana.exibir();
            assertNotNull(texto);
            assertFalse(texto.trim().isEmpty());
        }
    }
}