package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrecioManager {

    public static boolean guardarPrecio(Precio precio) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO precios (evento_id, seccion_id, precio) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, precio.getEventoId());
            preparedStatement.setInt(2, precio.getSeccionId());
            preparedStatement.setDouble(3, precio.getPrecio());
            preparedStatement.executeUpdate();

            return true; // Devolver true si la inserción es exitosa
        } catch (SQLException e) {
            // Aquí debes manejar cualquier error que ocurra durante la inserción.
            e.printStackTrace();

            return false; // Devolver false si ocurre una excepción
        }
    }

    public static void eliminaPrecio(int id_evento) {
        String sql = "DELETE FROM precios WHERE evento_id = ?";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id_evento);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Los precios asociados al evento han sido eliminados exitosamente");
            } else {
                System.out.println("No se encontraron precios para eliminar asociados a este evento");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Eliminación fallida");
        }
    }

    public static List<PrecioDetalle> getPrecioDetalles() {
        List<PrecioDetalle> precioDetalles = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT p.id AS id, e.nombre AS nombre_evento, s.nombre AS nombre_seccion, p.precio AS precio "
                    + "FROM precios p JOIN eventos e ON p.evento_id = e.id JOIN secciones s ON p.seccion_id = s.id";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombreEvento = resultSet.getString("nombre_evento");
                String nombreSeccion = resultSet.getString("nombre_seccion");
                double precio = resultSet.getDouble("precio");
                PrecioDetalle precioDetalle = new PrecioDetalle(id, nombreEvento, nombreSeccion, precio);
                precioDetalles.add(precioDetalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return precioDetalles;
    }
    
    public static double precioSeccion(String nombreEvento,String nombreSeccion){
        double precio = 0;
        List<PrecioDetalle> precioDetalle = getPrecioDetalles();
        for(PrecioDetalle detalle: precioDetalle ){
            if(detalle.getNombreEvento().equals(nombreEvento) && detalle.getNombreSeccion().equals(nombreSeccion)){
                precio = detalle.getPrecio();
                return precio;
            }
        }
        
        return precio;
    }

}
