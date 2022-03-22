package br.com.hmv.dtos.responses;

import br.com.hmv.models.enums.StatusEmergenciaEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmergenciaDefaultResponseDTO {

    @JsonProperty("codigo_emergencia")
    private String codigoEmergencia;

    @JsonProperty("id_paciente")
    private String idPaciente;

    @JsonProperty("primeiro_nome_paciente")
    private String primeiroNomePaciente;

    @JsonProperty("score_prioridade")
    private Integer score;

    @JsonProperty("status")
    private StatusEmergenciaEnum statusEmergencia;

    @JsonProperty("detalhes_pedido_atendimento")
    private DetalhePedidoAtendimentoDefaultResponseDTO detalhesPedidoAtendimento;

    @JsonProperty("data_criacao")
    private LocalDateTime dataCriacao;

    @JsonProperty("data_atualizacao")
    private LocalDateTime dataAtualizacao;
}