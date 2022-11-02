package com.doua.domain.criador;


import javax.persistence.*;

@Entity
@Table(name = "Criador")
public class Criador {

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


    public Criador(String email, String nome, String foto, String token) {
        this.email = email;
        this.nome = nome;
        this.foto = foto;
        this.token = token;
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
}
