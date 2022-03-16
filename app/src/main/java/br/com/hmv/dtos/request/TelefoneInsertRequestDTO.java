package br.com.hmv.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class TelefoneInsertRequestDTO {
    @NotNull(message = "Campo codigo_pais deve ser preenchido")
    @JsonProperty("codigo_pais")
    private Integer codigoPais;

    @NotNull(message = "Campo codigo_area deve ser preenchido")
    @JsonProperty("codigo_area")
    private Integer codigoArea;

    @NotNull(message = "Campo numero deve ser preenchido")
    @JsonProperty("numero")
    private Integer numero;

    @JsonProperty("descricao")
    private String descricao;
}