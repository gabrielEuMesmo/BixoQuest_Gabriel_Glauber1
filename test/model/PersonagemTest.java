package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonagemTest {

    @Test
    void subclasseFakeDeveImplementarConversarERetornarAcaoValida() {
        Personagem personagem = new PersonagemFake("Leo");

        Acao acao = personagem.conversar();

        assertEquals("Falou", acao.getTipo());
        assertEquals(1, acao.getSaldoMotivacao());
    }

    @Test
    void herancaDeGetNomeDeveFuncionar() {
        Personagem personagem = new PersonagemFake("Leo");

        assertEquals("Leo", personagem.getNome());
    }

    private static class PersonagemFake extends Personagem {
        private PersonagemFake(String nome) {
            super(nome);
        }

        @Override
        public Acao conversar() {
            return new Acao("Falou", 0, 1, 0.0, 0, 0, 0);
        }
    }
}

