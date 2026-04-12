package model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlocoTempoTest {

    @Test
    void proximoSegueSequenciaCorretaEAposNoiteRetornaNull() {
        assertEquals(BlocoTempo.MANHA_2, BlocoTempo.MANHA_1.proximo());
        assertEquals(BlocoTempo.TARDE_1, BlocoTempo.MANHA_2.proximo());
        assertEquals(BlocoTempo.TARDE_2, BlocoTempo.TARDE_1.proximo());
        assertEquals(BlocoTempo.NOITE, BlocoTempo.TARDE_2.proximo());
        assertNull(BlocoTempo.NOITE.proximo());
    }

    @Test
    void exibirRetornaTextoNaoNuloENaoVazioParaTodasConstantes() {
        for (BlocoTempo bloco : BlocoTempo.values()) {
            String texto = bloco.exibir();
            assertNotNull(texto);
            assertFalse(texto.trim().isEmpty());
        }

        assertEquals("Manha (inicio)", BlocoTempo.MANHA_1.exibir());
        assertEquals("Manha (tarde)", BlocoTempo.MANHA_2.exibir());
        assertEquals("Tarde (inicio)", BlocoTempo.TARDE_1.exibir());
        assertEquals("Tarde (fim)", BlocoTempo.TARDE_2.exibir());
        assertEquals("Noite", BlocoTempo.NOITE.exibir());
    }
}