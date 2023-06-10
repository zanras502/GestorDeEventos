package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String DATABASE_URL = "jdbc:postgresql://srvprogra.postgres.database.azure.com:5432/eventos";
    private static final String USER = "hmiranda";
    private static final String PASSWORD = "Usuario.1";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            System.out.println("Conexion con exito");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Conexion sin exito");
        }

        return connection;
    }
}

