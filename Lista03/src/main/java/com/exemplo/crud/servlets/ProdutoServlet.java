package com.exemplo.crud.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.exemplo.crud.model.Produto;

public class ProdutoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static List<Produto> produtos = new ArrayList<>();
    public static List<Produto> getProdutos() {
        return produtos;
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Lista de Produtos:</h2>");
        out.println("<ul>");
        for (Produto p : produtos) {
            out.println("<li>" + p.getId() + " - " + p.getNome() + " - R$ " + p.getPreco() + "</li>");
        }
        out.println("</ul>");
        out.println("<a href=\"index.html\">Voltar para a página inicial</a>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("acao");

        if ("criar".equals(action)) {
            String nome = request.getParameter("nome");
            double preco = Double.parseDouble(request.getParameter("preco"));
            Produto produto = new Produto(nome, preco);
            produtos.add(produto);
            response.sendRedirect("produtos.html");

        } else if ("editar".equals(action)) {
            String nomeAntigo = request.getParameter("nomeAntigo");
            String novoNome = request.getParameter("nome");
            double novoPreco = Double.parseDouble(request.getParameter("preco"));

            for (Produto produto : produtos) {
                if (produto.getNome().equals(nomeAntigo)) {
                    produto.setNome(novoNome);
                    produto.setPreco(novoPreco);
                    break;
                }
            }
            response.sendRedirect("produtos.html");

        } else if ("excluir".equals(action)) {
            String nome = request.getParameter("nome");

            produtos.removeIf(produto -> produto.getNome().equals(nome));

            response.sendRedirect("produtos.html");

        } else {
            doGet(request, response);
        }
    }
}