package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BancoDialogoColega {

    public static ColegaDia criarSuco() {
        List<CenaDialogo> roteiro = new ArrayList<>();

        // ── CENA 0: Primeiro encontro
        String fala0 = "Fala seu virge, aqui é ZECAmmmm! Bó vê um rango no RU TO NA BROCAAA!";

        OpcaoResposta op0_1 = new OpcaoResposta(
                "bora la!",
                1,      // +1 Relacionamento
                0,
                5,      // Custo 5 Energia
                5,      // +5 Motivacao
                -2.0,   // -2 Dinheiro (ticket do almoco)
                5,      // +5 Saude (comeu)
                "bora virge"
        );

        OpcaoResposta op0_2 = new OpcaoResposta(
                "Vou estudar patrão deixa pra proxima",
                0,
                10,     // +10 EXA (estudou)
                10,
                -2,     // -2 Motivacao (cansativo)
                0.0,
                -5,     // -5 Saude (nao comeu)
                "Ih, ala o nerd. O cara quer estudar"
        );

        roteiro.add(new CenaDialogo(fala0, Arrays.asList(op0_1, op0_2)));

        // ── CENA 1: Nivel 1
        String fala1 = "Cara, mulher?";

        OpcaoResposta op1_1 = new OpcaoResposta(
                "Com certeza! to precisando pra relaxar",
                2,      // +2 Relacionamento
                0,
                15,     // Custo 15 Energia
                20,     // +20 Motivacao
                -30.0,  // -30 Dinheiro
                -10,    // -10 Saude (festa cansa)
                "É esse né virge naum"
        );

        OpcaoResposta op1_2 = new OpcaoResposta(
                "mais futuro não pingou essa desgraça.",
                0,
                0,
                0,
                0,
                0.0,
                0,
                "mas pra mulher e cachaça você tem dinheiro né?"
        );

        roteiro.add(new CenaDialogo(fala1, Arrays.asList(op1_1, op1_2)));

        // ── CENA 2: Nivel 2 ──────────────────────────────────────────────────
        String fala2 = "Mano, to formando um grupo de estudos pra PBL. Quer entrar? A gente se reune toda quarta.";

        OpcaoResposta op2_1 = new OpcaoResposta(
                "Claro! Grupo de estudos e sempre bom.",
                1,
                15,     // +15 ALG
                10,
                10,
                0.0,
                0,
                "Show! O grupo ja tem mais 3 pessoas. Vou te adicionar no grupo do WhatsApp."
        );

        OpcaoResposta op2_2 = new OpcaoResposta(
                "Prefiro estudar sozinho, mas obrigado.",
                0,
                5,      // Pouco ganho
                5,
                -5,
                0.0,
                0,
                "Tudo bem, qualquer coisa e so chamar."
        );

        roteiro.add(new CenaDialogo(fala2, Arrays.asList(op2_1, op2_2)));

        return new ColegaDia("Suco", "ALG", 3, roteiro);
    }

    public static ColegaDia criarMaeli() {

        List<CenaDialogo> roteiro = new ArrayList<>();

        String fala0 = "Oi! Posso te ajudar com alguma coisa no colegiado?";

        OpcaoResposta op0_1 = new OpcaoResposta(
                "Sim! Estou perdido nas materias que devo cursar esse semestre.",
                1,
                8,       // +8 EXA (dica academica)
                5,
                10,
                0.0,
                0,
                "Sem problemas! Olha, o prof Borges recomenda que voce comece por Calculo I. " +
                        "Ele eh bravo mas ensina bem. E o Anfranserai so aceita alunos dedicados no lab."
        );

        OpcaoResposta op0_2 = new OpcaoResposta(
                "Nao obrigado, so estava passando por aqui.",
                0,
                0,
                2,
                0,
                0.0,
                0,
                "Ok! Se precisar de qualquer coisa o colegiado ta aqui pra ajudar."
        );

        roteiro.add(new CenaDialogo(fala0, Arrays.asList(op0_1, op0_2)));

        String fala1 = "Ah voce de novo! Lembra que te falei do prof Anfranserai? Ele me pediu pra avisar " +
                "que tem uma bolsa de monitoriaem aberto. Voce topa?";

        OpcaoResposta op1_1 = new OpcaoResposta(
                "Claro! Quero muito ser monitor.",
                1,
                20,
                10,
                15,
                150.0,  // Bolsa de monitoria
                0,
                "Que otimo! Vou te colocar na lista. O Anfranserai vai entrar em contato."
        );

        OpcaoResposta op1_2 = new OpcaoResposta(
                "Nao tenho tempo agora, mas obrigado.",
                0,
                0,
                0,
                -5,
                0.0,
                0,
                "Tudo bem, a oportunidade vai aparecer de novo no proximo semestre."
        );

        roteiro.add(new CenaDialogo(fala1, Arrays.asList(op1_1, op1_2)));

        return new ColegaDia("Maeli", "EXA", 1, roteiro);
    }

    public static ColegaDia criarCaramelo() {

        List<CenaDialogo> roteiro = new ArrayList<>();

        // Caramelo e o cachorro do campus — interacao simples mas reconfortante
        String fala0 = "*O caozinho do campus olha pra voce com olhos brilhando e abana o rabo*";

        OpcaoResposta op0_1 = new OpcaoResposta(
                "Fazer carinho no Caramelo",
                1,
                0,
                3,      // Custo 3 Energia
                20,     // +20 Motivacao (reconfortante)
                0.0,
                10,     // +10 Saude (bem-estar mental)
                "*Caramelo late de felicidade e lambe sua mao. Voce se sentiu muito melhor.*"
        );

        OpcaoResposta op0_2 = new OpcaoResposta(
                "Nao tenho tempo agora, Caramelo",
                -1,     // Caramelo fica triste
                0,
                0,
                -5,     // -5 Motivacao (se sentiu mal por ignorar o cachorro)
                0.0,
                0,
                "*Caramelo abaixa as orelhas tristemente e vai embora.*"
        );

        roteiro.add(new CenaDialogo(fala0, Arrays.asList(op0_1, op0_2)));

        String fala1 = "*Caramelo corre em sua direcao agitando o rabo com muita energia!*";

        OpcaoResposta op1_1 = new OpcaoResposta(
                "Fica quieto Caramelo, deixa eu te escovar!",
                1,
                0,
                5,
                25,     // +25 Motivacao
                0.0,
                15,     // +15 Saude
                "*Caramelo fica completamente quieto e fecha os olhos de prazer. Melhor dia da sua vida.*"
        );

        roteiro.add(new CenaDialogo(fala1, Arrays.asList(op1_1)));

        return new ColegaDia("Caramelo", "GERAL", 0, roteiro);
    }
}
