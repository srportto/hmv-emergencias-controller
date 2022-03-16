package br.com.hmv.models.enums;

public enum StatusFuncionarioEnum {
    ATIVO(1L),
    INATIVO(2L);

    private long codigoStatus;

    StatusFuncionarioEnum(long codigoStatus) {
        this.codigoStatus = codigoStatus;
    }

    public long getCodigoStatusFuncionario() {
        return this.codigoStatus;
    }

    public static StatusFuncionarioEnum obterStatusFuncionario(long codigoStatusFuncionario) {
        for (StatusFuncionarioEnum status : StatusFuncionarioEnum.values()) {
            if (status.getCodigoStatusFuncionario() == codigoStatusFuncionario) {
                return status;
            }
        }
        throw new IllegalArgumentException(String.format("Status funcionario %i não conhecido ", codigoStatusFuncionario));
    }
}
