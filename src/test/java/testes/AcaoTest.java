package testes;

import model.Acao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AcaoTest {

    @Test
    void construtorDeCopiaDevePreservarTipoNomesESaldos() {
        Acao original = new Acao("Estudou", 1, 2, 3.5, 4, 5, 6);
        original.addNome("Ana");

        Acao copia = new Acao(original);

        assertEquals("Estudou", copia.getTipo());
        assertEquals(1, copia.getSaldoSaude());
        assertEquals(2, copia.getSaldoMotivacao());
        assertEquals(3.5, copia.getSaldoDinheiro());
        assertTrue(copia.toString().contains("Ana"));
    }

    @Test
    void toStringDeveListarParticipantesQuandoExistirem() {
        Acao acao = new Acao("Conversou", 0, 1, 0, 2, 3, 4);
        acao.addNome("Bia");
        acao.addNome("Caio");

        String descricao = acao.toString();

        assertTrue(descricao.contains("Conversou com Bia, Caio."));
        assertTrue(descricao.contains("Saldo de Saude:0"));
        assertTrue(descricao.contains("Saldo de Desempenho academico ALG:4"));
    }
}
