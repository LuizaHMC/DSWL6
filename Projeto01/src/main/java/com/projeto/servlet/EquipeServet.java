package com.projeto.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.projeto.dto.EquipeDTO;
import com.projeto.dto.ProjetoDTO;
import com.projeto.model.Equipe;
import com.projeto.model.Projeto;

/**
 * Servlet implementation class EquipeServet
 */
@WebServlet("/equipes")
public class EquipeServet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EquipeServet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProjetoDTO projetoDTO = new ProjetoDTO();
        List<Projeto> projetos = projetoDTO.list();
        
        EquipeDTO equipeDTO = new EquipeDTO();
        List<Equipe> membros = equipeDTO.list();
        
        
        request.setAttribute("projetos", projetos);
        request.setAttribute("membros", membros);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("equipes-list.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String sobrenome = request.getParameter("sobrenome");
        String email = request.getParameter("email");
        String cargo = request.getParameter("cargo");

        
        Equipe membro = new Equipe();
        membro.setNome(nome);
        membro.setSobrenome(sobrenome);
        membro.setEmail(email);
        membro.setCargo(cargo);

        
        EquipeDTO equipeDTO = new EquipeDTO();
        equipeDTO.save(membro);

        
        response.sendRedirect("equipes?action=list"); 
    }
}
