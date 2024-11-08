package com.projeto.dto;

import com.projeto.model.Tarefa;
import com.projeto.utils.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarefaDTO implements IDTO<Tarefa> {

	@Override
	public void save(Tarefa tarefa) {
		String sql = "INSERT INTO tarefas (nome, descricao, data_inicio, data_fim, projeto_id, dono_id, concluida) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    try (Connection conn = ConnectionFactory.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, tarefa.getNome());
	        stmt.setString(2, tarefa.getDescricao());
	        stmt.setDate(3, new java.sql.Date(tarefa.getDataInicio().getTime()));
	        stmt.setDate(4, new java.sql.Date(tarefa.getDataFim().getTime()));
	        stmt.setInt(5, tarefa.getProjetoId());

	        
	        if (tarefa.getDonoId() != null) {
	            stmt.setInt(6, tarefa.getDonoId().intValue());
	        } else {
	            stmt.setNull(6, java.sql.Types.INTEGER);
	        }

	        
	        stmt.setBoolean(7, tarefa.isConcluida()); 
	        
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM tarefas WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Tarefa> list() {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefas ORDER BY projeto_id";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getInt("id"));
                tarefa.setNome(rs.getString("nome"));
                tarefa.setDescricao(rs.getString("descricao"));
                tarefa.setDataInicio(rs.getDate("data_inicio"));
                tarefa.setDataFim(rs.getDate("data_fim"));
                tarefa.setProjetoId(rs.getInt("projeto_id"));
                tarefa.setDonoId(rs.getInt("dono_id"));
                tarefa.setConcluida(rs.getBoolean("concluida")); 
                tarefas.add(tarefa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tarefas;
    }

    @Override
    public List<Tarefa> listByProjeto(int projetoId) {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefas WHERE projeto_id = ? ORDER BY nome";
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
                    tarefa.setDonoId(rs.getInt("dono_id"));
                    tarefa.setConcluida(rs.getBoolean("concluida"));
                    tarefas.add(tarefa);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tarefas;
    }

    @Override
    public Tarefa getById(int id) {
        Tarefa tarefa = null;
        String sql = "SELECT * FROM tarefas WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tarefa = new Tarefa();
                    tarefa.setId(rs.getInt("id"));
                    tarefa.setNome(rs.getString("nome"));
                    tarefa.setDescricao(rs.getString("descricao"));
                    tarefa.setDataInicio(rs.getDate("data_inicio"));
                    tarefa.setDataFim(rs.getDate("data_fim"));
                    tarefa.setProjetoId(rs.getInt("projeto_id"));
                    tarefa.setDonoId(rs.getInt("dono_id")); 
                    tarefa.setConcluida(rs.getBoolean("concluida"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tarefa;
    }
    

    public List<Tarefa> listByDono(int donoId) {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefas WHERE dono_id = ? ORDER BY nome";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, donoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Tarefa tarefa = new Tarefa();
                    tarefa.setId(rs.getInt("id"));
                    tarefa.setNome(rs.getString("nome"));
                    tarefa.setDescricao(rs.getString("descricao"));
                    tarefa.setDataInicio(rs.getDate("data_inicio"));
                    tarefa.setDataFim(rs.getDate("data_fim"));
                    tarefa.setProjetoId(rs.getInt("projeto_id"));
                    tarefa.setDonoId(rs.getInt("dono_id"));
                    tarefa.setConcluida(rs.getBoolean("concluida"));
                    tarefas.add(tarefa);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tarefas;
    }

	public void marcarComoConcluida(int tarefaId, boolean concluida) {
		String sql = "UPDATE tarefas SET concluida = ? WHERE id = ?";
	    try (Connection conn = ConnectionFactory.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setBoolean(1, concluida);
	        stmt.setInt(2, tarefaId);
	        int rowsAffected = stmt.executeUpdate();
	        if (rowsAffected == 0) {
	            System.out.println("Nenhuma tarefa foi atualizada. ID não encontrado: " + tarefaId);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}
	
	public List<Tarefa> listPendentes() {
	    List<Tarefa> tarefas = new ArrayList<>();
	    String sql = "SELECT * FROM tarefas WHERE concluida = false ORDER BY data_fim";
	    try (Connection conn = ConnectionFactory.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {
	        while (rs.next()) {
	            Tarefa tarefa = new Tarefa();
	            tarefa.setId(rs.getInt("id"));
	            tarefa.setNome(rs.getString("nome"));
	            tarefa.setDescricao(rs.getString("descricao"));
	            tarefa.setDataInicio(rs.getDate("data_inicio"));
	            tarefa.setDataFim(rs.getDate("data_fim"));
	            tarefa.setProjetoId(rs.getInt("projeto_id"));
	            tarefa.setDonoId(rs.getInt("dono_id"));
	            tarefa.setConcluida(rs.getBoolean("concluida"));
	            tarefas.add(tarefa);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return tarefas;
	}
	
	public void atualizarDonoDaTarefa(int tarefaId, int donoId) throws SQLException {
        String sql = "UPDATE tarefas SET dono_id = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, donoId);
            pstmt.setInt(2, tarefaId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar o dono da tarefa", e);
        }
    }

}
