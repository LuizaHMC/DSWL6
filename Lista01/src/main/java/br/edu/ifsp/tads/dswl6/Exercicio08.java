package br.edu.ifsp.tads.dswl6;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class Exercicio08 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private List<Produto> produtos = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String preco = request.getParameter("preco");

        if (nome != null && preco != null && !nome.trim().isEmpty() && !preco.trim().isEmpty()) {
            produtos.add(new Produto(nome, Double.parseDouble(preco)));
        }

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><h1>Lista de Produtos</h1><ul>");
        for (Produto p : produtos) {
            out.println("<li>" + p.getNome() + " - " + p.getPreco() + " reais</li>");
        }
        out.println("</ul></html>");
    }
}

class Produto {
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}
