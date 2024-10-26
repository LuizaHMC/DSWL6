package com.example.shoppingcart.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // URL do banco de dados, usuário e senha são armazenados como constantes
    private static final String URL = "jdbc:mysql://localhost:3306/shoppingcart_db";
    private static final String USER = "root";
    private static final String PASSWORD = "ifsp";

    // O método estático getConnection() aplica o Factory Pattern, pois encapsula o processo de criação de uma conexão
    public static Connection getConnection() throws SQLException {
        try {
            // A classe DriverManager gerencia as conexões JDBC e retorna uma conexão válida
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            // Em caso de falha, o erro é lançado para que o código chamador o trate
            throw new SQLException("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
