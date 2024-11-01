package com.exemplo.crud.model;

public class Produto {
    private int id;
    private String nome;
    private double preco;

    public Produto() {
        this.id = (int) (Math.random() * 1000);
    }

    public Produto(String nome, double preco) {
        this(); 
        this.nome = nome;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                '}';
    }
}