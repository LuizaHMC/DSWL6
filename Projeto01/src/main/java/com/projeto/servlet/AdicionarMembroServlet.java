package com.projeto.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.projeto.dto.EquipeDTO;
import com.projeto.dto.ProjetoDTO;
import com.projeto.dto.TarefaDTO;
import com.projeto.model.Equipe;
import com.projeto.model.Tarefa;
import com.projeto.utils.ConnectionFactory;

/**
 * Servlet implementation class AtualizarTarefaServlet
 */
@WebServlet("/AdicionarMembro")
public class AdicionarMembroServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String tarefaIdStr = request.getParameter("tarefaId");
	    String membroIdStr = request.getParameter("membroId");

	    if (tarefaIdStr != null && membroIdStr != null) {
	        try {
	            int tarefaId = Integer.parseInt(tarefaIdStr);
	            int membroId = Integer.parseInt(membroIdStr);

	            // Usar TarefaDTO para atualizar o dono da tarefa
	            TarefaDTO tarefaDTO = new TarefaDTO();
	            tarefaDTO.atualizarDonoDaTarefa(tarefaId, membroId);
	            request.setAttribute("successMessage", "Membro adicionado à tarefa com sucesso.");
	        } catch (NumberFormatException e) {
	            request.setAttribute("errorMessage", "IDs inválidos.");
	        } catch (SQLException e) {
	            request.setAttribute("errorMessage", "Erro ao adicionar membro à tarefa: " + e.getMessage());
	        }
	    } else {
	        request.setAttribute("errorMessage", "Os IDs da tarefa e do membro devem ser fornecidos.");
	    }

	    RequestDispatcher dispatcher = request.getRequestDispatcher("tarefa-list.jsp");
	    dispatcher.forward(request, response);
	}


}

