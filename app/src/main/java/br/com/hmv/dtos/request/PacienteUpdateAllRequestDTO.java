package br.com.hmv.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PacienteUpdateAllRequestDTO {

    @JsonProperty("nome_completo")
    private String nomeCompleto;

    @JsonProperty("email")
    private String email;

    @JsonProperty("nome_completo_mae")
    private String nomeCompletoMae;

    @JsonProperty("nome_completo_pai")
    private String nomeCompletoPai;

    @JsonProperty("endereco")
    private EnderecoRequestDTO endereco;

    @JsonProperty("telefone")
    private TelefoneRequestDTO telefone;

    @JsonProperty("convenio")
    private ConvenioRequestDTO convenio;

}