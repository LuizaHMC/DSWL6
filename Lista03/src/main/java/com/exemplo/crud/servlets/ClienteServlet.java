package com.exemplo.crud.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.exemplo.crud.model.Cliente;

public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static List<Cliente> clientes = new ArrayList<>();
    public static List<Cliente> getClientes() {
        return clientes;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
    	response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Lista de Clientes:</h2>");
        out.println("<ul>");
        for (Cliente c : clientes) {
            out.println("<li>" + c.getId()+ " - " + c.getNome() + " - " +c.getEmail() + "</li>");
        }
        out.println("</ul>");
        out.println("<a href=\"index.html\">Voltar para a página inicial</a>");
        out.println("</body></html>");
	}
    	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getParameter("acao");
    	
        if ("criar".equals(action)) {
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            Cliente cliente = new Cliente(nome,email);
            clientes.add(cliente);
            response.sendRedirect("clientes.html");
        } else if ("editar".equals(action)) {
        	String nomeAntigo = request.getParameter("nomeAntigo");
            String novoNome = request.getParameter("nome");
            String novoEmail = request.getParameter("email");
            
            for (Cliente cliente : clientes) {
                if (cliente.getNome().equals(nomeAntigo)) {
                    cliente.setNome(novoNome);
                    cliente.setEmail(novoEmail);
                    break;
                }
            }
            response.sendRedirect("clientes.html");
        } else if ("excluir".equals(action)) {
        	String nome = request.getParameter("nome");
        	 for (Cliente cliente : clientes) {
                 if (cliente.getNome().equals(nome)) {
                	 clientes.remove(cliente);
                     break;
                 }
             }            
            response.sendRedirect("clientes.html");
        } else {
            doGet(request, response);
        }
        
        
    }
}