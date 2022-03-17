package br.com.hmv.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitoPacienteDefaultResponseDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("score")
    private Integer score;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;
}
