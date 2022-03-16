package br.com.hmv.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_pacientes")
public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_paciente", nullable = false, unique = true, length = 36)
    private String idPaciente;


    @Column(name = "primeiro_nome", nullable = false)
    private String primeiroNome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @EqualsAndHashCode.Include
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "indicador_tipo_cadastro_realizado", nullable = false, length = 1)
    private Integer indicadorTipoCadastroRealizado;


    @Column(name = "nome_completo", nullable = true)
    private String nomeCompleto;


    @Column(name = "nome_completo_mae", nullable = true)
    private String nomeCompletoMae;

    @Column(name = "nome_completo_pai", nullable = true)
    private String nomeCompletoPai;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @Embedded
    private Telefone telefone;


    @Embedded
    private Convenio convenio;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataAtualizacao;
}
