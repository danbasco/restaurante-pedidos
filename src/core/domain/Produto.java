package core.domain;

public class Produto {

    private long id;
    private String nome;
    private float preco;

    public Produto(long id, String nome, float preco) {
        setId(id);
        setNome(nome);
        setPreco(preco);
    }

    public long getId() {return this.id;}
    public String getNome() {return this.nome;}
    public float getPreco() {return this.preco;}

    public void setId(long id) {this.id = id;}
    public void setNome(String nome) {this.nome = nome;}
    public void setPreco(float preco) {this.preco = preco;}



}



