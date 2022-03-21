package br.com.hmv.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_detalhes_pedido_emergencia")
public class DetalheDoPedidoDeAtendimento implements Serializable {
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_detalhes_pedido", nullable = false, unique = true, length = 36)
    private String codigoDetalhesPedido;


    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimentoPaciente;

    @Column(name = "relato_em_texto_motivo_pedido_atendimento", columnDefinition = "TEXT", nullable = true)
    private String relatoEmTextoDoPedidoDeAtendimento;


    @ManyToMany
    @JoinTable(name = "tb_pedidos_dores",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "dor_id")
    )
    Set<RegiaoDorEscala> dores = new HashSet<>();


    @ManyToMany
    @JoinTable(name = "tb_pedidos_sintomas",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "sintoma_id")
    )
    Set<Sintoma> sintomas = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "tb_pedidos_habitos_pacientes",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "habitos_paciente_id")
    )
    Set<HabitoPaciente> habitosPaciente = new HashSet<>();


    @ManyToMany
    @JoinTable(name = "tb_pedidos_eventos_traumaticos",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "evento_traumatico_id")
    )
    Set<EventoTraumatico> eventosTraumaticos = new HashSet<>();

}
