package br.edu.ifsp.tads.dswl6;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/acesso")
public class Exercicio07 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Map<String, Integer> acessosPorUsuario = new HashMap<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        if (nome != null && !nome.trim().isEmpty()) {
            acessosPorUsuario.put(nome, acessosPorUsuario.getOrDefault(nome, 0) + 1);
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><h1>Contador de Acessos por Usuário</h1><ul>");
        for (Map.Entry<String, Integer> entry : acessosPorUsuario.entrySet()) {
            out.println("<li>" + entry.getKey() + " acessou " + entry.getValue() + " vezes.</li>");
        }
        out.println("</ul></html>");
    }
}