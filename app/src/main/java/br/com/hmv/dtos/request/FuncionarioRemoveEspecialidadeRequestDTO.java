package br.com.hmv.dtos.request;

import br.com.hmv.services.validation.paciente.remove_especialidade.FuncionarioRemoveEspecialidadeValid;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@FuncionarioRemoveEspecialidadeValid
public class FuncionarioRemoveEspecialidadeRequestDTO {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Campo id_especialidade deve ser preenchido")
    @JsonProperty("id_especialidade")
    private Long idEspecialidade;
}