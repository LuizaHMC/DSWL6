package br.edu.ifsp.tads.dswl6;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;


@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("param");

        // Adiciona um atributo à requisição para ser lido no próximo servlet
        request.setAttribute("message", "Parâmetro recebido: " + param);

        // Despacha a requisição para o ProcessServlet
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ProcessServlet");
        dispatcher.forward(request, response);
    }
}
