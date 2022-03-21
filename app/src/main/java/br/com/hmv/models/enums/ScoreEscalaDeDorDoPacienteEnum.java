package br.com.hmv.models.enums;

public enum ScoreEscalaDeDorDoPacienteEnum {
    LIGEIRA(1, 50),
    MODERADA(2, 75),
    INTENSA(3, 100),
    MAXIMA(4, 150);


    private Integer codigoRegiaoDor;
    private Integer score;

    ScoreEscalaDeDorDoPacienteEnum(Integer codigoEscalaDeDor, Integer score) {
        this.codigoRegiaoDor = codigoEscalaDeDor;
        this.score = score;
    }

    public Integer getCodigoEscalaDeDor() {
        return this.codigoRegiaoDor;
    }

    public Integer getScoreEscalaDeDor() {
        return this.score;
    }

    public static ScoreEscalaDeDorDoPacienteEnum obterEscalaDeDor(Integer codigoEscalaDeDor) {
        for (ScoreEscalaDeDorDoPacienteEnum itemEscalaDeDorEnum : ScoreEscalaDeDorDoPacienteEnum.values()) {
            if (itemEscalaDeDorEnum.getCodigoEscalaDeDor() == codigoEscalaDeDor) {
                return itemEscalaDeDorEnum;
            }
        }
        throw new IllegalArgumentException(String.format("Codigo de escala de dor %i não conhecido ", codigoEscalaDeDor));
    }

}
