package br.com.hmv.dtos.request;

import br.com.hmv.models.enums.ScoreEscalaDeDorDoPacienteEnum;
import br.com.hmv.models.enums.ScoreRegiaoDorEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DorRequestDTO {
    private static final long serialVersionUID = 1L;

    @JsonProperty("regiao")
    private ScoreRegiaoDorEnum regiaoDor;

    @JsonProperty("escala_de_dor")
    private ScoreEscalaDeDorDoPacienteEnum escalaDeDorDoPaciente;

}
