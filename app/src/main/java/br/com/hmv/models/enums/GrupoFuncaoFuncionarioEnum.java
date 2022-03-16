package br.com.hmv.models.enums;

public enum GrupoFuncaoFuncionarioEnum {
    OUTROS(1L),
    MEDICO(2L),
    ADMINISTRATIVO(3L),
    MASTER(4L);

    private long codigoGrupoFuncao;

    GrupoFuncaoFuncionarioEnum(long codigoGrupoFuncao) {
        this.codigoGrupoFuncao = codigoGrupoFuncao;
    }

    public long getCodigoGrupoFuncaoFuncionario() {
        return this.codigoGrupoFuncao;
    }

    public static GrupoFuncaoFuncionarioEnum obterGrupoFuncaoFuncionario(long codigoGrupoFuncao) {
        for (GrupoFuncaoFuncionarioEnum status : GrupoFuncaoFuncionarioEnum.values()) {
            if (status.getCodigoGrupoFuncaoFuncionario() == codigoGrupoFuncao) {
                return status;
            }
        }
        throw new IllegalArgumentException(String.format("Grupo funcao funcionario %i não conhecido ", codigoGrupoFuncao));
    }
}
