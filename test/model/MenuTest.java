package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MenuTest {

    @Test
    void construtorDeveIniciarPaginaVaziaEMostrarOpcaoRaiz() {
        Menu menu = novoMenu();

        assertNotNull(menu.mostrar());
        assertEquals("Menu", menu.mostrar().getTitulo());
        assertEquals("Menu", menu.getOpcao("").getTitulo());
    }

    @Test
    void realizarValidoDeveAdicionarAcaoESomarSaldos() {
        Menu menu = novoMenu();

        boolean resultado = menu.realizar("11");

        assertTrue(resultado);
        assertEquals(1, menu.getAcoes().size());
        assertEquals("Executar", menu.getAcoes().get(0).getTipo());
        assertEquals(2, menu.getSaldoSaude());
        assertEquals(3, menu.getSaldoMotivacao());
        assertEquals(10.0, menu.getSaldoDinheiro());
        assertEquals(4, menu.getSaldoDes_acad_m1());
        assertEquals(5, menu.getSaldoDes_acad_m2());
        assertEquals(6, menu.getSaldoDes_acad_m3());
    }

    @Test
    void realizarInvalidoOuForaDoRangeDeveRetornarFalso() {
        Menu menu = novoMenu();

        assertFalse(menu.realizar("12"));
        assertFalse(menu.realizar("99"));
        assertTrue(menu.getAcoes().isEmpty());
    }

    @Test
    void addAcaoDeveAcumularSaldos() {
        Menu menu = novoMenu();

        menu.addAcao(new Acao("A", 1, 2, 3.0, 4, 5, 6));
        menu.addAcao(new Acao("B", -1, 1, 2.0, 1, 1, 1));

        assertEquals(2, menu.getAcoes().size());
        assertEquals(0, menu.getSaldoSaude());
        assertEquals(3, menu.getSaldoMotivacao());
        assertEquals(5.0, menu.getSaldoDinheiro());
        assertEquals(5, menu.getSaldoDes_acad_m1());
        assertEquals(6, menu.getSaldoDes_acad_m2());
        assertEquals(7, menu.getSaldoDes_acad_m3());
    }

    @Test
    void escolherDeveNavegarParaSubmenuQuandoDestinoExiste() {
        Menu menu = novoMenu();

        boolean continua = menu.escolher(1);

        assertTrue(continua);
        assertEquals("Submenu", menu.mostrar().getTitulo());
    }

    @Test
    void escolherEmOpcaoInexistenteDeveManterEstadoAtual() {
        Menu menu = novoMenu();

        boolean continua = menu.escolher(9);

        assertTrue(continua);
        assertEquals("Menu", menu.mostrar().getTitulo());
    }

    @Test
    void escolherEmPaginaTerminalDeveExecutarAcaoEVoltar() {
        Menu menu = novoMenu();

        menu.escolher(1);
        boolean continua = menu.escolher(1);

        assertFalse(continua);
        assertEquals(1, menu.getAcoes().size());
        assertEquals("Menu", menu.mostrar().getTitulo());
    }

    @Test
    void fluxoVoltarNoComportamentoAtualLancaExcecao() {
        Menu menu = novoMenu();
        menu.escolher(1);

        assertThrows(StringIndexOutOfBoundsException.class, () -> menu.escolher(2));
    }

    @Test
    void atualizarDeveTransferirSaldosParaJogadorZerarInternosELimparAcoes() {
        Jogador jogador = new Jogador("Teste", 5, 5, 5, 100.0);
        Menu menu = novoMenu(jogador);
        menu.addAcao(new Acao("Dormiu", 2, 3, 50.0, 1, 1, 1));

        ArrayList<Acao> retorno = menu.atualizar();

        assertEquals(1, retorno.size());
        assertEquals("Dormiu", retorno.get(0).getTipo());
        assertEquals(7, jogador.getSaude());
        assertEquals(8, jogador.getMotivacao());
        assertEquals(150.0, jogador.getDinheiro());
        assertEquals(1, jogador.getDesempenho_m1());
        assertEquals(1, jogador.getDesempenho_m2());
        assertEquals(1, jogador.getDesempenho_m3());

        assertEquals(0, menu.getSaldoSaude());
        assertEquals(0, menu.getSaldoMotivacao());
        assertEquals(0.0, menu.getSaldoDinheiro());
        assertTrue(menu.getAcoes().isEmpty());
    }

    private Menu novoMenu() {
        return novoMenu(new Jogador("Teste", 5, 5, 5, 100.0));
    }

    private Menu novoMenu(Jogador jogador) {
        return new Menu(jogador, criarOpcoes(), new HashMap<>(), criarAcoesFixas());
    }

    private HashMap<String, Opcao> criarOpcoes() {
        HashMap<String, Opcao> opcoes = new HashMap<>();
        opcoes.put("", new Opcao("Menu", new ArrayList<>(List.of("Ignorada", "Abrir submenu"))));
        opcoes.put("1", new Opcao("Submenu", new ArrayList<>(List.of("Ignorada", "Executar", "Voltar"))));
        opcoes.put("11", new Opcao("Executar", new ArrayList<>(List.of("placeholder"))));
        opcoes.put("12", new Opcao("Voltar", new ArrayList<>()));
        return opcoes;
    }

    private HashMap<String, ArrayList<Acao>> criarAcoesFixas() {
        HashMap<String, ArrayList<Acao>> acoes = new HashMap<>();
        acoes.put("1", new ArrayList<>(List.of(
                new Acao("Ignorada", 0, 0, 0.0, 0, 0, 0),
                new Acao("Executar", 2, 3, 10.0, 4, 5, 6)
        )));
        return acoes;
    }
}