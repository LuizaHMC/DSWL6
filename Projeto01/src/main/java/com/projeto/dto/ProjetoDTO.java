package com.projeto.dto;

import com.projeto.model.Projeto;
import com.projeto.model.Tarefa;
import com.projeto.utils.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDTO implements IDTO<Projeto> {

	@Override
	public void save(Projeto projeto) {
	    String sql = "INSERT INTO projetos (nome, descricao, data_inicio, data_fim) VALUES (?, ?, ?, ?)";
	    try (Connection conn = ConnectionFactory.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	         
	        System.out.println("Preparando para inserir o projeto: " + projeto.getNome());
	        
	        stmt.setString(1, projeto.getNome());
	        stmt.setString(2, projeto.getDescricao());
	        stmt.setDate(3, new java.sql.Date(projeto.getDataInicio().getTime()));
	        stmt.setDate(4, new java.sql.Date(projeto.getDataFim().getTime()));

	        int rowsAffected = stmt.executeUpdate();
	        System.out.println("Número de linhas afetadas: " + rowsAffected);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();  // Isso imprimirá o stack trace do erro no console
	    }
	}

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM projetos WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Projeto> list() {
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT * FROM projetos";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setId(rs.getInt("id"));
                projeto.setNome(rs.getString("nome"));
                projeto.setDescricao(rs.getString("descricao"));
                projeto.setDataInicio(rs.getDate("data_inicio"));
                projeto.setDataFim(rs.getDate("data_fim"));
                projetos.add(projeto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        System.out.println("Projetos encontrados: " + projetos.size()); // Adicione esta linha para depuração
        return projetos;
    }

    public List<Tarefa> listByProjeto(int projetoId) {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefas WHERE projeto_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, projetoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Tarefa tarefa = new Tarefa();
                    tarefa.setId(rs.getInt("id"));
                    tarefa.setNome(rs.getString("nome"));
                    tarefa.setDescricao(rs.getString("descricao"));
                    tarefa.setDataInicio(rs.getDate("data_inicio"));
                    tarefa.setDataFim(rs.getDate("data_fim"));
                    tarefa.setProjetoId(rs.getInt("projeto_id"));
                    tarefas.add(tarefa);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tarefas;
    }

    @Override
    public Projeto getById(int id) {
        Projeto projeto = null;
        String sql = "SELECT * FROM projetos WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    projeto = new Projeto();
                    projeto.setId(rs.getInt("id"));
                    projeto.setNome(rs.getString("nome"));
                    projeto.setDescricao(rs.getString("descricao"));
                    projeto.setDataInicio(rs.getDate("data_inicio"));
                    projeto.setDataFim(rs.getDate("data_fim"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projeto;
    }
}
