package br.com.hmv.dtos.request;

import br.com.hmv.models.enums.StatusFuncionarioEnum;
import br.com.hmv.services.validation.paciente.atualizacao_status.FuncionarioAtualizaStatusValid;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@FuncionarioAtualizaStatusValid
public class FuncionarioAtualizaStatusRequestDTO {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Campo status deve ser preenchido")
    @JsonProperty("status")
    private StatusFuncionarioEnum statusFuncionario;
}