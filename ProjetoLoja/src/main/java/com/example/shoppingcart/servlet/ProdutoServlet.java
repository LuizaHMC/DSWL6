package com.example.shoppingcart.servlet;

import com.example.shoppingcart.dto.ProdutoDTO;
import com.example.shoppingcart.model.Produto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ProdutoServlet extends HttpServlet {

    private ProdutoDTO produtoDTO = new ProdutoDTO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Produto> produtos = produtoDTO.list();
        request.setAttribute("produtos", produtos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("produto-list.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        double preco = Double.parseDouble(request.getParameter("preco"));
        Produto produto = new Produto(id, nome, preco);
        produtoDTO.save(produto);
        response.sendRedirect("produtos");
    }
}
