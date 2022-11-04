package com.doua.domain.comentario;

import javax.persistence.*;

@Entity
@Table(name = "Comentario")
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "idCriador")
	private Long idCriador;
	@Column(name = "idDoacao")
	private Long idDoacao;
	@Column(name = "comentario")
	private String comentario;

	public Comentario() {
	}

	public Comentario(Long id, Long idCriador, Long idDoacao, String comentario) {
		this.id = id;
		this.idDoacao = idDoacao;
		this.idCriador = idCriador;
		this.comentario = comentario;
	}

	public Long getIdDoacao() {
		return idDoacao;
	}

	public void setIdDoacao(Long idDoacao) {
		this.idDoacao = idDoacao;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIdCriador(Long idCriador) {
		this.idCriador = idCriador;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Long getId() {
		return id;
	}

	public Long getIdCriador() {
		return idCriador;
	}

	public String getComentario() {
		return comentario;
	}
}
