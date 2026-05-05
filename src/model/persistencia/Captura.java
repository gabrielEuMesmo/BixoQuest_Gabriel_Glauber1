package model.persistencia;
import model.Jogador;
import model.Acao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Captura implements Serializable {

    //INFO do Save
    private int versao;
    private String id;
    private String savedAt;

    //Estado do Jogador
    private Jogador jogador;
    private List<Acao> acoesDoTurno;

    //Estado do Tempo
    private int semestreAtual;
    private int semanaAtual;
    private String blocoTempoAtual;
    private String tipoSemanaAtual;

    //Estado do Mundo (Mapeamento de amizades)
    private Map<String, Integer> relacionamentoPorNpc;

    // Construtor Limpo: Ele apenas recebe os dados do GameController e guarda.
    public Captura(int versao, String id, String savedAt, Jogador jogador,
                   int semestreAtual, int semanaAtual, String blocoTempoAtual,
                   String tipoSemanaAtual, Map<String, Integer> relacionamentoPorNpc,
                   List<Acao> acoesDoTurno) {

        this.versao = versao;
        this.id = id;
        this.savedAt = savedAt;

        // O jogador já vem montado do GameController
        this.jogador = jogador;

        this.semestreAtual = semestreAtual;
        this.semanaAtual = semanaAtual;
        this.blocoTempoAtual = blocoTempoAtual;
        this.tipoSemanaAtual = tipoSemanaAtual;
        this.relacionamentoPorNpc = relacionamentoPorNpc;

        //Inicialização segura da lista (evita NullPointerException)
        this.acoesDoTurno = (acoesDoTurno != null) ? new ArrayList<>(acoesDoTurno) : new ArrayList<>();
    }

    // --- Getters ---
    // O GameController vai usar esses métodos na hora do "Load" para pegar os dados de volta.

    public int getVersao() { return versao; }
    public String getId() { return id; }
    public String getSavedAt() { return savedAt; }
    public Jogador getJogador() { return jogador; }
    public int getSemestreAtual() { return semestreAtual; }
    public int getSemanaAtual() { return semanaAtual; }
    public String getBlocoTempoAtual() { return blocoTempoAtual; }
    public String getTipoSemanaAtual() { return tipoSemanaAtual; }
    public Map<String, Integer> getRelacionamentoPorNpc() { return relacionamentoPorNpc; }
    public List<Acao> getAcoesDoTurno() { return new ArrayList<>(acoesDoTurno); }
}