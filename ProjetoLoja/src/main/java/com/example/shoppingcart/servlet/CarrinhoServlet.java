package com.example.shoppingcart.servlet;

import com.example.shoppingcart.dto.CarrinhoDTO;
import com.example.shoppingcart.dto.ClienteDTO;
import com.example.shoppingcart.model.Carrinho;
import com.example.shoppingcart.model.Cliente;
import com.example.shoppingcart.model.Produto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class CarrinhoServlet extends HttpServlet {

    private CarrinhoDTO carrinhoDTO = new CarrinhoDTO();
    private ClienteDTO clienteDTO = new ClienteDTO(); // Para listar e associar clientes

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Carrinho> carrinhos = carrinhoDTO.list();
        List<Cliente> clientes = clienteDTO.list();
        request.setAttribute("carrinhos", carrinhos);
        request.setAttribute("clientes", clientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("carrinho-list.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String clienteId = request.getParameter("clienteId");
        Cliente cliente = clienteDTO.list().stream()
                                    .filter(c -> c.getId().equals(clienteId))
                                    .findFirst()
                                    .orElse(null);

        if (cliente != null) {
            // Aqui você pode obter os produtos de uma lista vinda da requisição
            // Para simplificar, vamos passar uma lista vazia
            Carrinho carrinho = new Carrinho(id, cliente, List.of());
            carrinhoDTO.save(carrinho);
        }

        response.sendRedirect("carrinhos");
    }
}