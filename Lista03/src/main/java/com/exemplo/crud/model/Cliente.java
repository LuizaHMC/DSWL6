package com.exemplo.crud.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int id;
    private String nome;
    private String email;
    private List<Produto> produtos = new ArrayList<>();

    public Cliente() {
        this.id = (int) (Math.random() * 1000);
    }

    public Cliente(String nome, String email) {
        this(); 
        this.nome = nome;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome  = nome;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }
    
    public void removerProduto(Produto produto) {
        produtos.remove(produto);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", produtos=" + produtos +
                '}';
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}