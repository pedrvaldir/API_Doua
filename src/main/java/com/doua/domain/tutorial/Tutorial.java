package com.doua.domain.tutorial;

import javax.persistence.*;

@Entity
@Table(name = "Tutorial")
public class Tutorial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "titulo")
	private String titulo;
	@Column(name = "imgBase64")
	private String imgBase64;
	@Column(name = "descricao")
	private String descricao;

	public Tutorial() {
	}

	public Tutorial(String titulo, String imgBase64, String descricao) {
		this.titulo = titulo;
		this.imgBase64 = imgBase64;
		this.descricao = descricao;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setImgBase64(String imgBase64) {
		this.imgBase64 = imgBase64;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getImgBase64() {
		return imgBase64;
	}

	public String getDescricao() {
		return descricao;
	}
}
