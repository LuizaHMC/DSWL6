package com.example.shoppingcart.servlet;

import com.example.shoppingcart.dto.ClienteDTO;
import com.example.shoppingcart.model.Cliente;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ClienteServlet extends HttpServlet {

    private ClienteDTO clienteDTO = new ClienteDTO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Cliente> clientes = clienteDTO.list();
        request.setAttribute("clientes", clientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("cliente-list.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        Cliente cliente = new Cliente(id, nome, email);
        clienteDTO.save(cliente);
        response.sendRedirect("clientes");
    }
}