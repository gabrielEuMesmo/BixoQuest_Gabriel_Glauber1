package model;
import java.util.List;

public class Professor implements NPC {
    private String nome;
    private String area;
    private int nivelRelacionamento;
    private List<CenaDialogo> roteiro;

    //CONSTRUTOR
    public Professor(String nome, String area, List<CenaDialogo> roteiro) {
        this.nome               = nome;
        this.area               = area;
        this.nivelRelacionamento = 0;
        this.roteiro            = roteiro;
    }

    //TODOS OS GETs
    public String getNome() { return nome; }
    public String getArea() { return area; }
    public int getNivelRelacionamento() { return nivelRelacionamento; }

    //METODO PARA AUMENTAR RELACIONAMENTO A CADA INTeRAÇÃO COM NPC
    public void aumentarRelacionamento(int valor) {
        this.nivelRelacionamento += valor;
        // Trava: relacionamento nunca fica negativo
        if (this.nivelRelacionamento < 0) {
            this.nivelRelacionamento = 0;
        }
    }

    //TRAS O ROTEIRO ESPECIFICO PARA O NIVEL DE RELACIONAMENTO ATUAL, PARA EXIBIR A CENA CORRESPONDENTE AO JOGADOR!
    public CenaDialogo getCenaAtual() {
        // Se a amizade ultrapassar o tamanho do roteiro, repete a ultima cena
        if (this.nivelRelacionamento >= roteiro.size()) {
            return roteiro.get(roteiro.size() - 1);
        }
        return roteiro.get(this.nivelRelacionamento);
    }
}
