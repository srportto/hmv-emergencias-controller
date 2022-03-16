package br.com.hmv.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class EnderecoInsertRequestDTO {

    @NotBlank(message = "Campo descricao deve ser preenchido")
    private String descricao;

    @NotBlank(message = "Campo logradouro deve ser preenchido")
    private String logradouro;

    @NotNull(message = "Campo numero deve ser preenchido")
    private Integer numero;

    @NotBlank(message = "Campo complemento deve ser preenchido")
    private String complemento;

    @NotBlank(message = "Campo cidade deve ser preenchido")
    private String cidade;

    @NotBlank(message = "Campo UF deve ser preenchido")
    private String uf;

    @NotNull(message = "Campo CEP deve ser preenchido")
    private Integer cep;

}