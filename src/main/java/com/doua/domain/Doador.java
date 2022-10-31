package com.doua.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Doador")
public class Doador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDoador")
	private Long idDoador;

	private String cpf;
	private String nome;
	
	@Column(name = "sobrenome")
	private String sobrenome;
	
	public Doador() {
		
	}
	
	public Doador(Long idCliente, String cpf, String nome, String sobrenome) {
		this.idDoador = idCliente;
		this.cpf = cpf;
		this.nome = nome;
		this.sobrenome = sobrenome;
	}

	public Long getId() {
		return idDoador;
	}

	public void setId(Long idCliente) {
		this.idDoador = idCliente;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
}
