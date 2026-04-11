package model.enums;

public enum SemanaEnum {
    LIVRE,
    PROVA_M1,
    PROVA_M2,
    PROVA_M3,
    FECHAMENTO;

    public boolean isProva() {
        return this == PROVA_M1 || this == PROVA_M2 || this == PROVA_M3;
    }

    public String exibir() {
        switch (this) {
            case LIVRE:      return "Semana Livre";
            case PROVA_M1:   return "Semana de Prova (M1)";
            case PROVA_M2:   return "Semana de Prova (M2)";
            case PROVA_M3:   return "Semana de Prova (M3)";
            case FECHAMENTO: return "Fechamento do Semestre";
            default:         return this.name();
        }
    }
}
