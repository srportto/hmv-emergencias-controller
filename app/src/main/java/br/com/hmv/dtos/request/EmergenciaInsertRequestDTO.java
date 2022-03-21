package br.com.hmv.dtos.request;

import br.com.hmv.models.enums.StatusEmergenciaEnum;
import br.com.hmv.services.validation.emergencias.criacao.EmergenciaInsertValid;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EmergenciaInsertValid
public class EmergenciaInsertRequestDTO {

    @NotBlank(message = "Campo id_paciente deve ser preenchido")
    @JsonProperty("id_paciente")
    private String idPaciente;

    @NotBlank(message = "Campo primeiro_nome_paciente deve ser preenchido")
    @JsonProperty("primeiro_nome_paciente")
    private String primeiroNomePaciente;

    @NotNull(message = "Campo status deve ser preenchido")
    @JsonProperty("status")
    private StatusEmergenciaEnum statusEmergencia;

    @JsonProperty("detalhes_pedido_atendimento")
    private DetalhePedidoAtendimentoRequestDTO detalhesPedidoAtendimento;


}