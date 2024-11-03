package com.projeto.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.projeto.dto.TarefaDTO;


@WebServlet("/marcarConcluida")
public class MarcarTarefaConcluidaServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	    private TarefaDTO tarefaDTO = new TarefaDTO();

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String idParam = request.getParameter("id");
	        
	        if (idParam == null || idParam.isEmpty()) {
	            
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID da tarefa não pode ser vazio.");
	            return;
	        }
	        
	        int tarefaId;
	        try {
	            tarefaId = Integer.parseInt(idParam);
	        } catch (NumberFormatException e) {
	            
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID da tarefa inválido.");
	            return;
	        }

	        boolean concluida = request.getParameter("concluida") != null; 

	        tarefaDTO.marcarComoConcluida(tarefaId, concluida); 
	       

	        System.out.println("ID da Tarefa: " + tarefaId);
	        System.out.println("Checkbox Concluída: " + concluida);
	        
	        response.sendRedirect("equipes"); 
	    }

}
