package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AtributosTest {

    @Test
    void construtorVazioDeveIniciarTudoComZero() {
        Atributos atributos = new Atributos();

        assertEquals(0, atributos.getSaldoSaude());
        assertEquals(0, atributos.getSaldoMotivacao());
        assertEquals(0.0, atributos.getSaldoDinheiro());
        assertEquals(0, atributos.getSaldoDes_acad_m1());
        assertEquals(0, atributos.getSaldoDes_acad_m2());
        assertEquals(0, atributos.getSaldoDes_acad_m3());
    }

    @Test
    void construtorCompletoDeveSalvarTodosOsCampos() {
        Atributos atributos = new Atributos(1, 2, 3.5, 4, 5, 6);

        assertEquals(1, atributos.getSaldoSaude());
        assertEquals(2, atributos.getSaldoMotivacao());
        assertEquals(3.5, atributos.getSaldoDinheiro());
        assertEquals(4, atributos.getSaldoDes_acad_m1());
        assertEquals(5, atributos.getSaldoDes_acad_m2());
        assertEquals(6, atributos.getSaldoDes_acad_m3());
    }

    @Test
    void construtorDeCopiaDeveCriarInstanciaIndependente() {
        Atributos original = new Atributos(1, 2, 3.0, 4, 5, 6);
        Atributos copia = new Atributos(original);

        original.setSaldoSaude(99);
        original.setSaldoDinheiro(88.0);

        assertEquals(1, copia.getSaldoSaude());
        assertEquals(2, copia.getSaldoMotivacao());
        assertEquals(3.0, copia.getSaldoDinheiro());
        assertEquals(4, copia.getSaldoDes_acad_m1());
        assertEquals(5, copia.getSaldoDes_acad_m2());
        assertEquals(6, copia.getSaldoDes_acad_m3());
    }

    @Test
    void settersEGettersDevemFuncionarParaTodosOsCampos() {
        Atributos atributos = new Atributos();

        atributos.setSaldoSaude(10);
        atributos.setSaldoMotivacao(9);
        atributos.setSaldoDinheiro(99.9);
        atributos.setSaldoDes_acad_m1(8);
        atributos.setSaldoDes_acad_m2(7);
        atributos.setSaldoDes_acad_m3(6);

        assertEquals(10, atributos.getSaldoSaude());
        assertEquals(9, atributos.getSaldoMotivacao());
        assertEquals(99.9, atributos.getSaldoDinheiro());
        assertEquals(8, atributos.getSaldoDes_acad_m1());
        assertEquals(7, atributos.getSaldoDes_acad_m2());
        assertEquals(6, atributos.getSaldoDes_acad_m3());
    }

    @Test
    void somaAtributosComValoresPositivos() {
        Atributos base = new Atributos(5, 4, 100.0, 1, 2, 3);
        Atributos incremento = new Atributos(2, 1, 50.5, 4, 5, 6);

        base.somaAtributos(incremento);

        assertEquals(7, base.getSaldoSaude());
        assertEquals(5, base.getSaldoMotivacao());
        assertEquals(150.5, base.getSaldoDinheiro());
        assertEquals(5, base.getSaldoDes_acad_m1());
        assertEquals(7, base.getSaldoDes_acad_m2());
        assertEquals(9, base.getSaldoDes_acad_m3());
    }

    @Test
    void somaAtributosComValoresNegativos() {
        Atributos base = new Atributos(5, 5, 50.0, 5, 5, 5);
        Atributos decrescimo = new Atributos(-2, -3, -10.5, -1, -1, -1);

        base.somaAtributos(decrescimo);

        assertEquals(3, base.getSaldoSaude());
        assertEquals(2, base.getSaldoMotivacao());
        assertEquals(39.5, base.getSaldoDinheiro());
        assertEquals(4, base.getSaldoDes_acad_m1());
        assertEquals(4, base.getSaldoDes_acad_m2());
        assertEquals(4, base.getSaldoDes_acad_m3());
    }

    @Test
    void somaAtributosComZerosNaoDeveAlterarValores() {
        Atributos base = new Atributos(3, 4, 5.5, 6, 7, 8);
        Atributos zeros = new Atributos(0, 0, 0.0, 0, 0, 0);

        base.somaAtributos(zeros);

        assertEquals(3, base.getSaldoSaude());
        assertEquals(4, base.getSaldoMotivacao());
        assertEquals(5.5, base.getSaldoDinheiro());
        assertEquals(6, base.getSaldoDes_acad_m1());
        assertEquals(7, base.getSaldoDes_acad_m2());
        assertEquals(8, base.getSaldoDes_acad_m3());
    }
}