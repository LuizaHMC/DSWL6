package com.example.shoppingcart.model;

import java.util.List;

public class Carrinho {
    private int id;
    private Cliente cliente; // O cliente dono do carrinho
    private List<Produto> produtos; // Lista de produtos no carrinho

    // Construtor padrão
    public Carrinho() {}

    // Construtor com parâmetros
    public Carrinho(int id, Cliente cliente, List<Produto> produtos) {
        this.id = id;
        this.cliente = cliente;
        this.produtos = produtos;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    // Método toString para depuração
    @Override
    public String toString() {
        return "Carrinho{" +
                "id='" + id + '\'' +
                ", cliente=" + cliente +
                ", produtos=" + produtos +
                '}';
    }
}
