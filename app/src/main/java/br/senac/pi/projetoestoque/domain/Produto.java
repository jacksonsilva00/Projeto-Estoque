package br.senac.pi.projetoestoque.domain;

import java.io.Serializable;

public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private int quantidade;
    private double preco;
    private String nome;

    public Produto(){

    }
    public Produto(Long id, int quantidade, String nome, double preco) {
        this.id = id;
        this.quantidade = quantidade;
        this.nome = nome;
        this.preco = preco;
    }

    public String toString() {
        return this.nome;
        //return super.toString();
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
