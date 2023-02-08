package com.doua.domain.tutorial;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tutorial")
public class Tutorial {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "titulo")
	private String titulo;
	@Column(name = "icon")
	private String icon;
	@Column(name = "descricao")
	private String descricao;
}
