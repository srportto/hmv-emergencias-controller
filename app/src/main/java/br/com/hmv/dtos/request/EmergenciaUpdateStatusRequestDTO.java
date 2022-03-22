package br.com.hmv.dtos.request;

import br.com.hmv.models.enums.StatusEmergenciaEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmergenciaUpdateStatusRequestDTO {
    private static final long serialVersionUID = 1L;

    @JsonProperty("status")
    private StatusEmergenciaEnum status;

}
