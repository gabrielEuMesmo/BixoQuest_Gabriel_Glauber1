package model.persistencia;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

//classe de leitura da classe Captura.
//contem metodo para ler o arquivo em formato JSON.
//na leitura ele lê, montar a classe Captura e no controlle/construção essa "Captura" é lida e atualizada no jogo!

public class Load {

    // Recebe o nome do arquivo que quer carregar
    public static Captura carregar(String nomeDoSlot) {
        String caminhoArquivo = "saves/" + nomeDoSlot + ".sav";
        Captura capturaRecuperada = null;

        try {
            // 1. Lê o arquivo do disco
            FileInputStream fis = new FileInputStream(caminhoArquivo);

            // 2. Prepara o conversor de Bytes de volta para Objeto
            ObjectInputStream ois = new ObjectInputStream(fis);

            // 3. A MÁGICA DO LOAD: Lê os bytes, converte e guarda na variável.
            // O "(Captura)" é o cast, forçando o Java a entender o tipo.
            capturaRecuperada = (Captura) ois.readObject();

            // 4. Fecha as tubulações
            ois.close();
            fis.close();

        } catch (Exception e) {
            System.out.println("Erro ao carregar o jogo: " + e.getMessage());
        }

        // Retorna a "Mochila" cheia de dados de volta para o GameController
        return capturaRecuperada;
    }
}