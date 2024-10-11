package br.edu.ifsp.tads.dswl6;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class Exercicio04 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private int contador = 0;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        contador++;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><h1>Este servlet foi acessado " + contador + " vezes.</h1></html>");
    }
}
