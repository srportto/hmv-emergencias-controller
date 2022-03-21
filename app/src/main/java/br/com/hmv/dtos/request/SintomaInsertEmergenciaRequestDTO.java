package br.com.hmv.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SintomaInsertEmergenciaRequestDTO {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id_sintoma")
    private Integer idSintoma;

    @JsonProperty("relatado_pelo_paciente")
    private Boolean relatadoPeloPaciente;

}
