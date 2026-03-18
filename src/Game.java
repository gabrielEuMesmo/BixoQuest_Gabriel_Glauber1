import java.util.*;

public class Game {


    public static void main() {

        Scanner entrada = new Scanner(System.in);

        System.out.println("=========== BIXO QUEST ============");

        //Criação de personagem jogavel
        System.out.println("Digite o nome do seu personagem: ");
        Jogador player1 = new Jogador(entrada.nextLine(),10, 10,10, 800);

        System.out.println(player1.getNome() + " se matriculou em um curso na UEFS, seu Mais Futuro caiu e você está no auge da sua sanidade e empolgação \n" +
                "para essa nova jornada.\n\n");


        while (player1.getSaude() > 0 && player1.getMotivacao() > 0 && player1.getDinheiro() > 0){

            System.out.println("Status =========================");
            System.out.println(player1);
            System.out.println("================================");
            while (player1.getEnergia() > 0) {

                System.out.println("O quê você deseja fazer?:");



            }


            player1.maximizarEnergia();

        }

    }

}
