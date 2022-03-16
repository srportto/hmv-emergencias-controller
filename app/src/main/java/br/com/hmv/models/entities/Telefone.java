package br.com.hmv.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Telefone {

    @JoinColumn(name = "codigo_pais", nullable = true)
    private Integer codigoPais;

    @JoinColumn(name = "codigo_area", nullable = true)
    private Integer codigoArea;

    @JoinColumn(name = "numero", nullable = true)
    private Integer numero;

    @JoinColumn(name = "descricao", nullable = true)
    private String descricao;

}
