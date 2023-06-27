package com.doua.domain.criador;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "criador")
public class Criador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "urlfoto")
	private String urlFoto;
	@Column(name = "token")
	private String token;
	@Column(name = "email")
	private String email;
	@Column(name = "nome")
	private String nome;
}
