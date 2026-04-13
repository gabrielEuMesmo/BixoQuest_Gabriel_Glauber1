package model;

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
    void criarColegaDeveRetornarInstanciaValida() {
        Colega colega = CriarRandom.criarColega();

        assertNotNull(colega);
        assertNotNull(colega.getNome());
        assertFalse(colega.getNome().isBlank());
    }

    @Test
    void opcoesSocialDevemTerEstruturaEsperada() {
        HashMap<String, Opcao> opcoes = CriarRandom.opcoesSocial();

        assertTrue(opcoes.containsKey(""));
        assertTrue(opcoes.containsKey("1"));
        assertTrue(opcoes.containsKey("2"));
        assertTrue(opcoes.containsKey("11"));

        assertEquals("Social", opcoes.get("").getTitulo());
        assertEquals("Conversar", opcoes.get("1").getTitulo());
        assertEquals("Sair", opcoes.get("2").getTitulo());
        assertEquals("Colegas", opcoes.get("11").getTitulo());

        assertEquals(List.of("Conversar", "Sair"), opcoes.get("").getOpcoes());
        assertEquals(List.of("Colegas"), opcoes.get("1").getOpcoes());
        assertTrue(opcoes.get("2").getOpcoes().isEmpty());
        assertTrue(opcoes.get("11").getOpcoes().isEmpty());
    }

    @Test
    void opcoesAcademicoFinanceiroESaudeDevemSerCorretas() {
        HashMap<String, Opcao> academico = CriarRandom.opcoesAcademico();
        HashMap<String, Opcao> financeiro = CriarRandom.opcoesFinanceiro();
        HashMap<String, Opcao> saude = CriarRandom.opcoesSaude();

        assertEquals(List.of("Estudar", "Estagiar"), academico.get("").getOpcoes());
        assertTrue(academico.get("1").getOpcoes().isEmpty());
        assertTrue(academico.get("2").getOpcoes().isEmpty());

        assertEquals(List.of("Fazer Freelancer"), financeiro.get("").getOpcoes());
        assertTrue(financeiro.get("1").getOpcoes().isEmpty());

        assertEquals(List.of("Exercicios", "Alimentacao"), saude.get("").getOpcoes());
        assertTrue(saude.get("1").getOpcoes().isEmpty());
        assertEquals(List.of("Comer comidas saudaveis", "Voltar"), saude.get("2").getOpcoes());
    }

    @Test
    void opcoesAcoesFixasEVariaveisDevemConterDadosEsperados() {
        assertEquals("Foi na lanchonete com amigos", CriarRandom.opcoesAcoesFixasSocial().get("2").get(0).getTipo());
        assertEquals("Foi para Academia", CriarRandom.opcoesAcoesFixasSaude().get("1").get(0).getTipo());
        assertEquals(3, CriarRandom.opcoesAcoesFixasAcademico().get("1").size());
        assertEquals("Fez um projeto pequeno de freela", CriarRandom.opcoesAcoesFixasFinanceiro().get("1").get(0).getTipo());

        assertEquals("Saiu para calourada", CriarRandom.opcoesAcoesVariaveisSocial().get("2").get(0).getTipo());
        assertEquals("Correu na UEFS com amigos", CriarRandom.opcoesAcoesVariaveisSaude().get("1").get(0).getTipo());
        assertEquals(2, CriarRandom.opcoesAcoesVariaveisAcademico().get("2").size());
        assertEquals("Fez um projeto grande de freela", CriarRandom.opcoesAcoesVariaveisFinanceiro().get("1").get(0).getTipo());
    }

    @Test
    void ramdomAcoesVariaveisDeveManterFixasEIncluirChaves() {
        HashMap<String, ArrayList<Acao>> fixas = new HashMap<>();
        HashMap<String, ArrayList<Acao>> variaveis = new HashMap<>();
        HashMap<String, ArrayList<Acao>> disponiveis = new HashMap<>();

        fixas.put("1", new ArrayList<>(List.of(new Acao("Fixa", 0, 0, 0.0, 0, 0, 0))));
        variaveis.put("1", new ArrayList<>(List.of(new Acao("Var1", 0, 0, 0.0, 0, 0, 0))));
        variaveis.put("2", new ArrayList<>(List.of(new Acao("Var2", 0, 0, 0.0, 0, 0, 0))));

        CriarRandom.ramdomAcoesVariaveis(fixas, variaveis, disponiveis);

        assertTrue(disponiveis.containsKey("1"));
        assertTrue(disponiveis.containsKey("2"));
        assertEquals("Fixa", disponiveis.get("1").get(0).getTipo());

        int tamanhoChave1 = disponiveis.get("1").size();
        assertTrue(tamanhoChave1 >= 1 && tamanhoChave1 <= 2);

        int tamanhoChave2 = disponiveis.get("2").size();
        assertTrue(tamanhoChave2 >= 0 && tamanhoChave2 <= 1);
    }
}