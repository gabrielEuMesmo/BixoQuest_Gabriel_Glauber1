package controller;

import model.OpcaoVisualNovel;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class SistemaDeRotasTest {

    @Test
    void estadoInicialCorreto() {
        SistemaDeRotas rotas = new SistemaDeRotas();

        assertEquals("0", rotas.getPagAtual());
        assertTrue(rotas.isNaRaiz());
        OpcaoVisualNovel menu = rotas.getMenuAtual();
        assertNotNull(menu);
        assertEquals("Portao da UEFS", menu.getTituloLocal());
    }

    @Test
    void processarEscolhaVoltarFuncionaNosNiveis() {
        SistemaDeRotas rotas = new SistemaDeRotas();

        rotas.processarEscolha(2); // 02
        assertEquals("02", rotas.getPagAtual());

        assertTrue(rotas.processarEscolha(0));
        assertEquals("0", rotas.getPagAtual());

        rotas.processarEscolha(2); // 02
        rotas.processarEscolha(1); // 021
        assertEquals("021", rotas.getPagAtual());

        assertTrue(rotas.processarEscolha(0));
        assertEquals("02", rotas.getPagAtual());

        assertTrue(rotas.processarEscolha(0));
        assertEquals("0", rotas.getPagAtual());

        assertTrue(rotas.processarEscolha(0));
        assertEquals("0", rotas.getPagAtual());
    }

    @Test
    void processarEscolhaNavegacaoNormalConcatenaDigitos() {
        SistemaDeRotas rotas = new SistemaDeRotas();

        assertTrue(rotas.processarEscolha(2));
        assertEquals("02", rotas.getPagAtual());

        assertTrue(rotas.processarEscolha(1));
        assertEquals("021", rotas.getPagAtual());
    }

    @Test
    void processarEscolhaAcaoCriticaRetornaFalseENaoAlteraPagina() {
        SistemaDeRotas rotas = new SistemaDeRotas();
        rotas.processarEscolha(2); // 02
        rotas.processarEscolha(1); // 021 (acao critica)

        String antes = rotas.getPagAtual();
        boolean retorno = rotas.processarEscolha(1);

        assertFalse(retorno);
        assertEquals(1, rotas.getUltimaEscolha());
        assertEquals(antes, rotas.getPagAtual());
    }

    @Test
    void processarEscolhaInvalidaNaoAlteraPagina() {
        SistemaDeRotas rotas = new SistemaDeRotas();
        String pagInicial = rotas.getPagAtual();

        assertTrue(rotas.processarEscolha(99));
        assertEquals(pagInicial, rotas.getPagAtual());

        assertTrue(rotas.processarEscolha(-1));
        assertEquals(pagInicial, rotas.getPagAtual());
    }

    @Test
    void resetarParaRaizEIsNaRaizFuncionam() {
        SistemaDeRotas rotas = new SistemaDeRotas();

        rotas.processarEscolha(1);
        assertFalse(rotas.isNaRaiz());

        rotas.resetarParaRaiz();
        assertEquals("0", rotas.getPagAtual());
        assertTrue(rotas.isNaRaiz());
    }

    @Test
    void getMenuAtualRetornaMenuDaChaveAtualEPodeSerNuloParaChaveInexistenteComReflexao() throws Exception {
        SistemaDeRotas rotas = new SistemaDeRotas();

        rotas.processarEscolha(3);
        assertEquals("03", rotas.getPagAtual());
        assertEquals("Area Central", rotas.getMenuAtual().getTituloLocal());

        Field pagField = SistemaDeRotas.class.getDeclaredField("pag");
        pagField.setAccessible(true);
        pagField.set(rotas, "0999");

        assertNull(rotas.getMenuAtual());
    }
}

