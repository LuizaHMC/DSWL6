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

	    System.out.println("tarefaId: " + tarefaIdStr);
	    System.out.println("membroId: " + membroIdStr);
	    System.out.println("AdicionarMembroServlet chamado!");


	    if (tarefaIdStr != null && membroIdStr != null) {
	        try {
	            int tarefaId = Integer.parseInt(tarefaIdStr);
	            int membroId = Integer.parseInt(membroIdStr);

	            
	            try {
	                atualizarDonoDaTarefa(tarefaId, membroId);
	                request.setAttribute("successMessage", "Membro adicionado à tarefa com sucesso.");
	            } catch (SQLException e) {
	                request.setAttribute("errorMessage", "Erro ao adicionar membro à tarefa: " + e.getMessage());
	            }
	        } catch (NumberFormatException e) {
	            request.setAttribute("errorMessage", "IDs inválidos.");
	        }
	    } else {
	        request.setAttribute("errorMessage", "Os IDs da tarefa e do membro devem ser fornecidos.");
	    }

	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("tarefa-list.jsp");
	    dispatcher.forward(request, response);
	}

    private void atualizarDonoDaTarefa(int tarefaId, int donoId) throws SQLException {
        Connection conn = null; 
        PreparedStatement pstmt = null;
        String sql = "UPDATE tarefas SET dono_id = ? WHERE id = ?";

        try {
            
            conn = ConnectionFactory.getConnection(); 
            System.out.println("Conexão ao banco de dados estabelecida.");

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, donoId); 
            pstmt.setInt(2, tarefaId); 

            System.out.println("Preparando a execução da instrução SQL: " + pstmt.toString());
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Linhas afetadas: " + rowsAffected);
        } catch (SQLException e) {
            System.err.println("Erro durante a execução da instrução SQL: " + e.getMessage());
            throw e; 
        } finally {
            if (pstmt != null) {
                pstmt.close();
                System.out.println("PreparedStatement fechado.");
            }
            if (conn != null) {
                conn.close();
                System.out.println("Conexão com o banco de dados fechada.");
            }
        }
    }
}

