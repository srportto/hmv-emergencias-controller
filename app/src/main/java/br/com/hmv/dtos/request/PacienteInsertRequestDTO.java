package br.com.hmv.dtos.request;

import br.com.hmv.services.validation.paciente.criacao.PacienteInsertValid;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@PacienteInsertValid
public class PacienteInsertRequestDTO {

    @NotBlank(message = "Campo primeiro_nome deve ser preenchido")
    @JsonProperty("primeiro_nome")
    private String primeiroNome;

    @NotBlank(message = "Campo CPF deve ser preenchido")
    @JsonProperty("cpf")
    private String cpf;

    @NotNull(message = "Campo data_nascimento deve ser preenchido")
    @JsonProperty("data_nascimento")
    private LocalDate dataNascimento;

    @NotBlank(message = "Campo email deve ser preenchido")
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "Campo senha deve ser preenchido")
    @JsonProperty("senha")
    private String senha;
}