package br.com.hmv.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConvenioRequestDTO {
    private static final long serialVersionUID = 1L;

    @JsonProperty("codigo")
    private String codigoConvenio;

    @JsonProperty("descricao")
    private String descricaoConvenio;

    @JsonProperty("numero_carteira")
    private String numeroCarteiraConvenio;

    @JsonProperty("data_validade")
    private LocalDate dataValidadeConvenio;
}
