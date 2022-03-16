package br.com.hmv.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Convenio {

    @JoinColumn(name = "codigo", nullable = true)
    private String codigoConvenio;

    @JoinColumn(name = "descricao", nullable = true)
    private String descricaoConvenio;

    @JoinColumn(name = "numero_carteira", nullable = true)
    private String numeroCarteiraConvenio;

    @JoinColumn(name = "data_validade", nullable = true)
    private LocalDate dataValidadeConvenio;

}
