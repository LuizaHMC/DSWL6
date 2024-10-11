package br.edu.ifsp.tads.dswl6;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class Exercicio03 extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("nome"); 
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
       
         out.println("<h1>Bem vindo "+ username +"</h1>");
        
	}

	

}
