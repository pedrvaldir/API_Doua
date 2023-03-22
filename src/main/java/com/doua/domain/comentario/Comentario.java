package com.doua.domain.comentario;

import com.doua.domain.acao.Acao;
import com.doua.domain.criador.Criador;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "comentario")
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "criador_id")
	private Criador criador;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Acao acao;
	@Column(name = "descricao")
	private String descricao;
}
