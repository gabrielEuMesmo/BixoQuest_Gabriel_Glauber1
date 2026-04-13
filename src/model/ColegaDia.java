package model;
import java.util.List;

public class ColegaDia implements NPC {
    private String nome;
    private String area;
    private int semestre;
    private int nivelRelacionamento;
    private List<CenaDialogo> roteiro;

    //CONSTRUTOR
    public ColegaDia(String nome, String area, int semestre, List<CenaDialogo> roteiro) {
        this.nome               = nome;
        this.area               = area;
        this.semestre           = semestre;
        this.nivelRelacionamento = 0;
        this.roteiro            = roteiro;
    }

    //GETs de TUDO!
    public int getSemestre() { return semestre; }
    public String getNome() { return nome; }
    public String getArea() { return area; }
    public int getNivelRelacionamento() { return nivelRelacionamento; }

    //METODO PARA AUMENTAR RELACIONAMENTO A CADA INTeRAÇÃO COM NPC
    public void aumentarRelacionamento(int valor) {
        this.nivelRelacionamento += valor;
        if (this.nivelRelacionamento < 0) {
            this.nivelRelacionamento = 0;
        }
    }

    //TRAS O ROTEIRO ESPECIFICO PARA O NIVEL DE RELACIONAMENTO ATUAL, PARA EXIBIR A CENA CORRESPONDENTE AO JOGADOR!
    public CenaDialogo getCenaAtual() {
        if (this.nivelRelacionamento >= roteiro.size()) {
            return roteiro.get(roteiro.size() - 1);
        }
        return roteiro.get(this.nivelRelacionamento);
    }
}
