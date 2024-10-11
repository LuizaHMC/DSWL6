package br.edu.ifsp.tads.dswl6;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addNome")
public class Exercicio05 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private List<String> nomes = new ArrayList<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        if (nome != null && !nome.trim().isEmpty()) {
            nomes.add(nome);
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><h1>Nomes Armazenados:</h1><ul>");
        for (String n : nomes) {
            out.println("<li>" + n + "</li>");
        }
        out.println("</ul></html>");
    }
}