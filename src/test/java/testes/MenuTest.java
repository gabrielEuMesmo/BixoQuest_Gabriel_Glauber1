package testes;

import model.Acao;
import model.Jogador;
import model.Menu;
import model.Opcao;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MenuTest {

    @Test
    void realizarDeveRegistrarAcaoDisponivelESomarSeusSaldos() {
        Jogador jogador = new Jogador("Teste", 5, 5, 5, 100.0);
        Menu menu = new Menu(jogador, criarOpcoes(), criarAcoesVariaveisVazias(), criarAcoesFixas());

        boolean continua = menu.realizar("11");

        assertTrue(continua);
        assertEquals(1, menu.getAcoes().size());
        assertEquals("Estudou", menu.getAcoes().get(0).getTipo());
        assertEquals(1, menu.getSaldoSaude());
        assertEquals(2, menu.getSaldoMotivacao());
        assertEquals(10.0, menu.getSaldoDinheiro());
        assertEquals(3, menu.getSaldoDes_acad_m1());
        assertEquals(4, menu.getSaldoDes_acad_m2());
        assertEquals(5, menu.getSaldoDes_acad_m3());
        assertEquals("Menu", menu.mostrar().getTitulo());
    }

    @Test
    void realizarDeveRetornarFalsoQuandoIndiceNaoExistir() {
        Jogador jogador = new Jogador("Teste", 5, 5, 5, 100.0);
        Menu menu = new Menu(jogador, criarOpcoes(), criarAcoesVariaveisVazias(), criarAcoesFixas());

        boolean continua = menu.realizar("12");

        assertFalse(continua);
        assertTrue(menu.getAcoes().isEmpty());
    }

    @Test
    void escolherDeveAvancarParaSubmenuQuandoPaginaDestinoExistir() {
        Jogador jogador = new Jogador("Teste", 5, 5, 5, 100.0);
        Menu menu = new Menu(jogador, criarOpcoes(), criarAcoesVariaveisVazias(), criarAcoesFixas());

        boolean continua = menu.escolher(1);

        assertTrue(continua);
        assertEquals("Submenu", menu.mostrar().getTitulo());
    }

    @Test
    void escolherDeveExecutarAcaoQuandoSelecionaPaginaTerminalComAcao() {
        Jogador jogador = new Jogador("Teste", 5, 5, 5, 100.0);
        Menu menu = new Menu(jogador, criarOpcoes(), criarAcoesVariaveisVazias(), criarAcoesFixas());

        menu.escolher(1);
        boolean continua = menu.escolher(1);

        assertFalse(continua);
        assertEquals(1, menu.getAcoes().size());
        assertEquals("Estudou", menu.getAcoes().get(0).getTipo());
        assertEquals("Menu", menu.mostrar().getTitulo());
    }

    @Test
    void escolherDeveVoltarParaPaginaAnteriorQuandoOpcaoForVoltar() {
        Jogador jogador = new Jogador("Teste", 5, 5, 5, 100.0);
        Menu menu = new Menu(jogador, criarOpcoes(), criarAcoesVariaveisVazias(), criarAcoesFixas());

        menu.escolher(1);
        boolean continua = menu.escolher(2);

        assertFalse(continua);
        assertEquals("Menu", menu.mostrar().getTitulo());
    }

    @Test
    void atualizarDeveTransferirSaldosParaJogadorELimparAcumulado() {
        Jogador jogador = new Jogador("Teste", 5, 5, 5, 100.0);
        Menu menu = new Menu(jogador, criarOpcoes(), criarAcoesVariaveisComNovidade(), criarAcoesFixas());

        menu.addAcao(new Acao("Dormiu", 2, 3, 50.0, 1, 1, 1));
        menu.atualizar();

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
        assertTrue(menu.getOpcao("1").getOpcoes().stream().anyMatch(opcao -> opcao.contains("Estudou")));
        assertTrue(menu.getOpcao("1").getOpcoes().contains("Voltar"));
    }

    @Test
    void mostrarEGetOpcaoDevemRetornarOpcaoDaPaginaAtual() {
        Jogador jogador = new Jogador("Teste", 5, 5, 5, 100.0);
        Menu menu = new Menu(jogador, criarOpcoes(), criarAcoesVariaveisVazias(), criarAcoesFixas());

        assertNotNull(menu.mostrar());
        assertEquals("Menu", menu.getOpcao("").getTitulo());
    }

    private HashMap<String, Opcao> criarOpcoes() {
        HashMap<String, Opcao> opcoes = new HashMap<>();
        opcoes.put("", new Opcao("Menu", new ArrayList<>(List.of("ignorada", "Abrir submenu"))));
        opcoes.put("1", new Opcao("Submenu", new ArrayList<>(List.of("ignorada", "Executar acao", "Voltar"))));
        opcoes.put("11", new Opcao("Executar", new ArrayList<>()));
        opcoes.put("12", new Opcao("Voltar", new ArrayList<>()));
        return opcoes;
    }

    private HashMap<String, ArrayList<Acao>> criarAcoesVariaveisVazias() {
        return new HashMap<>();
    }

    private HashMap<String, ArrayList<Acao>> criarAcoesVariaveisComNovidade() {
        HashMap<String, ArrayList<Acao>> acoes = new HashMap<>();
        ArrayList<Acao> lista = new ArrayList<>();
        lista.add(new Acao("Virou monitoria", 0, 1, 0.0, 2, 0, 0));
        acoes.put("1", lista);
        return acoes;
    }

    private HashMap<String, ArrayList<Acao>> criarAcoesFixas() {
        HashMap<String, ArrayList<Acao>> acoes = new HashMap<>();
        ArrayList<Acao> lista = new ArrayList<>();
        lista.add(new Acao("Estudou", 1, 2, 10.0, 3, 4, 5));
        acoes.put("1", lista);
        return acoes;
    }
}
