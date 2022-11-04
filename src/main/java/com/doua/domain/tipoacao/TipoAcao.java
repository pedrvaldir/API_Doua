package com.doua.domain.tipoacao;

import javax.persistence.*;

@Entity
@Table(name = "TipoAcao")
public class TipoAcao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "TipoAcao")
	private String TipoAcao;

	public TipoAcao() {
	}

	public TipoAcao(Long id, String tipoAcao) {
		this.id = id;
		TipoAcao = tipoAcao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoAcao() {
		return TipoAcao;
	}

	public void setTipoAcao(String tipoAcao) {
		TipoAcao = tipoAcao;
	}
}
