package br.com.hmv.dtos.responses;

import br.com.hmv.services.validation.emergencias.criacao.EmergenciaInsertValid;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EmergenciaInsertValid
public class DetalhePedidoAtendimentoDefaultResponseDTO {

    @JsonProperty("data_nascimento")
    private LocalDate dataNascimento;

    @JsonProperty("relato_motivo_pedido_atendimento")
    private String relatoMotivoPedidoAtendimento;

    private List<DorDefaultResponseDTO> dores = new ArrayList<>();

    private List<SintomaEmergenciaDefaultResponsetDTO> sintomas = new ArrayList<>();

    @JsonProperty("habitos_paciente")
    private List<HabitoPacienteEmergenciaDefaultResponsetDTO> habitosPaciente = new ArrayList<>();

    @JsonProperty("eventos_traumaticos")
    private List<EventoTraumaticoEmergenciaDefaultResponsetDTO> eventosTraumaticos;
}