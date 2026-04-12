package testes;

import model.Acao;
import model.Colega;
import model.CriarRandom;
import model.Opcao;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CriarRandomTest {

    @Test
    void criarColegaDeveRetornarColegaValido() {
        Colega colega = CriarRandom.criarColega();

        assertNotNull(colega);
        assertFalse(colega.getNome().isBlank());
    }

    @Test
    void opcoesSocialDevemRefletirEstruturaAtual() {
        HashMap<String, Opcao> opcoes = CriarRandom.opcoesSocial();

        assertEquals("Social", opcoes.get("").getTitulo());
        assertEquals("Conversar", opcoes.get("1").getTitulo());
        assertEquals("Sair", opcoes.get("2").getTitulo());
        assertEquals("Colegas", opcoes.get("11").getTitulo());
        assertTrue(opcoes.get("").getOpcoes().isEmpty());
        assertTrue(opcoes.get("1").getOpcoes().isEmpty());
        assertTrue(opcoes.get("2").getOpcoes().isEmpty());
        assertTrue(opcoes.get("11").getOpcoes().isEmpty());
    }

    @Test
    void opcoesDasOutrasAreasDevemSerInicializadas() {
        assertEquals("Academico", CriarRandom.opcoesAcademico().get("").getTitulo());
        assertEquals("Financeiro", CriarRandom.opcoesFinanceiro().get("").getTitulo());
        assertEquals("Saude", CriarRandom.opcoesSaude().get("").getTitulo());
        assertTrue(CriarRandom.opcoesAcademico().get("").getOpcoes().isEmpty());
        assertTrue(CriarRandom.opcoesFinanceiro().get("").getOpcoes().isEmpty());
        assertEquals(List.of("Comer comidas saudaveis", "Voltar"), CriarRandom.opcoesSaude().get("").getOpcoes());
        assertNotNull(CriarRandom.opcoesAcoesFixasSocial());
        assertNotNull(CriarRandom.opcoesAcoesFixasAcademico());
        assertNotNull(CriarRandom.opcoesAcoesFixasFinanceiro());
        assertNotNull(CriarRandom.opcoesAcoesFixasSaude());
    }

    @Test
    void acoesVariaveisDevemRefletirConfiguracaoAtual() {
        assertEquals("Saiu para calourada", CriarRandom.opcoesAcoesVariaveisSocial().get("2").get(0).getTipo());
        assertEquals("Estagiou integral", CriarRandom.opcoesAcoesVariaveisAcademico().get("2").get(0).getTipo());
        assertEquals("Fez um projeto grande de freela", CriarRandom.opcoesAcoesVariaveisFinanceiro().get("2").get(0).getTipo());
        assertEquals("Correu na UEFS com amigos", CriarRandom.opcoesAcoesVariaveisSaude().get("1").get(0).getTipo());
    }

    @Test
    void randomAcoesVariaveisDeveSempreManterAcoesFixas() {
        HashMap<String, ArrayList<Acao>> fixas = new HashMap<>();
        HashMap<String, ArrayList<Acao>> variaveis = new HashMap<>();
        HashMap<String, ArrayList<Acao>> disponiveis = new HashMap<>();
        ArrayList<Acao> acoesFixas = new ArrayList<>();
        ArrayList<Acao> acoesVariaveis = new ArrayList<>();

        acoesFixas.add(new Acao("Fixa", 0, 0, 0, 0, 0, 0));
        acoesVariaveis.add(new Acao("Variavel", 0, 0, 0, 0, 0, 0));
        fixas.put("1", acoesFixas);
        variaveis.put("1", acoesVariaveis);

        CriarRandom.ramdomAcoesVariaveis(fixas, variaveis, disponiveis);

        assertTrue(disponiveis.containsKey("1"));
        assertEquals("Fixa", disponiveis.get("1").get(0).getTipo());
        assertTrue(disponiveis.get("1").size() >= 1);
        assertTrue(disponiveis.get("1").size() <= 2);
    }
}
