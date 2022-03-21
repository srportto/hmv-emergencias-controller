package br.com.hmv.dtos.responses;

import br.com.hmv.dtos.request.DetalhePedidoAtendimentoRequestDTO;
import br.com.hmv.models.enums.StatusEmergenciaEnum;
import br.com.hmv.services.validation.emergencias.criacao.EmergenciaInsertValid;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EmergenciaInsertValid
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


    private DetalhePedidoAtendimentoRequestDTO detalhesPedidoAtendimento;


}