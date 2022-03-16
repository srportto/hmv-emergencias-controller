package br.com.hmv.models.enums;

public enum CadastroPacienteEnum {
    COMPLETO(1),
    SIMPLES(2);

    private Integer codigoStatusCadastro;

    CadastroPacienteEnum(Integer codigoStatusCadastro) {
        this.codigoStatusCadastro = codigoStatusCadastro;
    }

    public Integer getCodigoStatusCadastroPaciente() {
        return this.codigoStatusCadastro;
    }

    public static CadastroPacienteEnum obterStatusCadastroPaciente(Integer codigoStatusCadastroPaciente) {
        for (CadastroPacienteEnum status : CadastroPacienteEnum.values()) {
            if (status.getCodigoStatusCadastroPaciente() == codigoStatusCadastroPaciente) {
                return status;
            }
        }
        throw new IllegalArgumentException(String.format("Status cadastro paciente %i não conhecido ", codigoStatusCadastroPaciente));
    }
}
