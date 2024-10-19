package com.example.shoppingcart.dto;

import java.util.ArrayList;
import java.util.List;
import com.example.shoppingcart.model.Produto;

public class ProdutoDTO implements IDTO<Produto>{
	
    // Simulando um banco de dados em memória
    private static List<Produto> produtos = new ArrayList<>();

    @Override
    public void save(Produto produto) {
        produtos.add(produto);
    }

    @Override
    public void update(Produto produto) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId().equals(produto.getId())) {
                produtos.set(i, produto);
                break;
            }
        }
    }

    @Override
    public List<Produto> list() {
        return produtos;
    }
}
