package modelo;

import gestor.eventos.App;
import modelo.Boleto;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class ReportGenerator {

    public static void generarReporteEventos() {
        List<Evento> eventos = EventManager.getEvents();

        StringBuilder html = new StringBuilder();

        html.append("<html>\n");
        html.append("<head><title>Reporte de Eventos</title></head>\n");
        html.append("<body>\n");
        html.append("<h1>Reporte de Eventos</h1>\n");
        html.append("<table>\n");
        html.append("<tr><th>Nombre</th><th>Sinopsis</th><th>Fecha Inicio</th><th>Fecha Fin</th></tr>\n");

        for (Evento evento : eventos) {
            html.append("<tr>");
            html.append("<td>").append(evento.getNombre()).append("</td>");
            html.append("<td>").append(evento.getSinopsis()).append("</td>");
            html.append("<td>").append(evento.getFechaInicio().toLocalDate()).append("</td>");
            html.append("<td>").append(evento.getFechaFin().toLocalDate()).append("</td>");
            html.append("</tr>\n");
        }

        html.append("</table>\n");
        html.append("</body>\n");
        html.append("</html>\n");

        // Ensure directory exists
        File directory = new File("c:\\reportes");
        if (!directory.exists()) {
            directory.mkdir();
        }

        try (PrintWriter out = new PrintWriter(new File(directory, "reporte_eventos.html"))) {
            out.println(html.toString());
            App.idReporte = 1;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Boleto> getBoletos(int eventoId) {
        // Implementar la lógica para obtener los boletos de la base de datos filtrados por eventoId.
        List<Boleto> listaBoletos = new ArrayList<>();
        return listaBoletos;
    }

    public void generarReporteBoletos(int eventoId) {
        List<Boleto> boletos = getBoletos(eventoId);

        // A continuación, formatea los datos en HTML y escribe el archivo.
        try (PrintWriter out = new PrintWriter("reporte_boletos.html")) {
            out.println("<html><body>");
            out.println("<h1>Reporte de Boletos</h1>");

            // Aquí podrías tener un ciclo for o un stream para iterar sobre tus boletos y generar las líneas de la tabla.
            for (Boleto boleto : boletos) {
                out.println("<p>" + boleto.toString() + "</p>"); // Asegúrate de implementar el método toString() en tu clase Boleto.
            }

            out.println("</body></html>");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Boleto> getBoletosEvento(int eventoId) {
        // Implementar la lógica para obtener los boletos de la base de datos filtrados por eventoId.
        List<Boleto> listaBoleto = new ArrayList<>();
        return listaBoleto;
    }

    public Usuario getUsuarioBoleto(int boletoId) {
        // Implementar la lógica para obtener el usuario que compró un boleto específico.
        Usuario usuario = new Usuario();

        return usuario;
    }

    public void generarReporteBoletosEvento(int eventoId) {
        List<Boleto> boletos = getBoletosEvento(eventoId);

        try (PrintWriter out = new PrintWriter("reporte_boletos_evento.html")) {
            out.println("<html><body>");
            out.println("<h1>Reporte de Boletos por Evento</h1>");

            for (Boleto boleto : boletos) {
                Usuario usuario = getUsuarioBoleto(boleto.getId());
                out.println("<p>" + boleto.toString());
                if (usuario != null) {
                    out.println(" Comprado por: " + usuario.toString()); // Asegúrate de implementar el método toString() en tu clase Usuario.
                }
                out.println("</p>");
            }

            out.println("</body></html>");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void generarReporteUsuarios() {
        List<Usuario> usuarios = UserManager.todosLosUsuarios();

        StringBuilder html = new StringBuilder();

        html.append("<html>\n");
        html.append("<head><title>Reporte de Usuarios</title></head>\n");
        html.append("<body>\n");
        html.append("<h1>Reporte de Usuarios</h1>\n");
        html.append("<table>\n");
        html.append("<tr><th>ID</th><th>Nombre</th><th>Apellido</th><th>Email</th><th>Teléfono</th><th>Rol</th></tr>\n");

        for (Usuario usuario : usuarios) {
            html.append("<tr>");
            html.append("<td>").append(usuario.getId()).append("</td>");
            html.append("<td>").append(usuario.getNombre()).append("</td>");
            html.append("<td>").append(usuario.getApellido()).append("</td>");
            html.append("<td>").append(usuario.getEmail()).append("</td>");
            html.append("<td>").append(usuario.getTelefono()).append("</td>");
            html.append("<td>").append(usuario.getRol()).append("</td>");
            html.append("</tr>\n");
        }

        html.append("</table>\n");
        html.append("</body>\n");
        html.append("</html>\n");

        // Ensure directory exists
        File directory = new File("c:\\reportes");
        if (!directory.exists()) {
            directory.mkdir();
        }

        try (PrintWriter out = new PrintWriter(new File(directory, "reporte_usuarios.html"))) {
            out.println(html.toString());
            App.idReporte = 2;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void generarReporteBoletos() {
        ArrayList<BoletoDetalle> boletos = BoletoManager.listaBoletos();

        StringBuilder html = new StringBuilder();

        html.append("<html>\n");
        html.append("<head><title>Reporte de Boletos</title></head>\n");
        html.append("<body>\n");
        html.append("<h1>Reporte de Boletos</h1>\n");
        html.append("<table>\n");
        html.append("<tr><th>ID</th><th>Evento </th><th>Usuario </th><th>Sección</th><th>Asiento</th><th>Fecha</th></tr>\n");

        for (BoletoDetalle boleto : boletos) {
            html.append("<tr>");
            html.append("<td>").append(boleto.getId()).append("</td>");
            html.append("<td>").append(boleto.getNombreEvento()).append("</td>");
            html.append("<td>").append(boleto.getNombreUsuario()).append("</td>");
            html.append("<td>").append(boleto.getSeccion()).append("</td>");
            html.append("<td>").append(boleto.getAsiento()).append("</td>");
            html.append("<td>").append(boleto.getFechaHoraEvento().toLocalDate()).append("</td>");
            html.append("</tr>\n");
        }

        html.append("</table>\n");
        html.append("</body>\n");
        html.append("</html>\n");

        // Ensure directory exists
        File directory = new File("c:\\reportes");
        if (!directory.exists()) {
            directory.mkdir();
        }

        try (PrintWriter out = new PrintWriter(new File(directory, "reporte_boletos.html"))) {
            out.println(html.toString());
            App.idReporte = 3;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Métodos adicionales para generar los demás reportes...
}
