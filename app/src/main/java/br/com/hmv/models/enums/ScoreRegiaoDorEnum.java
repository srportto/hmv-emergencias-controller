package br.com.hmv.models.enums;

public enum ScoreRegiaoDorEnum {
    BRACOS(1, 50),
    PERNAS(2, 50),
    BARRIGA(3, 100),
    PEITORAL(4, 300),
    COSTAS(5, 100),
    CABECA(6, 200);


    private Integer codigoRegiaoDor;
    private Integer score;

    ScoreRegiaoDorEnum(Integer codigoRegiaoDor, Integer score) {
        this.codigoRegiaoDor = codigoRegiaoDor;
        this.score = score;
    }

    public Integer getCodigoRegiaoDor() {
        return this.codigoRegiaoDor;
    }

    public Integer getScoreRegiaoDor() {
        return this.score;
    }

    public static ScoreRegiaoDorEnum obterRegiaoDor(Integer codigoRegiaoDor) {
        for (ScoreRegiaoDorEnum itemRegiaoDorEnum : ScoreRegiaoDorEnum.values()) {
            if (itemRegiaoDorEnum.getCodigoRegiaoDor() == codigoRegiaoDor) {
                return itemRegiaoDorEnum;
            }
        }
        throw new IllegalArgumentException(String.format("Codigo de regiao da dor %i não conhecido ", codigoRegiaoDor));
    }

}
