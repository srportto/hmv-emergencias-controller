package br.com.hmv.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_emergencias")
public class Emergencia implements Serializable {
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_emergencia", nullable = false, unique = true, length = 36)
    private String codigoEmergencia;

    @Column(name = "id_paciente", nullable = false, unique = true, length = 36)
    private String idPaciente;

    @Column(name = "primeiro_nome_paciente", nullable = false)
    private String primeiroNomePaciente;

    @Column(name = "score_prioridade", nullable = false)
    private Integer score;

    @Column(name = "status", nullable = false)
    private Long codigoStatusEmergencia;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_detalhes_pedido_atendimento")
    private DetalheDoPedidoDeAtendimento detalhesPedidoAtendimento;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataAtualizacao;
}
