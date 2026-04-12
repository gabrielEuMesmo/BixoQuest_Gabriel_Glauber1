package model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class AcaoTest {

    @Test
    void construtorNormalInicializaTipoEAtributos() {
        Acao acao = new Acao("ESTUDOU", 1, 2, 3.0, 4, 5, 6);

        assertEquals("ESTUDOU", acao.getTipo());
        assertEquals(1, acao.getSaldoSaude());
        assertEquals(2, acao.getSaldoMotivacao());
        assertEquals(3.0, acao.getSaldoDinheiro());
        assertEquals(4, acao.getSaldoDes_acad_m1());
        assertEquals(5, acao.getSaldoDes_acad_m2());
        assertEquals(6, acao.getSaldoDes_acad_m3());
        assertThrows(IndexOutOfBoundsException.class, acao::getPersonagem);
    }

    @Test
    void construtorCopiaReplicaDadosEListaIndependente() {
        Acao original = new Acao("FALOU", 7, 8, 9.0, 1, 2, 3);
        original.addNome("Suco");

        Acao copia = new Acao(original);
        copia.addNome("Maeli");

        assertEquals("FALOU", copia.getTipo());
        assertEquals(7, copia.getSaldoSaude());
        assertEquals(8, copia.getSaldoMotivacao());
        assertEquals(9.0, copia.getSaldoDinheiro());
        assertEquals(1, copia.getSaldoDes_acad_m1());
        assertEquals(2, copia.getSaldoDes_acad_m2());
        assertEquals(3, copia.getSaldoDes_acad_m3());

        assertEquals("Suco", original.getPersonagem());
        assertTrue(copia.toString().contains("Suco"));
        assertTrue(copia.toString().contains("Maeli"));
        assertFalse(original.toString().contains("Maeli"));
    }

    @Test
    void addNomeEGetPersonagemFuncionam() {
        Acao acao = new Acao("FALOU", 0, 0, 0.0, 0, 0, 0);

        acao.addNome("Suco");
        assertEquals("Suco", acao.getPersonagem());

        assertDoesNotThrow(() -> {
            acao.addNome("Maeli");
            acao.addNome("Caramelo");
        });
    }

    @Test
    void toStringSemNomesNaoIncluiComEMostraSaldos() {
        Acao acao = new Acao("DESCANSOU", 0, 0, 0.0, 0, 0, 0);

        String texto = acao.toString();

        assertFalse(texto.contains(" com "));
        assertTrue(texto.contains("Saldo de Saude:0"));
        assertTrue(texto.contains("Saldo de Motivacao:0"));
        assertTrue(texto.contains("Saldo de Dinheiro:0.0"));
        assertTrue(texto.contains("Saldo de Desempenho academico EXA:0"));
        assertTrue(texto.contains("Saldo de Desempenho academico TEC0"));
        assertTrue(texto.contains("Saldo de Desempenho academico ALG:0"));
    }

    @Test
    void toStringComUmOuMultiplosNomesFormataCorretamente() {
        Acao comUmNome = new Acao("FALOU", 0, 0, 0.0, 0, 0, 0);
        comUmNome.addNome("Suco");
        String textoUm = comUmNome.toString();
        assertTrue(textoUm.contains("FALOU com Suco."));

        Acao comMultiplos = new Acao("FALOU", 0, 0, 0.0, 0, 0, 0);
        comMultiplos.addNome("Suco");
        comMultiplos.addNome("Maeli");
        comMultiplos.addNome("Caramelo");

        String textoVarios = comMultiplos.toString();
        assertTrue(textoVarios.contains("FALOU com Suco, Maeli, Caramelo."));
        assertFalse(textoVarios.contains(", ."));
    }
}