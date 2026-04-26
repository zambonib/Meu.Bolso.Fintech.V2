package br.com.fiap.meubolso.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String connectionString = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private static final String user = "rm567973";
    private static final String password = "170681";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString,user,password);
    }

}