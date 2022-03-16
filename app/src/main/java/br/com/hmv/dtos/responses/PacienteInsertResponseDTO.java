package br.com.hmv.dtos.responses;

import br.com.hmv.models.enums.CadastroPacienteEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PacienteInsertResponseDTO {

    @JsonProperty("id_paciente")
    private String idPaciente;

    @JsonProperty("primeiro_nome")
    private String primeiroNome;

    @JsonProperty("data_nascimento")
    private LocalDate dataNascimento;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("email")
    private String email;

    @JsonIgnore
    private String senha;

    @JsonProperty("tipo_cadastro_realizado")
    private CadastroPacienteEnum indicadorCadastro;
}