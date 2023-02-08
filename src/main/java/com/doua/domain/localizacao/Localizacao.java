package com.doua.domain.localizacao;

import com.doua.domain.acao.Acao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "localizacao")
public class Localizacao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "latitude")
	private Double latitude;
	@Column(name = "longitude")
	private Double longitude;
}
