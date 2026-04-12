package model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProfessorTest {

    private List<CenaDialogo> roteiroDuasCenas() {
        CenaDialogo c0 = new CenaDialogo("cena0", Collections.singletonList(new OpcaoResposta("a", 1, 1, 1, 1, 1.0, 1, "r")));
        CenaDialogo c1 = new CenaDialogo("cena1", Collections.singletonList(new OpcaoResposta("b", 1, 1, 1, 1, 1.0, 1, "r")));
        return Arrays.asList(c0, c1);
    }

    @Test
    void construtorArmazenaCamposIniciais() {
        Professor professor = new Professor("Rogerio", "EXA", roteiroDuasCenas());

        assertEquals("Rogerio", professor.getNome());
        assertEquals("EXA", professor.getArea());
        assertEquals(0, professor.getNivelRelacionamento());
    }

    @Test
    void aumentarRelacionamentoRespeitaRegras() {
        Professor professor = new Professor("Anfranserai", "TEC", roteiroDuasCenas());

        professor.aumentarRelacionamento(2);
        assertEquals(2, professor.getNivelRelacionamento());

        professor.aumentarRelacionamento(-1);
        assertEquals(1, professor.getNivelRelacionamento());

        professor.aumentarRelacionamento(-10);
        assertEquals(0, professor.getNivelRelacionamento());

        professor.aumentarRelacionamento(0);
        assertEquals(0, professor.getNivelRelacionamento());

        professor.aumentarRelacionamento(1);
        professor.aumentarRelacionamento(1);
        assertEquals(2, professor.getNivelRelacionamento());
    }

    @Test
    void getCenaAtualRetornaCenaCorretaOuUltimaQuandoUltrapassa() {
        List<CenaDialogo> roteiro = roteiroDuasCenas();
        Professor professor = new Professor("Rogerio", "EXA", roteiro);

        assertSame(roteiro.get(0), professor.getCenaAtual());

        professor.aumentarRelacionamento(1);
        assertSame(roteiro.get(1), professor.getCenaAtual());

        professor.aumentarRelacionamento(1);
        assertSame(roteiro.get(1), professor.getCenaAtual());

        professor.aumentarRelacionamento(10);
        assertSame(roteiro.get(1), professor.getCenaAtual());
    }

    @Test
    void integracaoNpcCenaDialogoOpcaoResposta() {
        List<CenaDialogo> roteiro = roteiroDuasCenas();
        Professor professor = new Professor("Rogerio", "EXA", roteiro);

        assertEquals("cena0", professor.getCenaAtual().getFalaPrincipal());

        professor.aumentarRelacionamento(1);
        assertEquals("cena1", professor.getCenaAtual().getFalaPrincipal());

        professor.aumentarRelacionamento(10);
        assertEquals("cena1", professor.getCenaAtual().getFalaPrincipal());
    }
}