package br.com.hmv.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneDefaultResponseDTO {
    private static final long serialVersionUID = 1L;

    @JsonProperty("codigo_pais")
    private Integer codigoPais;

    @JsonProperty("codigo_area")
    private Integer codigoArea;

    @JsonProperty("numero")
    private Integer numero;

    @JsonProperty("descricao")
    private String descricao;
}
