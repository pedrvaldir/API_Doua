package com.doua.domain.usuario;


import javax.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email")
    private String email;

    @Column(name = "nome")
    private String nome;

    @Column(name = "foto")
    private String foto;

    @Column(name = "token")
    private String token;

    @Column(name = "tipoDoadorDonatario")
    private int tipoDoadorDonatario;

    public Usuario(String email, String nome, String foto, String token, int tipoDoadorDonatario) {
        this.email = email;
        this.nome = nome;
        this.foto = foto;
        this.token = token;
        this.tipoDoadorDonatario = tipoDoadorDonatario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getTipoDoadorDonatario() {
        return tipoDoadorDonatario;
    }

    public void setTipoDoadorDonatario(int tipoDoadorDonatario) {
        this.tipoDoadorDonatario = tipoDoadorDonatario;
    }
}
