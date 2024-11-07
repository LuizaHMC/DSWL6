package com.projeto.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


// Caso Utilize o MySQL:
//
//public class ConnectionFactory {
//
//    // URL do banco de dados, usuário e senha são armazenados como constantes
//    private static final String URL = "jdbc:mysql://localhost:3306/projeto_java_web";
//    private static final String USER = "root";
//    private static final String PASSWORD = "ifsp";
//
//    // O método estático getConnection() aplica o Factory Pattern, pois encapsula o processo de criação de uma conexão
//    public static Connection getConnection() throws SQLException {
//        try {
//            // A classe DriverManager gerencia as conexões JDBC e retorna uma conexão válida
//            return DriverManager.getConnection(URL, USER, PASSWORD);
//        } catch (SQLException e) {
//            // Em caso de falha, o erro é lançado para que o código chamador o trate
//            throw new SQLException("Erro ao conectar ao banco de dados: " + e.getMessage());
//        }
//    }
//}


public class ConnectionFactory {

    // URL de conexão ao banco de dados PostgreSQL
    private static final String URL = "jdbc:postgresql://localhost:5432/projeto_java_web";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgre";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver"); // Carrega o driver
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão estabelecida com sucesso!");
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC não encontrado: " + e.getMessage());
            throw new SQLException("Driver JDBC não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            throw new SQLException("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
