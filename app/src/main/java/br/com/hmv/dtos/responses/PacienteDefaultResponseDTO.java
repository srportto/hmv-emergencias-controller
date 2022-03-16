package br.com.hmv.dtos.responses;

import br.com.hmv.models.enums.CadastroPacienteEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PacienteDefaultResponseDTO {

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

    @JsonProperty("nome_completo")
    private String nomeCompleto;

    @JsonProperty("nome_completo_mae")
    private String nomeCompletoMae;


    @JsonProperty("nome_completo_pai")
    private String nomeCompletoPai;

    @JsonProperty("endereco")
    private EnderecoDefaultResponseDTO endereco;

    @JsonProperty("telefone")
    private TelefoneDefaultResponseDTO telefone;

    @JsonProperty("convenio")
    private ConvenioDefaultResponseDTO convenio;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;
}