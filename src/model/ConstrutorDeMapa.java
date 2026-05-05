package model;
import model.enums.BlocoTempo;
import java.util.HashMap;
import java.util.Map;

public class ConstrutorDeMapa {
    public static Map<String, Local> gerarMapaUefs() {
        Map<String, Local> mapaMundo = new HashMap<>();

        //CRIANDO OS LOCAIS
        Local cantinaM5     = new Local("Cantina do M5");
        Local patsM5        = new Local("PATs do M5");
        Local daComputacao  = new Local("DA de Computacao");
        Local biblioteca    = new Local("Biblioteca Central");
        Local colegiado     = new Local("Colegiado de Computacao");
        Local salasM1       = new Local("Salas do M1");
        Local pracaCentral  = new Local("Praca Central");

        //CRIANDO OS NPCs PELAS FABRICAS
        Professor anfranserai = BancoDialogoProfessor.criarAnfranserai();
        Professor rogerio      = BancoDialogoProfessor.criarRogerio();
        ColegaDia suco           = BancoDialogoColega.criarSuco();
        ColegaDia maeli          = BancoDialogoColega.criarMaeli();
        ColegaDia caramelo       = BancoDialogoColega.criarCaramelo();

        //REGISTRANDO PRESENCAS -> Quem esta onde e quando

        // Anfranserai: PATs DO MODULO 5 DE 7:30!
        patsM5.registrarPresenca(BlocoTempo.MANHA_1, anfranserai);
        patsM5.registrarPresenca(BlocoTempo.MANHA_2, anfranserai);

        // rogerio: SALAS DO MODULO 1 7:30
        salasM1.registrarPresenca(BlocoTempo.MANHA_1, rogerio);
        salasM1.registrarPresenca(BlocoTempo.MANHA_2, rogerio);
        salasM1.registrarPresenca(BlocoTempo.TARDE_1, rogerio);

        // Suco: CANTINA DO MODULO 5 E DE NOITE PRA FUMAR
        cantinaM5.registrarPresenca(BlocoTempo.TARDE_1, suco);
        cantinaM5.registrarPresenca(BlocoTempo.TARDE_2, suco);
        daComputacao.registrarPresenca(BlocoTempo.NOITE, suco);

        // Maeli: APENAS COLEGIADO
        colegiado.registrarPresenca(BlocoTempo.MANHA_1, maeli);
        colegiado.registrarPresenca(BlocoTempo.MANHA_2, maeli);
        colegiado.registrarPresenca(BlocoTempo.TARDE_1, maeli);
        colegiado.registrarPresenca(BlocoTempo.TARDE_2, maeli);

        // Caramelo: PRAÇA DO BOROGIVIS
        pracaCentral.registrarPresenca(BlocoTempo.MANHA_1, caramelo);
        pracaCentral.registrarPresenca(BlocoTempo.MANHA_2, caramelo);
        pracaCentral.registrarPresenca(BlocoTempo.TARDE_1, caramelo);
        pracaCentral.registrarPresenca(BlocoTempo.TARDE_2, caramelo);
        pracaCentral.registrarPresenca(BlocoTempo.NOITE,   caramelo);

        // Biblioteca fica vazia por padrao (pode adicionar NPCs depois)

        // ── 4. EMPACOTANDO NO DICIONARIO FINAL ───────────────────────────────
        // A chave DEVE ser identica ao nomeLocalReal definido no SistemaDeRotas
        mapaMundo.put(cantinaM5.getNome(),    cantinaM5);
        mapaMundo.put(patsM5.getNome(),       patsM5);
        mapaMundo.put(daComputacao.getNome(), daComputacao);
        mapaMundo.put(biblioteca.getNome(),   biblioteca);
        mapaMundo.put(colegiado.getNome(),    colegiado);
        mapaMundo.put(salasM1.getNome(),      salasM1);
        mapaMundo.put(pracaCentral.getNome(), pracaCentral);

        return mapaMundo; //AQUI O MAPA DO MUNDO ESTÁ PRONTO, COM TODOS OS LOCAIS E NPCs REGISTRADOS!
        // O SISTEMA DE ROTAS VAI USAR ESSE MAPA PARA CONTROLAR AS INTERAÇÕES DO JOGADOR COM O AMBIENTE!
    }
}
