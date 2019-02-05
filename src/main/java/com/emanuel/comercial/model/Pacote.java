package com.emanuel.comercial.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "pk_id"))
public class Pacote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 4, max = 80)
	private String nome;

	@NotNull
	@NotEmpty
	@Size(max = 20)
	@Column(name = "codigo", length = 20, nullable = false)
	private String codigo;

	@Size(max = 150)
	@Column(name = "descricao", length = 150, nullable = true)
	private String descricao;

	@NotNull
	@Column(name = "data_registro", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRegistro;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	public Pacote() {
	}

	public Pacote(Long id, @NotNull @Size(min = 4, max = 80) String nome,
			@NotNull @NotEmpty @Size(max = 20) String codigo, @Size(max = 150) String descricao,
			@NotNull Date dataRegistro, @NotNull Usuario usuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.codigo = codigo;
		this.descricao = descricao;
		this.dataRegistro = dataRegistro;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pacote other = (Pacote) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
