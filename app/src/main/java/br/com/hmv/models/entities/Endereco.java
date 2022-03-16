package br.com.hmv.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_enderecos")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="codigo_endereco", nullable = false, unique=true, length = 36)
	private String codigoEndereco;

	@Column(nullable = true)
	private String descricao;

	@Column(nullable = true)
	private String logradouro;

	@Column(nullable = true)
	private Integer numero;

	@Column(nullable = true)
	private String complemento;

	@Column(nullable = true)
	private String cidade;

	@Column(nullable = true)
	private String uf;

	@Column(nullable = true)
	private Integer cep;
}
