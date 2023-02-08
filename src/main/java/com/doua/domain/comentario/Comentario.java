package com.doua.domain.comentario;

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
	@Column(name = "criador_id")
	private Long criadorId;
	@Column(name = "acaoId")
	private Long acaoId;
	@Column(name = "descricao")
	private String descricao;
}
