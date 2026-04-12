package view;

import model.CriarRandom;
import model.Jogador;

import java.util.Scanner;

// Ponto de entrada da interação em console do jogo.
public class Game {

    // Executa o loop principal do jogo falando apenas com a API pública de Jogador.
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        System.out.println("=========== BIXO QUEST ============");
        System.out.print("Digite o nome do seu personagem: ");
        Jogador jogador = new Jogador(entrada.nextLine(), 10, 10, 10, 800);
        System.out.println(CriarRandom.opcoesSocial());
        jogador.atualizar();
        System.out.println();
        System.out.println("Bem-vindo, " + jogador.getNome() + ".");

        boolean executando = true;
        while (executando) {
            mostrarStatus(jogador);
            mostrarOpcoes(jogador);

            System.out.print("Escolha uma opcao ou 5 para sair: ");
            if (!entrada.hasNextInt()) {
                System.out.println("Entrada invalida.");
                entrada.nextLine();
                System.out.println();
                continue;
            }

            int escolha = entrada.nextInt();
            entrada.nextLine();

            if (escolha == 0) {
                executando = false;
                continue;
            }

            try {
                boolean continuarNoFluxo = jogador.escolherOpcao(escolha);
                if (!continuarNoFluxo) {
                    System.out.println("Escolha processada.");
                }
            } catch (RuntimeException e) {
                System.out.println("A escolha nao pode ser processada no estado atual do jogo.");
            }

            System.out.println();
        }

        System.out.println("Jogo encerrado.");
    }

    // Exibe o estado atual do jogador.
    private static void mostrarStatus(Jogador jogador) {
        System.out.println("Status =========================");
        System.out.println(jogador);
        System.out.println("================================");
        System.out.println(jogador.getPag());
    }

    // Exibe o menu atual retornado pelo jogador.
    private static void mostrarOpcoes(Jogador jogador) {
        System.out.println(jogador.getPag());
        System.out.println(jogador.academico.pag);
        var opcaoAtual = jogador.mostrarOpcao();
        System.out.println(opcaoAtual.getTitulo());
        for (int i = 1; i <= opcaoAtual.getOpcoes().size(); i++) {
            System.out.println(i + " - " + opcaoAtual.getOpcao(i -1));
        }
    }
}
