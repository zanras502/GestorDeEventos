package modelo;

import gestor.eventos.App;
import modelo.DatabaseConnection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class UserManager {

    public static boolean registerUser(String nombre, String apellido, String email, String telefono, String contrasena, String rol) {
        String hashedPassword = hashPassword(contrasena);

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO usuarios (nombre, apellido, email, telefono, contraseña, rol) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellido);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, telefono);
            preparedStatement.setString(5, hashedPassword);
            preparedStatement.setString(6, rol);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            // manejar excepción
            e.printStackTrace();
        }

        return false;
    }

    public static int idUsuario(String email) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT id FROM usuarios WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            // manejar excepción
            e.printStackTrace();
        }

        // Retornar -1 si no se encontró el ID o hubo un error
        return -1;
    }

    public static String loginUser(String email, String password) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM usuarios WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("contraseña");
                // Comprobamos si la contraseña es correcta
                if (storedPassword.equals(hashPassword(password))) {
                    // Si es correcta, retornamos el rol del usuario
                    int id = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    String apellido = resultSet.getString("apellido");
                    String mail = resultSet.getString("email");
                    String telefono = resultSet.getString("telefono");
                    String rol = resultSet.getString("rol");

                    Usuario usuario = new Usuario(id, nombre, apellido, mail, telefono, storedPassword, rol);
                    App.usuarioActivo = usuario;
                    return resultSet.getString("rol");
                }
            }
        } catch (SQLException e) {
            // manejar excepción
            e.printStackTrace();
        }

        // Retornamos null si las credenciales no son correctas
        return null;
    }

    private static String hashPassword(String password) {
        // Aquí se debe implementar la lógica de hashing de la contraseña.
        // En un caso real, nunca se debe utilizar un método de hashing tan simple.
        // Se debe usar un algoritmo seguro de hashing de contraseñas, como bcrypt.
        return String.valueOf(password.hashCode());
    }

    public static int countUsers() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT COUNT(*) AS count FROM usuarios";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("count");
            }
        } catch (SQLException e) {
            // manejar excepción
            e.printStackTrace();
        }

        // Devolver -1 para indicar que ocurrió un error
        return -1;
    }

    public static List<Usuario> todosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM usuarios";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String email = resultSet.getString("email");
                String telefono = resultSet.getString("telefono");
                String contrasena = resultSet.getString("contraseña");
                String rol = resultSet.getString("rol");

                Usuario usuario = new Usuario(id, nombre, apellido, email, telefono, contrasena, rol);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            // manejar excepción
            e.printStackTrace();
        }

        return usuarios;
    }

}
