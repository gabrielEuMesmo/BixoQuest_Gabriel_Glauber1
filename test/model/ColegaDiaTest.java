package model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ColegaDiaTest {

    private List<CenaDialogo> roteiroDuasCenas() {
        CenaDialogo c0 = new CenaDialogo("cena0", Collections.singletonList(new OpcaoResposta("a", 1, 1, 1, 1, 1.0, 1, "r")));
        CenaDialogo c1 = new CenaDialogo("cena1", Collections.singletonList(new OpcaoResposta("b", 1, 1, 1, 1, 1.0, 1, "r")));
        return Arrays.asList(c0, c1);
    }

    @Test
    void construtorArmazenaCamposIniciais() {
        ColegaDia colega = new ColegaDia("Suco", "ALG", 3, roteiroDuasCenas());

        assertEquals("Suco", colega.getNome());
        assertEquals("ALG", colega.getArea());
        assertEquals(3, colega.getSemestre());
        assertEquals(0, colega.getNivelRelacionamento());
    }

    @Test
    void aumentarRelacionamentoRespeitaRegras() {
        ColegaDia colega = new ColegaDia("Maeli", "EXA", 1, roteiroDuasCenas());

        colega.aumentarRelacionamento(2);
        assertEquals(2, colega.getNivelRelacionamento());

        colega.aumentarRelacionamento(-1);
        assertEquals(1, colega.getNivelRelacionamento());

        colega.aumentarRelacionamento(-10);
        assertEquals(0, colega.getNivelRelacionamento());

        colega.aumentarRelacionamento(0);
        assertEquals(0, colega.getNivelRelacionamento());

        colega.aumentarRelacionamento(1);
        colega.aumentarRelacionamento(1);
        assertEquals(2, colega.getNivelRelacionamento());
    }

    @Test
    void getCenaAtualRetornaCenaCorretaOuUltimaQuandoUltrapassa() {
        List<CenaDialogo> roteiro = roteiroDuasCenas();
        ColegaDia colega = new ColegaDia("Suco", "ALG", 3, roteiro);

        assertSame(roteiro.get(0), colega.getCenaAtual());

        colega.aumentarRelacionamento(1);
        assertSame(roteiro.get(1), colega.getCenaAtual());

        colega.aumentarRelacionamento(1);
        assertSame(roteiro.get(1), colega.getCenaAtual());

        colega.aumentarRelacionamento(10);
        assertSame(roteiro.get(1), colega.getCenaAtual());
    }

    @Test
    void integracaoNpcCenaDialogoOpcaoResposta() {
        List<CenaDialogo> roteiro = roteiroDuasCenas();
        ColegaDia colega = new ColegaDia("Suco", "ALG", 3, roteiro);

        assertEquals("cena0", colega.getCenaAtual().getFalaPrincipal());

        colega.aumentarRelacionamento(1);
        assertEquals("cena1", colega.getCenaAtual().getFalaPrincipal());

        colega.aumentarRelacionamento(10);
        assertEquals("cena1", colega.getCenaAtual().getFalaPrincipal());
    }
}