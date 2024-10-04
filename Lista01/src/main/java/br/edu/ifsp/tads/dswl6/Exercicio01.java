package br.edu.ifsp.tads.dswl6;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class Exercicio01 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType ("text.html");
		PrintWriter out = response.getWriter();
		out.println("<html><h1>Bem-vindo ao mundo dos Servlets!</h1></html>");
	}

}
