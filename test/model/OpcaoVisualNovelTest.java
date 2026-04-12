package model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OpcaoVisualNovelTest {

    @Test
    void construtorSemNomeLocalRealFuncionaENullEmTextosViraListaVazia() {
        List<String> escolhas = Arrays.asList("A", "B");
        OpcaoVisualNovel opcao = new OpcaoVisualNovel("Portao", escolhas, false);

        assertEquals("Portao", opcao.getTituloLocal());
        assertEquals(escolhas, opcao.getTextosEscolhas());
        assertFalse(opcao.isAcaoCritica());
        assertNull(opcao.getNomeLocalReal());
        assertEquals(opcao.getTitulo(), opcao.getTituloLocal());

        OpcaoVisualNovel comListaNula = new OpcaoVisualNovel("Modulo", null, true);
        assertNotNull(comListaNula.getTextosEscolhas());
        assertTrue(comListaNula.getTextosEscolhas().isEmpty());
    }

    @Test
    void construtorComNomeLocalRealArmazenaTodosCampos() {
        List<String> escolhas = Collections.singletonList("Interagir");
        OpcaoVisualNovel opcao = new OpcaoVisualNovel("Cantina", escolhas, true, "Cantina do M5");

        assertEquals("Cantina", opcao.getTituloLocal());
        assertEquals(escolhas, opcao.getTextosEscolhas());
        assertTrue(opcao.isAcaoCritica());
        assertEquals("Cantina do M5", opcao.getNomeLocalReal());
        assertEquals(opcao.getTitulo(), opcao.getTituloLocal());
    }
}