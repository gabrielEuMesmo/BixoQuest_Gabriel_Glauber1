package model.persistencia;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.File;

//classe de escrita da classe Captura. Contem um metodo para salvar o arquivo em formato JSON.
//O nome do arquivo deve ser o Id (ex: "slot1.json")
//e deve ser salvo em uma pasta específica para saves ("saves/").

public class Salvar {

    // Recebe o objeto Captura cheio de dados e o nome que você quer dar ao arquivo
    public static void gravar(Captura captura, String nomeDoSlot) {
        // Monta o caminho: "saves/slot1.sav"
        String caminhoArquivo = "saves/" + nomeDoSlot + ".sav";

        try {
            // 1. Abre o arquivo no disco (se não existir, o Java cria)
            FileOutputStream fos = new FileOutputStream(caminhoArquivo);

            // 2. Prepara o conversor de Objeto para Bytes
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // 3. A MÁGICA ACONTECE AQUI! Ele escreve a Captura inteira no arquivo.
            oos.writeObject(captura);

            // 4. Fecha as tubulações
            oos.close();
            fos.close();

            System.out.println("Jogo salvo com sucesso em: " + caminhoArquivo);

        } catch (Exception e) {
            System.out.println("Erro ao salvar o jogo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}