package core.domain;

import java.util.UUID;

// A classe Cliente é uma entidade de domínio que representa o usuário do sistema.
// Ela não tem dependências de frameworks ou tecnologias externas.
public class Cliente {

    private UUID id;
    private String nome;
    private String email;

    // Construtor
    public Cliente(String nome, String email) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.email = email;
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    // Setters para os casos de uso de atualização
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
