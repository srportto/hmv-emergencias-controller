package br.com.hmv.dtos.request;

import br.com.hmv.services.validation.emergencias.criacao.EmergenciaInsertValid;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EmergenciaInsertValid
public class DetalhePedidoAtendimentoRequestDTO {


    @NotNull(message = "Campo data_nascimento deve ser preenchido")
    @JsonProperty("data_nascimento")
    private LocalDate dataNascimento;

    @NotBlank(message = "Campo relato_motivo_pedido_atendimento deve ser preenchido")
    @JsonProperty("relato_motivo_pedido_atendimento")
    private String relatoMotivoPedidoAtendimento;

    private List<DorRequestDTO> dores;

    private List<SintomaInsertEmergenciaRequestDTO> sintomas;

    private List<HabitoPacienteInsertEmergenciaRequestDTO> habitosPaciente;

    private List<EventoTraumaticoInsertEmergenciaRequestDTO> eventosTraumaticos;


}