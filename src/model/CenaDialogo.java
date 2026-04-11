package model;

import java.util.ArrayList;
import java.util.List;

public class CenaDialogo {
    private String falaPrincipal;
    private List<OpcaoResposta> opcoes;

    //CONSTRUTOR
    public CenaDialogo(String falaPrincipal, List<OpcaoResposta> opcoes) {

        //VALIDA SE A FALA RECEBIDA AQUI NUNCA SERIA INVALIDA (NULA)
        if (falaPrincipal == null || falaPrincipal.trim().isEmpty()) { //.TRIM REMOVE OS ESPAÇOS EM BRANCO!
            this.falaPrincipal = "... (silencio constrangedor)";
        } else {
            this.falaPrincipal = falaPrincipal;
        }

        //VALIDA QUE A LISTA DE OPÇÕES NUNCA SEJA NULA!
        if (opcoes == null) {
            this.opcoes = new ArrayList<>();
        } else {
            this.opcoes = opcoes;
        }
    }

    //GETs
    public String getFalaPrincipal() {
        return falaPrincipal;
    }
    public List<OpcaoResposta> getOpcoes() {
        return opcoes;
    }
    public int getTotalOpcoes() {return opcoes.size();}
}
