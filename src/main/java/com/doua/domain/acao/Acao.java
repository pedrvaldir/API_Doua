package com.doua.domain.acao;

import com.doua.domain.comentario.Comentario;
import com.doua.domain.localizacao.Localizacao;
import com.doua.domain.tipoacao.TipoAcao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "acao")
public class Acao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@OneToOne
	@JoinColumn(name = "tipoacao_id", nullable=false)
	private TipoAcao tipoAcao;
	@ManyToOne
	@JoinColumn(name = "localizacao_id", nullable=false)
	private Localizacao localizacao;
	@Column(name = "titulo")
	private String titulo;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "url_img")
	private String urlImg;
	@Column(name = "qtd_votos")
	private int qtdVotos;
	@OneToMany(
			mappedBy = "acao",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<Comentario> comentarios = new ArrayList<>();

	public void addComentario(Comentario comment) {
		comentarios.add(comment);
		comment.setAcao(this);
	}

	public void removeComentario(Comentario comment) {
		comentarios.remove(comment);
		comment.setAcao(null);
	}
}
