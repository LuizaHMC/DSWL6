package com.projeto.servlet;

import com.projeto.dto.ProjetoDTO;
import com.projeto.dto.TarefaDTO;
import com.projeto.model.Projeto;
import com.projeto.model.Tarefa;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/AdicionarTarefa")
public class TarefaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TarefaDTO tarefaDTO = new TarefaDTO();
    ProjetoDTO projetoDTO = new ProjetoDTO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        
        
        List<Projeto> projetos = projetoDTO.list();
        request.setAttribute("projetos", projetos); 
        
        if ("nova".equals(action)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("tarefa-nova.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("tarefas", tarefaDTO.list());
            RequestDispatcher dispatcher = request.getRequestDispatcher("tarefa-list.jsp");
            dispatcher.forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        java.sql.Date dataInicio = java.sql.Date.valueOf(request.getParameter("dataInicio"));
        java.sql.Date dataFim = java.sql.Date.valueOf(request.getParameter("dataFim"));
        int projetoId = Integer.parseInt(request.getParameter("projetoId"));
        Integer donoId = null;         
     
        boolean erroOcorreu = false;
        
     
        if (projetoDTO.getById(projetoId) == null) {
            request.setAttribute("errorMessage", "Projeto não encontrado.");
            request.getRequestDispatcher("tarefa-list.jsp").forward(request, response);
            return;
        }

        Tarefa tarefa = new Tarefa();
        tarefa.setNome(nome);
        tarefa.setDescricao(descricao);
        tarefa.setDataInicio(dataInicio);
        tarefa.setDataFim(dataFim);
        tarefa.setProjetoId(projetoId);
        tarefa.setDonoId(donoId); 

        tarefaDTO.save(tarefa);
        response.sendRedirect("tarefa-list.jsp");
        
        
    }
}
