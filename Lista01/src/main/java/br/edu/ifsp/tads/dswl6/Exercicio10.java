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

@WebServlet("/tarefas")
public class Exercicio10 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private List<String> tarefas = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tarefa = request.getParameter("tarefa");
        if (tarefa != null && !tarefa.trim().isEmpty()) {
            tarefas.add(tarefa);
        }

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><h1>Lista de Tarefas</h1><ul>");
        for (String t : tarefas) {
            out.println("<li>" + t + "</li>");
        }
        out.println("</ul></html>");
    }
}