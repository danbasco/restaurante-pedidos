package core.domain;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private long id;
    private String nome;
    private String email;
    private Endereco endereco;

    public Cliente(long id, String nome, String email, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}