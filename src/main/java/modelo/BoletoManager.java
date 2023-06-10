package modelo;

import modelo.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BoletoManager {

    public static boolean agregarBoleto(Boleto boleto) {
        String sql = "INSERT INTO boletos (evento_id, usuario_id, seccion, asiento, fecha) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, boleto.getEventoId());
            statement.setInt(2, boleto.getUsuarioId());
            statement.setString(3, boleto.getSeccion());
            statement.setString(4, boleto.getAsiento());
            statement.setTimestamp(5, java.sql.Timestamp.valueOf(boleto.getFechaHoraEvento()));

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public static ArrayList<BoletoDetalle> listaBoletos() {
    ArrayList<BoletoDetalle> boletos = new ArrayList<>();

    String sql = "SELECT b.id AS id, e.nombre AS nombre_evento, u.email AS nombre_usuario, b.seccion AS seccion, b.asiento AS asiento, b.fecha AS fecha " +
                 "FROM boletos b " +
                 "JOIN eventos e ON b.evento_id = e.id " +
                 "JOIN usuarios u ON b.usuario_id = u.id";

    try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombreEvento = resultSet.getString("nombre_evento");
                String nombreUsuario = resultSet.getString("nombre_usuario");
                String seccion = resultSet.getString("seccion");
                String asiento = resultSet.getString("asiento");
                LocalDateTime fechaHoraEvento = resultSet.getTimestamp("fecha").toLocalDateTime();

                BoletoDetalle boleto = new BoletoDetalle(id, nombreEvento, nombreUsuario, seccion, asiento, fechaHoraEvento);
                boletos.add(boleto);
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return boletos;
}


    public static boolean verificarDisponibilidadAsiento(int eventoId, int seccionId, String asiento) {
        String sql = "SELECT * FROM boletos WHERE eventoId = ? AND seccionId = ? AND asiento = ?";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, eventoId);
            statement.setInt(2, seccionId);
            statement.setString(3, asiento);

            try (ResultSet resultSet = statement.executeQuery()) {
                return !resultSet.next();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public static boolean reservarAsiento(int eventoId, int usuarioId, int seccionId, String asiento, LocalDateTime fechaHoraEvento, String direccionVenue, BigDecimal precio) {
        String sql = "INSERT INTO boletos (eventoId, usuarioId, seccionId, asiento, fechaHoraEvento, direccionVenue, precio) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, eventoId);
            statement.setInt(2, usuarioId);
            statement.setInt(3, seccionId);
            statement.setString(4, asiento);
            statement.setTimestamp(5, java.sql.Timestamp.valueOf(fechaHoraEvento));
            statement.setString(6, direccionVenue);
            statement.setBigDecimal(7, precio);

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public static Boleto obtenerBoleto(int id) {
        String sql = "SELECT * FROM boletos WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Boleto(
                            resultSet.getInt("id"),
                            resultSet.getInt("eventoId"),
                            resultSet.getInt("usuarioId"),
                            resultSet.getString("seccion"),
                            resultSet.getString("asiento"),
                            resultSet.getTimestamp("fechaHoraEvento").toLocalDateTime()
                    );
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
