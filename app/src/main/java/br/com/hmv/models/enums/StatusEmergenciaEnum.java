package br.com.hmv.models.enums;

public enum StatusEmergenciaEnum {
    PACIENTE_EM_TRANSITO(1),
    PACIENTE_EM_ESPERA(2),
    PACIENTE_EM_ATENDIMENTO(3),
    PACIENTE_EM_POS_ATENDIMENTO(4),
    PACIENTE_CANCELOU(5),
    ATENDENTE_HMV_CANCELOU(6),
    PACIENTE_ATENDIDO_E_LIBERADO(7);

    private long codigoStatus;

    StatusEmergenciaEnum(long codigoStatusEmergencia) {
        this.codigoStatus = codigoStatusEmergencia;
    }

    public long getCodigoStatusEmergencia() {
        return this.codigoStatus;
    }

    public static StatusEmergenciaEnum obterStatusEmergencia(long codigoStatusEmergencia) {
        for (StatusEmergenciaEnum status : StatusEmergenciaEnum.values()) {
            if (status.getCodigoStatusEmergencia() == codigoStatusEmergencia) {
                return status;
            }
        }
        throw new IllegalArgumentException(String.format("Status emergencia %i não conhecido ", codigoStatusEmergencia));
    }
}
