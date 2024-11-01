package com.exemplo.crud.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.exemplo.crud.model.Cliente;
import com.exemplo.crud.model.Produto;

import java.util.ArrayList;

public class CompraServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    
    private ArrayList<Cliente> clientes = (ArrayList<Cliente>) ClienteServlet.getClientes();
    private ArrayList<Produto> produtos = (ArrayList<Produto>) ProdutoServlet.getProdutos();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("acao");
      


        if ("adicionar".equals(action)) {
            int clienteId = Integer.parseInt(request.getParameter("clienteId"));
            int produtoId = Integer.parseInt(request.getParameter("produtoId"));

            Cliente cliente = encontrarClientePorId(clienteId);
            Produto produto = encontrarProdutoPorId(produtoId);            
            
            System.out.println("Cliente  recebido: " + cliente);
            System.out.println("Produto  recebido: " + produto);
            

            if (cliente != null && produto != null) {
                cliente.adicionarProduto(produto);
                response.sendRedirect("compras.html");
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Cliente ou Produto não encontrado.");
            }

        } else if ("remover".equals(action)) {
            int clienteId = Integer.parseInt(request.getParameter("clienteId"));
            int produtoId = Integer.parseInt(request.getParameter("produtoId"));

            Cliente cliente = encontrarClientePorId(clienteId);
            Produto produto = encontrarProdutoPorId(produtoId);

            if (cliente != null && produto != null) {
                cliente.removerProduto(produto);
                response.sendRedirect("compras.html");
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Cliente ou Produto não encontrado.");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int clienteId = Integer.parseInt(request.getParameter("clienteId"));

        Cliente cliente = encontrarClientePorId(clienteId);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (cliente != null) {
            out.println("<html><body>");
            out.println("<h2>Produtos comprados por " + cliente.getNome() + ":</h2>");
            out.println("<ul>");
            for (Produto p : cliente.getProdutos()) {
                out.println("<li>" + p.getNome() + " - R$ " + p.getPreco() + "</li>");
            }
            out.println("</ul>");
            out.println("<a href=\"index.html\">Voltar para a página inicial</a>");
            out.println("</body></html>");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Cliente não encontrado.");
        }
    }
    
    private Cliente encontrarClientePorId(int id) {
        if (clientes == null) {
            return null; 
        }
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    private Produto encontrarProdutoPorId(int id) {
    	if (produtos == null) {
            return null; 
        }
        for (Produto produto : produtos) {
        	System.out.println("Produto  r: " + produto);
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }
}
