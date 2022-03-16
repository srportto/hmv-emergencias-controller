package br.com.hmv.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SintomaRequestDTO {
    private static final long serialVersionUID = 1L;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("score")
    private Integer score;

}
