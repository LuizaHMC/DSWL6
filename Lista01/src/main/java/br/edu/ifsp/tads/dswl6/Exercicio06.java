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

@WebServlet("/comentarios")
public class Exercicio06 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private List<String> comentarios = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String comentario = request.getParameter("comentario");
        if (comentario != null && !comentario.trim().isEmpty()) {
            comentarios.add(comentario);
        }

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><h1>Comentários:</h1><ul>");
        for (String c : comentarios) {
            out.println("<li>" + c + "</li>");
        }
        out.println("</ul></html>");
    }
}