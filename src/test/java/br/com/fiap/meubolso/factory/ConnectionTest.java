package br.com.fiap.meubolso.factory;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionTest {
    public static void main(String[] args) {
        try {
            System.out.println("Tentando conectar ao banco de dados Oracle...");

            // Chama o método da sua factory
            Connection conn = ConnectionFactory.getConnection();

            if (conn != null) {
                System.out.println("✅ Conexão estabelecida com sucesso!");
                // Sempre feche a conexão após o teste
                conn.close();
                System.out.println("Conexão fechada.");
            }

        } catch (SQLException e) {
            System.err.println("❌ Falha na conexão!");
            System.err.println("Mensagem de erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}