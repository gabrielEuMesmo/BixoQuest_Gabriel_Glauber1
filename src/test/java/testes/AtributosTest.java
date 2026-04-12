package testes;

import model.Atributos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AtributosTest {

    @Test
    void deveSomarTodosOsSaldos() {
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
    void construtorDeCopiaDeveCriarInstanciaIndependente() {
        Atributos original = new Atributos(1, 2, 3.0, 4, 5, 6);
        Atributos copia = new Atributos(original);

        original.setSaldoSaude(99);
        original.setSaldoDinheiro(88.0);

        assertEquals(1, copia.getSaldoSaude());
        assertEquals(3.0, copia.getSaldoDinheiro());
    }
}
