package com.doua.domain;

import javax.persistence.*;

@Entity
@Table(name = "Criador")
public class Criador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCriador")
	private Long idCriador;
	@Column(name = "urlFoto")
	private String urlFoto;
	@Column(name = "token")
	private String token;
	@Column(name = "email")
	private String email;
	@Column(name = "nome")
	private String nome;

	public Criador() {}

	public Criador(String urlFoto, String token, String email, String nome) {
		this.urlFoto = urlFoto;
		this.token = token;
		this.email = email;
		this.nome = nome;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getIdCriador() {
		return idCriador;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public String getToken() {
		return token;
	}

	public String getEmail() {
		return email;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
