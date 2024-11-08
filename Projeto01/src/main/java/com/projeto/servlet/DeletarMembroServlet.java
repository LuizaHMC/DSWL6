package com.projeto.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.projeto.dto.EquipeDTO;
import com.projeto.dto.TarefaDTO;
import com.projeto.model.Tarefa;

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

                
                TarefaDTO tarefaDTO = new TarefaDTO();
                List<Tarefa> tarefas = tarefaDTO.listByDono(membroId);  

                if (!tarefas.isEmpty()) {
                    
                    request.setAttribute("errorMessage", "Este membro não pode ser deletado porque possui tarefas associadas.");
                } else {
                   
                    EquipeDTO equipeDTO = new EquipeDTO();
                    equipeDTO.delete(membroId);
                    request.setAttribute("successMessage", "Membro deletado com sucesso.");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "ID inválido.");
            }
        } else {
            request.setAttribute("errorMessage", "Sem ID fornecido.");
        }

        
        RequestDispatcher dispatcher = request.getRequestDispatcher("equipes");
        dispatcher.forward(request, response);
    }
}
