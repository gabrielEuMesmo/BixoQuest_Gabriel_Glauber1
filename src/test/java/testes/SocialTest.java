package testes;

import model.Colega;
import model.Jogador;
import model.Social;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SocialTest {

    @Test
    void addColegaDeveInserirNovoColegaNaLista() throws ReflectiveOperationException {
        Social social = new Social(new Jogador("Bixo", 10, 10, 10, 100));

        social.addColega();

        ArrayList<?> colegas = obterLista(social, "colegas");
        assertEquals(1, colegas.size());
        assertNotNull(colegas.get(0));
    }

    @Test
    void adicionarAmigosDevePromoverColegaComRelacionamentoCinco() throws ReflectiveOperationException {
        Social social = new Social(new Jogador("Bixo", 10, 10, 10, 100));
        Colega colega = new Colega("Ana", 2, false, "", 1, 1, 1);

        for (int i = 0; i < 5; i++) {
            colega.conversar();
            colega.atualizar();
        }

        obterLista(social, "colegas").add(colega);

        social.adicionarAmigos();

        ArrayList<?> amigos = obterLista(social, "amigos");
        assertEquals(1, amigos.size());
    }

    @Test
    void atualizarOpcoesDevePopularPaginaOnzeComColegasDisponiveis() throws ReflectiveOperationException {
        Social social = new Social(new Jogador("Bixo", 10, 10, 10, 100));
        Colega colega = new Colega("Ana", 2, false, "", 1, 1, 1);
        obterLista(social, "colegas").add(colega);

        social.atualizarOpcoes();

        assertEquals(1, obterLista(social, "colegasDisponiveis").size());
        assertEquals(2, social.getOpcao("11").getOpcoes().size());
        assertTrue(social.getOpcao("11").getOpcao(0).contains("Ana"));
        assertEquals("Voltar", social.getOpcao("11").getOpcao(1));
    }

    @Test
    void realizarDeveRetornarFalsoParaPaginaInvalida() {
        Social social = new Social(new Jogador("Bixo", 10, 10, 10, 100));

        boolean resultado = social.realizar("999");

        assertFalse(resultado);
    }

    @Test
    void mostrarDeveRetornarPaginaInicialSocial() {
        Social social = new Social(new Jogador("Bixo", 10, 10, 10, 100));

        assertEquals("Social", social.mostrar().getTitulo());
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Colega> obterLista(Social social, String nomeCampo) throws ReflectiveOperationException {
        Field field = Social.class.getDeclaredField(nomeCampo);
        field.setAccessible(true);
        return (ArrayList<Colega>) field.get(social);
    }
}
