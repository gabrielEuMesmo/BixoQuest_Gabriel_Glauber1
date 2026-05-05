package model;
import java.util.List;
import java.util.ArrayList;

public class OpcaoVisualNovel extends Opcao {
    public String tituloLocal;
    public List<String> textosEscolhas;
    public boolean acaoCritica;

    //ESSE "nomeLocalReal" É USADO PARA OPÇÕES DE AÇÃO CRÍTICA, ONDE O JOGADOR PRECISA SELECIONAR UM LOCAL ESPECÍFICO
    // PARA REALIZAR A AÇÃO, E ESSE NOME SERVE PARA BUSCAR O LOCAL ATUAL!
    public String nomeLocalReal;

    //CONSTRUTOR PARA OPÇÕES DE AÇÃO CRÍTICA, ONDE O JOGADOR PODE SELECIONAR QUALQUER LOCAL PARA REALIZAR A AÇÃO!
    public OpcaoVisualNovel(String tituloLocal, List<String> textosEscolhas, boolean acaoCritica) {
        super(this.tituloLocal = tituloLocal);
        this.textosEscolhas = textosEscolhas != null ? textosEscolhas : new ArrayList<>();
        this.acaoCritica    = acaoCritica;
        this.nomeLocalReal  = null;
    }

    //CONSTRUTOR PARA MENUS DE AÇÃO CRÍTICA, ONDE O JOGADOR PRECISA SELECIONAR UM LOCAL ESPECÍFICO PARA REALIZAR A AÇÃO!
    public OpcaoVisualNovel(String tituloLocal, List<String> textosEscolhas, boolean acaoCritica, String nomeLocalReal) {
        super(this.tituloLocal =  tituloLocal);
        this.textosEscolhas = textosEscolhas != null ? textosEscolhas : new ArrayList<>();
        this.acaoCritica    = acaoCritica;
        this.nomeLocalReal  = nomeLocalReal;
    }

    //TODOS OS GETs
    public String       getTituloLocal()    { return tituloLocal; }
    public List<String> getTextosEscolhas() { return textosEscolhas; }
    public boolean      isAcaoCritica()     { return acaoCritica; }
    public String       getNomeLocalReal()  { return nomeLocalReal; }
}
