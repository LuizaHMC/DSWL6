package com.projeto.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.projeto.dto.TarefaDTO;
import com.projeto.model.Tarefa;

/**
 * Servlet implementation class TarefasPorMembroServlet
 */
@WebServlet("/tarefasPorMembro")
public class TarefasPorMembroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TarefaDTO tarefaDTO = new TarefaDTO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int membroId = Integer.parseInt(request.getParameter("id")); 
        
        List<Tarefa> tarefas = tarefaDTO.listByDono(membroId);
        request.setAttribute("tarefas", tarefas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("tarefas.jsp");
        dispatcher.forward(request, response);
    }

}
