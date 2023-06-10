
package modelo;

import modelo.DatabaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    public List<Evento> getAllEvents() {
        List<Evento> events = new ArrayList<>();
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM events;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String sinopsis = resultSet.getString("sinopsis");
                String fecha_inicio = resultSet.getString("fecha_inicio");
                String fecha_fin = resultSet.getString("fecha_fin");
                String imagen_publicitaria = resultSet.getString("imagen_publicitaria");
                String responsable = resultSet.getString("responsable");
                String fecha_hora_publicacion = resultSet.getString("fecha_hora_publicacion");
                String fecha_hora_ocultar = resultSet.getString("fecha_hora_ocultar");
                // Obtén los demás campos de la tabla events aquí
                
                Evento event = new Evento(id,nombre,sinopsis,LocalDateTime.parse(fecha_inicio),LocalDateTime.parse(fecha_fin),imagen_publicitaria,responsable,LocalDateTime.parse(fecha_hora_publicacion),LocalDateTime.parse(fecha_hora_ocultar));
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }
}

