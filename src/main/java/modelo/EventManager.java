package modelo;

import modelo.Boleto;
import modelo.Asiento;
import modelo.Cliente;
import modelo.DatabaseConnection;
import modelo.EstadoAsiento;
import modelo.Seccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventManager {

    List<Evento> eventos = new ArrayList<>();

    public static void addEvent(Evento event) {
        String query = "INSERT INTO eventos (nombre, sinopsis, fecha_inicio, fecha_fin, imagen_publicitaria, responsable, fecha_hora_publicacion, fecha_hora_ocultar) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, event.getNombre());
            stmt.setString(2, event.getSinopsis());
            stmt.setTimestamp(3, Timestamp.valueOf(event.getFechaInicio()));
            stmt.setTimestamp(4, Timestamp.valueOf(event.getFechaFin()));
            stmt.setString(5, event.getImagenPublicitaria());
            stmt.setString(6, event.getResponsable());
            stmt.setTimestamp(7, Timestamp.valueOf(event.getFechaHoraPublicacion()));
            stmt.setTimestamp(8, Timestamp.valueOf(event.getFechaHoraOcultar()));

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static List<Evento> getAllEvents() {
        List<Evento> events = new ArrayList<>();
        String query = "SELECT * FROM eventos";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Evento event = new Evento(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("sinopsis"),
                        rs.getObject("fechaInicio", LocalDateTime.class),
                        rs.getObject("fechaFin", LocalDateTime.class),
                        rs.getString("imagenPublicitaria"),
                        rs.getString("responsable"),
                        rs.getObject("fechaHoraPublicacion", LocalDateTime.class),
                        rs.getObject("fechaHoraOcultar", LocalDateTime.class)
                );

                events.add(event);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return events;
    }

    public static Evento getEvent(int id) {
        String query = "SELECT * FROM eventos WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Evento(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("sinopsis"),
                            rs.getObject("fechaInicio", LocalDateTime.class),
                            rs.getObject("fechaFin", LocalDateTime.class),
                            rs.getString("imagenPublicitaria"),
                            rs.getString("responsable"),
                            rs.getObject("fechaHoraPublicacion", LocalDateTime.class),
                            rs.getObject("fechaHoraOcultar", LocalDateTime.class)
                    );
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static List<Evento> getEvents() {
        List<Evento> eventos = new ArrayList<>();
        String query = "SELECT * FROM eventos";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Evento evento = new Evento(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("sinopsis"),
                        rs.getObject("fecha_inicio", LocalDateTime.class),
                        rs.getObject("fecha_fin", LocalDateTime.class),
                        rs.getString("imagen_publicitaria"),
                        rs.getString("responsable"),
                        rs.getObject("fecha_hora_publicacion", LocalDateTime.class),
                        rs.getObject("fecha_hora_ocultar", LocalDateTime.class)
                );
                eventos.add(evento);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return eventos;
    }

    public static int buscarEvento(String nombreEvento) {
        String query = "SELECT id FROM eventos WHERE nombre = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nombreEvento);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return -1;  // Retorna -1 si no se encuentra el evento
    }

    public static void modificarEvento(Evento evento) {
        String sql = "UPDATE eventos SET nombre = ?, sinopsis = ?, fecha_inicio = ?, fecha_fin = ?, imagen_publicitaria = ?, responsable = ?, fecha_hora_publicacion = ?, fecha_hora_ocultar = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, evento.getNombre());
            statement.setString(2, evento.getSinopsis());
            statement.setTimestamp(3, Timestamp.valueOf(evento.getFechaInicio()));
            statement.setTimestamp(4, Timestamp.valueOf(evento.getFechaFin()));
            statement.setString(5, evento.getImagenPublicitaria());
            statement.setString(6, evento.getResponsable());
            statement.setTimestamp(7, Timestamp.valueOf(evento.getFechaHoraPublicacion()));
            statement.setTimestamp(8, Timestamp.valueOf(evento.getFechaHoraOcultar()));
            statement.setInt(9, evento.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Actualizacion exitosa");
            } else {
                System.out.println("No se encontró el registro para actualizar");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Actualizacion fallida");
        }
    }

    public static void eliminarEvento(Evento evento) {
        String sql = "DELETE FROM eventos WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, evento.getId());

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("El evento ha sido eliminado exitosamente");
            } else {
                System.out.println("No se encontró el evento para eliminar");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Eliminación fallida");
        }
    }

    public void gestionarVisibilidadEvento(int idEvento, boolean visible) {
        String sql = "UPDATE eventos SET visible = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setBoolean(1, visible);
            statement.setInt(2, idEvento);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public Boleto seleccionarBoleto(int idEvento, int idSeccion, String asiento) {
//        // Buscar el evento
//        for (Evento evento : eventos) {
//            if (evento.getId() == idEvento) {
//                // Encontramos el evento, ahora buscamos la sección
//                for (Seccion seccion : evento.getSecciones()) {
//                    if (seccion.getId() == idSeccion) {
//                        // Encontramos la sección, ahora buscamos el asiento
//                        for (Asiento a : seccion.getAsientos()) {
//                            if (a.getFila().equals(asiento) && a.getEstado() == EstadoAsiento.DISPONIBLE) {
//                                // Encontramos el asiento y está disponible, ahora lo seleccionamos
//                                a.setEstado(EstadoAsiento.OCUPADO);
//                                // Creamos y retornamos el boleto
//                                return new Boleto(1, evento.getId(), 1, seccion.getId(), asiento, LocalDateTime.now(), "hola", seccion.getPrecio());
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        // No encontramos el asiento, o no estaba disponible
//        return null;
//    }
    public boolean comprarBoleto(Boleto boleto, Cliente cliente) {
        // Aquí iría tu código para realizar la transacción de la compra.
        // Esto podría implicar crear un nuevo registro en la base de datos para la compra,
        // y actualizar el registro del boleto para marcarlo como vendido.
        // Deberías devolver true si la compra es exitosa, y false en caso contrario.
        return false;
    }
}
