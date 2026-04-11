package model.enums;

public enum BlocoTempo {
    MANHA_1,
    MANHA_2,
    TARDE_1,
    TARDE_2,
    NOITE;

    public BlocoTempo proximo() {
        BlocoTempo[] valores = BlocoTempo.values();
        int proxIdx = this.ordinal() + 1;
        if (proxIdx >= valores.length) {
            return null; // Fim do dia
        }
        return valores[proxIdx];
    }

    public String exibir() {
        switch (this) {
            case MANHA_1:  return "Manha (inicio)";
            case MANHA_2:  return "Manha (tarde)";
            case TARDE_1:  return "Tarde (inicio)";
            case TARDE_2:  return "Tarde (fim)";
            case NOITE:    return "Noite";
            default:       return this.name();
        }
    }
}
