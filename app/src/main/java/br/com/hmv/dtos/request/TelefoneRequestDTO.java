package br.com.hmv.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TelefoneRequestDTO {

    @JsonProperty("codigo_pais")
    private Integer codigoPais;

    @JsonProperty("codigo_area")
    private Integer codigoArea;

    @JsonProperty("numero")
    private Integer numero;

    @JsonProperty("descricao")
    private String descricao;
}