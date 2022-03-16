package br.com.hmv.dtos.responses;

import br.com.hmv.models.enums.GrupoFuncaoFuncionarioEnum;
import br.com.hmv.models.enums.StatusFuncionarioEnum;
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
public class FuncionarioForListResponseDTO {

    @JsonProperty("id_funcionario")
    private String idFuncionario;

    @JsonProperty("email")
    private String email;

    @JsonIgnore
    private String senha;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("crm")
    private String crm;

    @JsonIgnore
    private String primeiroNome;

    @JsonProperty("nome_completo")
    private String nomeCompleto;

    @JsonProperty("data_nascimento")
    private LocalDate dataNascimento;

    @JsonIgnore
    private EnderecoDefaultResponseDTO endereco;

    @JsonProperty("grupo_funcao")
    private GrupoFuncaoFuncionarioEnum grupoFuncaoFuncionario;

    @JsonProperty("status")
    private StatusFuncionarioEnum statusFuncionario;
//
//    @JsonIgnore
//    private TelefoneDTO telefone;
//
//    @JsonIgnore
//    private Set<EspecialidadeDTO> especialidades = new HashSet<>();
}