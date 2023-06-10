package modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ClienteManager {

    public static boolean agregarCliente(Cliente cliente) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Comprueba si ya existe un cliente con el mismo correo electrónico
            String queryCheck = "SELECT COUNT(*) FROM clientes WHERE correo = ?";
            PreparedStatement preparedStatementCheck = connection.prepareStatement(queryCheck);
            preparedStatementCheck.setString(1, cliente.getCorreo());
            ResultSet resultSet = preparedStatementCheck.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count > 0) {
                // Si ya existe un cliente con el mismo correo electrónico, retorna false
                return false;
            } else {
                // Si no existe un cliente con el mismo correo electrónico, inserta el nuevo cliente
                String queryInsert = "INSERT INTO clientes (nombre, apellido, correo, telefono) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatementInsert = connection.prepareStatement(queryInsert);
                preparedStatementInsert.setString(1, cliente.getNombre());
                preparedStatementInsert.setString(2, cliente.getApellido());
                preparedStatementInsert.setString(3, cliente.getCorreo());
                preparedStatementInsert.setString(4, cliente.getTelefono());
                preparedStatementInsert.executeUpdate();

                // Retorna true si la inserción es exitosa
                return true;
            }
        } catch (SQLException e) {
            // Aquí debes manejar cualquier error que ocurra durante la inserción.
            e.printStackTrace();

            // Retorna false si ocurre una excepción
            return false;
        }
    }
}
