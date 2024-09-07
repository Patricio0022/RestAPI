package org.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class mySqlConecct {

    public void connect() {
        Connection conn = null;

        try {
            // Configurando a conexão ao banco de dados mysql
            String url = "jdbc:mysql://localhost:3306/jpacourse?useTimezone=true&serveTimezone=UTC";
            String username = "root";
            String password = "root";

            conn = DriverManager.getConnection(url, username, password);

            System.out.println("Conexão estabelecida com sucesso");
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao tentar estabelecer a conexão: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Conexão fechada com sucesso");
                } catch (SQLException e) {
                    System.out.println("Ocorreu um erro ao fechar a conexão: " + e.getMessage());
                }
            }
        }
    }
}
