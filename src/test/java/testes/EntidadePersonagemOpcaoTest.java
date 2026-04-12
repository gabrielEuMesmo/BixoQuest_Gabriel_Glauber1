package testes;

import model.Acao;
import model.Entidade;
import model.Opcao;
import model.Personagem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntidadePersonagemOpcaoTest {

    @Test
    void entidadeDeveRetornarNomeInformado() {
        Entidade entidade = new EntidadeFake("Maria");

        assertEquals("Maria", entidade.getNome());
    }

    @Test
    void personagemDeveExecutarImplementacaoDeConversa() {
        Personagem personagem = new PersonagemFake("Leo");

        Acao acao = personagem.conversar();

        assertEquals("Falou", acao.getTipo());
        assertEquals("Leo", personagem.getNome());
    }

    @Test
    void opcaoDeveAdicionarERetornarItens() {
        Opcao opcao = new Opcao("Menu", new ArrayList<>());

        opcao.addOpcao("Item 1");
        opcao.addOpcao("Item 2");

        assertEquals("Menu", opcao.getTitulo());
        assertEquals(2, opcao.getOpcoes().size());
        assertEquals("Item 2", opcao.getOpcao(1));
    }

    private static class EntidadeFake extends Entidade {
        private EntidadeFake(String nomeNovo) {
            super(nomeNovo);
        }
    }

    private static class PersonagemFake extends Personagem {
        private PersonagemFake(String nome) {
            super(nome);
        }

        @Override
        public Acao conversar() {
            return new Acao("Falou", 0, 0, 0, 0, 0, 0);
        }
    }
}
