package model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BancoDialogoProfessor {

    public static Professor criarAnfranserai() {

        List<CenaDialogo> roteiro = new ArrayList<>();

        // ── CENA 0: Nivel de relacionamento 0 (Primeiro encontro) ─────────────
        String fala0 = "Quem deixou voce entrar no laboratorio de Hardware?";

        OpcaoResposta op0_1 = new OpcaoResposta(
                "Sou calouro seu beta, me ensine a codar em verilog",
                1,       // +1 Relacionamento
                10,      // +10 Conhecimento (TEC)
                5,       // Custo 5 Energia
                2,       // +2 Motivacao
                0.0,     // 0 Dinheiro
                0,       // 0 Saude
                "Gostei da atitude."
        );

        OpcaoResposta op0_2 = new OpcaoResposta(
                "Eu so queria saber onde fica a cantina",
                0,       // 0 Relacionamento
                0,       // 0 Conhecimento
                2,       // Custo 2 Energia
                -5,      // -5 Motivacao (tomou esporro)
                0.0,
                -5,      // -5 Saude (estresse)
                "Isso e um laboratorio miseravel! Va procurar comida em outro lugar, seu beta!"
        );

        roteiro.add(new CenaDialogo(fala0, Arrays.asList(op0_1, op0_2)));

        // ── CENA 1: Nivel de relacionamento 1 ────────────────────────────────
        String fala1 = "Voce de novo?";

        OpcaoResposta op1_1 = new OpcaoResposta(
                "Vim pra terminar o circuito da aula.",
                1,       // +1 Relacionamento
                15,      // +15 Conhecimento (TEC)
                10,      // Custo 10 Energia
                5,       // +5 Motivacao
                100.0,   // +100 Dinheiro (Bolsa de IC!)
                0,
                "Impressionante! Agora sai pra lá inseto"
        );

        OpcaoResposta op1_2 = new OpcaoResposta(
                "fui mogado professor :(",
                0,
                0,
                0,
                -5,
                0.0,
                0,
                "só me volte quando tiver resultados."
        );

        roteiro.add(new CenaDialogo(fala1, Arrays.asList(op1_1, op1_2)));

        // ── CENA 2: Nivel de relacionamento 2 ────────────────────────────────
        String fala2 = "Preciso de alguem para ser monitor voluntario. Voce topa?";

        OpcaoResposta op2_1 = new OpcaoResposta(
                "Claro professor, deixarei de ser beta!",
                1,
                20,      // +20 TEC
                15,
                15,
                200.0,   // Premiacao da feira
                0,
                "Excelente! Você começa na prox semana."
        );

        OpcaoResposta op2_2 = new OpcaoResposta(
                "Estou com a agenda cheia professor.",
                -1,      // Perde relacionamento
                0,
                0,
                -10,
                0.0,
                0,
                "Nem queria mesmo, vou convidar outro aluno melhor. Ô MAIKI..."
        );

        roteiro.add(new CenaDialogo(fala2, Arrays.asList(op2_1, op2_2)));

        return new Professor("Anfranserai", "TEC", roteiro);
    }

    public static Professor criarRogerio() {

        List<CenaDialogo> roteiro = new ArrayList<>();

        String fala0 = "Seu miseravel, voce sabe para que serve uma derivada?";

        OpcaoResposta op0_1 = new OpcaoResposta(
                "Para calcular a taxa de variacao de uma funcao, professor!",
                1,
                12,      // +12 EXA
                5,
                5,
                0.0,
                0,
                "Muito bem!"
        );

        OpcaoResposta op0_2 = new OpcaoResposta(
                "EM? para resolver exercicios da prova?",
                0,
                0,
                3,
                -3,
                0.0,
                0,
                "Resposta de quem nao estudou e é beta"
        );

        roteiro.add(new CenaDialogo(fala0, Arrays.asList(op0_1, op0_2)));

        String fala1 = "Tenho uma lista extra de exercicios de Calculo II. Quer levar?";

        OpcaoResposta op1_1 = new OpcaoResposta(
                "Sim",
                1,
                20,      // +20 EXA
                10,
                10,
                0.0,
                0,
                "Otimo! Quem resolve essa lista costuma ir muito bem e deixa de ser beta!"
        );

        OpcaoResposta op1_2 = new OpcaoResposta(
                "Obrigado, mas ja tenho material suficiente.",
                0,
                0,
                0,
                0,
                0.0,
                0,
                "Como quiser seu beta"
        );

        roteiro.add(new CenaDialogo(fala1, Arrays.asList(op1_1, op1_2)));

        return new Professor("Rogerio", "EXA", roteiro);
    }
}
