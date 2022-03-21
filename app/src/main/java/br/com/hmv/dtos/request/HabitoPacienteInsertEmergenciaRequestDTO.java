package br.com.hmv.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitoPacienteInsertEmergenciaRequestDTO {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id_habito")
    private Integer idHabito;

    @JsonProperty("paciente_possui")
    private Boolean pacientePossui;

}
