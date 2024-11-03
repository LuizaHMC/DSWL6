package com.projeto.servlet;

import com.projeto.dto.ProjetoDTO;
import com.projeto.model.Projeto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/projetos")
public class ProjetoServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProjetoDTO projetoDTO = new ProjetoDTO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("projetos", projetoDTO.list());
        RequestDispatcher dispatcher = request.getRequestDispatcher("projeto-list.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");

        // Usando java.sql.Date
        java.sql.Date dataInicio = java.sql.Date.valueOf(request.getParameter("dataInicio"));
        java.sql.Date dataFim = java.sql.Date.valueOf(request.getParameter("dataFim"));

        Projeto projeto = new Projeto();
        projeto.setNome(nome);
        projeto.setDescricao(descricao);
        projeto.setDataInicio(dataInicio);
        projeto.setDataFim(dataFim);

        projetoDTO.save(projeto);
        response.sendRedirect("projetos");
    }
}