package com.example.shoppingcart.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.shoppingcart.model.Cliente;
import com.example.shoppingcart.utils.ConnectionFactory;

public class ClienteDTO implements IDTO<Cliente>{
	
	@Override
	public void save(Cliente cliente) {
	    String sql = "INSERT INTO clientes (nome, email) VALUES (?, ?)";

	    try (Connection conn = ConnectionFactory.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, cliente.getNome());
	        stmt.setString(2, cliente.getEmail());
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}



	@Override
	public void update(Cliente cliente) {
	    String sql = "UPDATE clientes SET nome = ?, email = ? WHERE id = ?";

	    try (Connection conn = ConnectionFactory.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, cliente.getNome());
	        stmt.setString(2, cliente.getEmail());
	        stmt.setInt(3, cliente.getId());
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public List<Cliente> list() {
	    List<Cliente> clientes = new ArrayList<>();
	    String sql = "SELECT * FROM clientes";

	    try (Connection conn = ConnectionFactory.getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            Cliente cliente = new Cliente();
	            cliente.setId(rs.getInt("id"));
	            cliente.setNome(rs.getString("nome"));
	            cliente.setEmail(rs.getString("email"));
	            clientes.add(cliente);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return clientes;
	}

	

}
