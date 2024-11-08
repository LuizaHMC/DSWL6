package com.projeto.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.projeto.dto.EquipeDTO;
import com.projeto.dto.TarefaDTO;

/**
 * Servlet implementation class DeletarMembroServlet
 */
@WebServlet("/DeletarMembro")
public class DeletarMembroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String membroIdStr = request.getParameter("membroId");

	    if (membroIdStr != null) {
	        try {
	            int membroId = Integer.parseInt(membroIdStr);

	            // Usar TarefaDTO para deletar o membro
	            EquipeDTO equipeDTO = new EquipeDTO();
	            equipeDTO.delete(membroId);
	            request.setAttribute("successMessage", "Membro adicionado à tarefa com sucesso.");
	        } catch (NumberFormatException e) {
	            request.setAttribute("errorMessage", "IDs inválidos.");
	        }
	    } else {
	        request.setAttribute("errorMessage", "Sem ID");
	    }

	    RequestDispatcher dispatcher = request.getRequestDispatcher("equipes");
	    dispatcher.forward(request, response);
	}

}
