package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AtributosTest {

    @Test
    void construtorNormalInicializaTodosCampos() {
        Atributos atributos = new Atributos(10, 20, 30.5, 1, 2, 3);

        assertEquals(10, atributos.getSaldoSaude());
        assertEquals(20, atributos.getSaldoMotivacao());
        assertEquals(30.5, atributos.getSaldoDinheiro());
        assertEquals(1, atributos.getSaldoDes_acad_m1());
        assertEquals(2, atributos.getSaldoDes_acad_m2());
        assertEquals(3, atributos.getSaldoDes_acad_m3());
    }

    @Test
    void construtorNormalComNegativosMantemValores() {
        Atributos atributos = new Atributos(-10, -20, -30.5, -1, -2, -3);

        assertEquals(-10, atributos.getSaldoSaude());
        assertEquals(-20, atributos.getSaldoMotivacao());
        assertEquals(-30.5, atributos.getSaldoDinheiro());
        assertEquals(-1, atributos.getSaldoDes_acad_m1());
        assertEquals(-2, atributos.getSaldoDes_acad_m2());
        assertEquals(-3, atributos.getSaldoDes_acad_m3());
    }

    @Test
    void construtorCopiaECamposIndependentes() {
        Atributos original = new Atributos(10, 20, 30.0, 1, 2, 3);
        Atributos copia = new Atributos(original);

        assertEquals(10, copia.getSaldoSaude());
        assertEquals(20, copia.getSaldoMotivacao());
        assertEquals(30.0, copia.getSaldoDinheiro());
        assertEquals(1, copia.getSaldoDes_acad_m1());
        assertEquals(2, copia.getSaldoDes_acad_m2());
        assertEquals(3, copia.getSaldoDes_acad_m3());

        copia.setSaldoSaude(99);
        assertEquals(10, original.getSaldoSaude());
        assertEquals(99, copia.getSaldoSaude());
    }

    @Test
    void gettersSettersFuncionam() {
        Atributos base = new Atributos();
        base.setSaldoSaude(5);
        base.setSaldoMotivacao(6);
        base.setSaldoDinheiro(7.5);
        base.setSaldoDes_acad_m1(1);
        base.setSaldoDes_acad_m2(2);
        base.setSaldoDes_acad_m3(3);

        assertEquals(5, base.getSaldoSaude());
        assertEquals(6, base.getSaldoMotivacao());
        assertEquals(7.5, base.getSaldoDinheiro());
        assertEquals(1, base.getSaldoDes_acad_m1());
        assertEquals(2, base.getSaldoDes_acad_m2());
        assertEquals(3, base.getSaldoDes_acad_m3());
    }

    @Test
    void somaAtributosPositivoNegativoEZeroMantemRegras() {
        Atributos base = new Atributos(5, 6, 7.5, 1, 2, 3);
        Atributos deltaPositivo = new Atributos(10, 20, 30.0, 4, 5, 6);
        base.somaAtributos(deltaPositivo);

        assertEquals(15, base.getSaldoSaude());
        assertEquals(26, base.getSaldoMotivacao());
        assertEquals(37.5, base.getSaldoDinheiro());
        assertEquals(5, base.getSaldoDes_acad_m1());
        assertEquals(7, base.getSaldoDes_acad_m2());
        assertEquals(9, base.getSaldoDes_acad_m3());

        Atributos deltaNegativo = new Atributos(-5, -6, -7.5, -1, -2, -3);
        base.somaAtributos(deltaNegativo);

        assertEquals(10, base.getSaldoSaude());
        assertEquals(20, base.getSaldoMotivacao());
        assertEquals(30.0, base.getSaldoDinheiro());
        assertEquals(4, base.getSaldoDes_acad_m1());
        assertEquals(5, base.getSaldoDes_acad_m2());
        assertEquals(6, base.getSaldoDes_acad_m3());

        Atributos zero = new Atributos(0, 0, 0.0, 0, 0, 0);
        base.somaAtributos(zero);
        assertEquals(10, base.getSaldoSaude());
        assertEquals(20, base.getSaldoMotivacao());
        assertEquals(30.0, base.getSaldoDinheiro());

        assertEquals(0, zero.getSaldoSaude());
        assertEquals(0, zero.getSaldoMotivacao());
        assertEquals(0.0, zero.getSaldoDinheiro());
        assertEquals(0, zero.getSaldoDes_acad_m1());
        assertEquals(0, zero.getSaldoDes_acad_m2());
        assertEquals(0, zero.getSaldoDes_acad_m3());
    }
}