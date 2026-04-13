package model;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SocialTest {

    @Test
    void addColegaDeveAdicionarColegaNaListaInterna() throws ReflectiveOperationException {
        Social social = novoSocial();

        social.addColega();

        ArrayList<Colega> colegas = getLista(social, "colegas");
        assertEquals(1, colegas.size());
        assertNotNull(colegas.get(0));
    }

    @Test
    void atualizarOpcoesDevePopularColegasDisponiveisEAdicionarVoltarPorColega() throws ReflectiveOperationException {
        Social social = novoSocial();
        ArrayList<Colega> colegas = getLista(social, "colegas");
        colegas.add(new Colega("Ana", 2, false, "", 1, 1, 1));
        colegas.add(new Colega("Bia", 2, false, "", 1, 1, 1));

        social.atualizarOpcoes();

        ArrayList<Colega> disponiveis = getLista(social, "colegasDisponiveis");
        assertEquals(2, disponiveis.size());
        assertEquals(4, social.getOpcao("11").getOpcoes().size());
        assertTrue(social.getOpcao("11").getOpcoes().get(0).contains("Ana"));
        assertTrue(social.getOpcao("11").getOpcoes().get(1).equals("Voltar")
                || social.getOpcao("11").getOpcoes().get(2).equals("Voltar"));
    }

    @Test
    void adicionarAmigosNoComportamentoAtualPodeDuplicarAmigos() throws ReflectiveOperationException {
        Social social = novoSocial();
        Colega colega = new Colega("Ana", 2, false, "", 1, 1, 1);
        for (int i = 0; i < 5; i++) {
            colega.conversar();
            colega.atualizar();
        }

        getLista(social, "colegas").add(colega);

        social.adicionarAmigos();
        social.adicionarAmigos();

        ArrayList<Colega> amigos = getLista(social, "amigos");
        assertEquals(2, amigos.size());
    }

    @Test
    void realizarInvalidoDeveRetornarFalso() {
        Social social = novoSocial();

        boolean resultado = social.realizar("999");

        assertFalse(resultado);
    }

    @Test
    void realizarNoFluxoAtualNaoConversaComColegaQuandoPaginaTemTresCaracteres() throws ReflectiveOperationException {
        Social social = novoSocial();
        Colega colega = new Colega("Ana", 2, false, "", 1, 1, 1);
        getLista(social, "colegas").add(colega);
        social.atualizarOpcoes();

        boolean resultado = social.realizar("110");

        assertFalse(resultado);
        assertEquals(0, social.getAcoes().size());
        assertFalse(colega.getConversou());
    }

    @Test
    void mostrarDeveRetornarTituloSocial() {
        Social social = novoSocial();

        assertEquals("Social", social.mostrar().getTitulo());
    }

    private Social novoSocial() {
        return new Social(new Jogador("Bixo", 10, 10, 10, 100));
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Colega> getLista(Social social, String campo) throws ReflectiveOperationException {
        Field field = Social.class.getDeclaredField(campo);
        field.setAccessible(true);
        return (ArrayList<Colega>) field.get(social);
    }
}