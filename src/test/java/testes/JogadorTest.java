package testes;

import model.Jogador;
import model.Opcao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JogadorTest {

    @Test
    void deveInicializarComValoresInformados() {
        Jogador jogador = new Jogador("Gabriel", 10, 8, 7, 500.0);

        assertEquals("Gabriel", jogador.getNome());
        assertEquals(10, jogador.getSaude());
        assertEquals(8, jogador.getEnergia());
        assertEquals(7, jogador.getMotivacao());
        assertEquals(500.0, jogador.getDinheiro());
        assertEquals(0.0, jogador.getPorcentagem());
    }

    @Test
    void deveAlterarRecursosComMetodosDeSomaSubtracaoEZerar() {
        Jogador jogador = new Jogador("Bixo", 5, 5, 5, 100.0);

        jogador.somarEnergia(2);
        jogador.subtrairEnergia(1);
        jogador.somarMotivacao(3);
        jogador.subtrairMotivacao(2);
        jogador.somarSaude(4);
        jogador.subtrairSaude(1);
        jogador.somarDinheiro(50.0);
        jogador.subtrairDinheiro(20.0);
        jogador.somarDes_acad_m1(2);
        jogador.somarDes_acad_m2(3);
        jogador.somarDes_acad_m3(4);

        assertEquals(6, jogador.getEnergia());
        assertEquals(8, jogador.getSaude());
        assertEquals(6, jogador.getMotivacao());
        assertEquals(130.0, jogador.getDinheiro());
        assertEquals(2, jogador.getDesempenho_m1());
        assertEquals(3, jogador.getDesempenho_m2());
        assertEquals(4, jogador.getDesempenho_m3());

        jogador.zerarDinheiro();

        assertEquals(0.0, jogador.getDinheiro());
    }

    @Test
    void deveExibirMenuPrincipalAoMostrarOpcoesIniciais() {
        Jogador jogador = new Jogador("Bixo", 10, 10, 10, 800.0);

        Opcao opcao = jogador.mostrarOpcao();

        assertNotNull(opcao.getTitulo());
        assertEquals(4, opcao.getOpcoes().size());
        assertEquals("Social", opcao.getOpcao(0));
    }

    @Test
    void deveMaximizarAtributosLimitados() {
        Jogador jogador = new Jogador("Bixo", 1, 2, 3, 10.0);

        jogador.maximizarEnergia();
        jogador.maximizarMotivacao();
        jogador.maximizarSaude();

        assertEquals(10, jogador.getEnergia());
        assertEquals(10, jogador.getMotivacao());
        assertEquals(10, jogador.getSaude());
    }

    @Test
    void escolherOpcaoInicialSocialAtualmenteDisparaExcecaoDoSubmenu() {
        Jogador jogador = new Jogador("Bixo", 10, 10, 10, 800.0);

        assertThrows(IndexOutOfBoundsException.class, () -> jogador.escolherOpcao(1));
    }

    @Test
    void escolherOutrasOpcoesIniciaisAtualmentePropagaErroDePaginaVazia() {
        Jogador jogadorAcademico = new Jogador("Bixo", 10, 10, 10, 800.0);
        assertThrows(IndexOutOfBoundsException.class, () -> jogadorAcademico.escolherOpcao(2));

        Jogador jogadorFinanceiro = new Jogador("Bixo", 10, 10, 10, 800.0);
        assertThrows(StringIndexOutOfBoundsException.class, () -> jogadorFinanceiro.escolherOpcao(3));

        Jogador jogadorSaude = new Jogador("Bixo", 10, 10, 10, 800.0);
        assertThrows(StringIndexOutOfBoundsException.class, () -> jogadorSaude.escolherOpcao(4));
    }

    @Test
    void toStringDeveExibirResumoDoJogador() {
        Jogador jogador = new Jogador("Bixo", 10, 9, 8, 700.0);

        String descricao = jogador.toString();

        assertTrue(descricao.contains("Nome:Bixo"));
        assertTrue(descricao.contains("Energia: 9"));
        assertTrue(descricao.contains("Dinheiro: 700.0"));
    }
}
