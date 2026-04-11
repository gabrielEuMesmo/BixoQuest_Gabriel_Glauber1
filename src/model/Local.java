package model;
import model.enums.BlocoTempo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Local {
    private String nome;
    // Agenda: cada BlocoTempo tem uma lista de NPCs presentes
    private Map<BlocoTempo, List<NPC>> agendaDoLocal;

    //CONSTRUTOR
    public Local(String nome) {
        this.nome           = nome;
        this.agendaDoLocal  = new HashMap<>();
    }

    //GET do NOME DO LOCAL
    public String getNome() { return nome; }

    //ESSE METODO REGISTRA A PRESENÇA DE UM NPC EM UM DETERMINADO BLOCO DE TEMPO!
    public void registrarPresenca(BlocoTempo turno, NPC personagem) {
        // O ".puIfAbsent" É UM METODO DO MAP QUE CRIA UMA NOVA CHAVE RESPECTIVA AO "turno" COM UMA NOVA LISTA CASO ESSA CHAVE NÃO EXISTA
        agendaDoLocal.putIfAbsent(turno, new ArrayList<>());
        agendaDoLocal.get(turno).add(personagem);
    }

    //ESSE METODO RETORNA A LISTA DE NPCs PRESENTES NESTE LOCAL NO TURNO INFORMADO!
    public List<NPC> getPersonagensNoTurno(BlocoTempo turno) {
        return agendaDoLocal.getOrDefault(turno, new ArrayList<>());
    }

    //ESSE METODO RETORNA TODOS OS NPCs!
    public List<NPC> getTodosPersonagens() {
        List<NPC> todos = new ArrayList<>();
        for (List<NPC> lista : agendaDoLocal.values()) {
            for (NPC npc : lista) {
                if (!todos.contains(npc)) {
                    todos.add(npc);
                }
            }
        }
        return todos; //AQUI O "todos" CONTEM TODOS OS NPCs REGISTRADOS NESTE LOCAL NA FORMA DE LISTA!
    }

    //ESSE METODO VERIFICA SE O LOCAL ESTÁ VAZIO NO TURNO INFORMADO, RETORNANDO TRUE SE NÃO HOUVER NENHUM NPC REGISTRADO!
    public boolean estaVazioNoTurno(BlocoTempo turno) {
        return getPersonagensNoTurno(turno).isEmpty();
    }
}
