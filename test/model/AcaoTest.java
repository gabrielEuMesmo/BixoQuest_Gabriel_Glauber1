package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AcaoTest {

    @Test
    void construtorDeveDefinirTipoESaldos() {
        Acao acao = new Acao("Estudou", 1, 2, 3.5, 4, 5, 6);

        assertEquals("Estudou", acao.getTipo());
        assertEquals(1, acao.getSaldoSaude());
        assertEquals(2, acao.getSaldoMotivacao());
        assertEquals(3.5, acao.getSaldoDinheiro());
        assertEquals(4, acao.getSaldoDes_acad_m1());
        assertEquals(5, acao.getSaldoDes_acad_m2());
        assertEquals(6, acao.getSaldoDes_acad_m3());
    }

    @Test
    void construtorDeCopiaDevePreservarTipoSaldosENomes() {
        Acao original = new Acao("Conversou", 0, 1, 2.0, 3, 4, 5);
        original.addNome("Ana");
        original.addNome("Bia");

        Acao copia = new Acao(original);

        assertEquals("Conversou", copia.getTipo());
        assertEquals(0, copia.getSaldoSaude());
        assertEquals(1, copia.getSaldoMotivacao());
        assertEquals(2.0, copia.getSaldoDinheiro());
        assertTrue(copia.toString().contains("com Ana, Bia."));
    }

    @Test
    void copiaDeveSerIndependenteDaListaDeNomesDoOriginal() {
        Acao original = new Acao("Rede", 0, 0, 0.0, 0, 0, 0);
        original.addNome("A");
        Acao copia = new Acao(original);

        original.addNome("B");

        assertTrue(copia.toString().contains("com A."));
        assertFalse(copia.toString().contains("B"));
    }

    @Test
    void addNomeDeveAceitarNomeVazioENullNoComportamentoAtual() {
        Acao acao = new Acao("Teste", 0, 0, 0.0, 0, 0, 0);

        acao.addNome("");
        acao.addNome(null);

        String texto = acao.toString();
        assertTrue(texto.contains("com , null."));
    }

    @Test
    void toStringSemNomesDeveExibirFormatoBasico() {
        Acao acao = new Acao("Dormiu", 1, 2, 3.0, 4, 5, 6);

        String texto = acao.toString();

        assertTrue(texto.startsWith("Dormiu"));
        assertTrue(texto.contains("Saldo de Saude:1"));
        assertTrue(texto.contains("Saldo de Motivacao:2"));
        assertTrue(texto.contains("Saldo de Dinheiro:3.0"));
        assertTrue(texto.contains("Saldo de Desempenho academico EXA:4"));
        assertTrue(texto.contains("Saldo de Desempenho academico TEC5"));
        assertTrue(texto.contains("Saldo de Desempenho academico ALG:6"));
    }

    @Test
    void devePermitirTipoVazioNoComportamentoAtual() {
        Acao acao = new Acao("", 0, 0, 0.0, 0, 0, 0);

        assertEquals("", acao.getTipo());
        assertTrue(acao.toString().startsWith("\nSaldo de Saude:0"));
    }
}