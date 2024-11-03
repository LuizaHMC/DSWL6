package com.projeto.dto;

import com.projeto.model.Equipe;
import com.projeto.model.Tarefa;
import com.projeto.utils.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipeDTO implements IDTO<Equipe> {

    @Override
    public void save(Equipe membro) {
        String sql = "INSERT INTO equipe (nome, sobrenome, email, cargo) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, membro.getNome());
            stmt.setString(2, membro.getSobrenome());
            stmt.setString(3, membro.getEmail());
            stmt.setString(4, membro.getCargo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Equipe> list() {
        List<Equipe> membros = new ArrayList<>();
        String sql = "SELECT * FROM equipe";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Equipe membro = new Equipe();
                membro.setId(rs.getInt("id"));
                membro.setNome(rs.getString("nome"));
                membro.setSobrenome(rs.getString("sobrenome"));
                membro.setEmail(rs.getString("email"));
                membro.setCargo(rs.getString("cargo"));
                membros.add(membro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membros;
    }
    
    public List<Equipe> listNames() {
        List<Equipe> membros = new ArrayList<>();
        String sql = "SELECT id, nome FROM equipe";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Equipe membro = new Equipe();
                membro.setId(rs.getInt("id"));
                membro.setNome(rs.getString("nome"));
                membros.add(membro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membros;
    }


    @Override
    public Equipe getById(int id) {
        Equipe membro = null;
        String sql = "SELECT * FROM equipe WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    membro = new Equipe();
                    membro.setId(rs.getInt("id"));
                    membro.setNome(rs.getString("nome"));
                    membro.setSobrenome(rs.getString("sobrenome"));
                    membro.setEmail(rs.getString("email"));
                    membro.setCargo(rs.getString("cargo"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membro;
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM equipe WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Equipe membro) {
        save(membro);
    }


	@Override
	public List<Tarefa> listByProjeto(int projetoId) {
		
		return null;
	}

}
