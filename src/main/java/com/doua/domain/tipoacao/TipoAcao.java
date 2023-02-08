package com.doua.domain.tipoacao;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tipoacao")
public class TipoAcao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "tipo")
	private String Tipo;
}
