
package gestor.eventos;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import modelo.AsientoSeleccionado;
import modelo.Boleto;
import modelo.BoletoManager;
import modelo.EventManager;
import modelo.Temporizador;
import modelo.TemporizadorListener;
import modelo.UserManager;

/**
 * FXML Controller class
 *
 * @author David
 */
public class PagoTarjetaController implements Initializable, TemporizadorListener {

    @FXML
    private TextField nombreTarjetahabienteField;
    @FXML
    private TextField numeroTarjetaField;
    @FXML
    private TextField fechaVencimientoField;
    @FXML
    private TextField codigoVerificacionField;
    @FXML
    private TextField direccionField;
    @FXML
    private TextField codigoPostalField;
    @FXML
    private Label temporizadorLabel;
    @FXML
    private Button btnPagar;
    @FXML
    private TextArea resumenCompraTextArea;
    
    private Temporizador temporizador;
    @FXML
    private Button btnRegresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        detalle();
        
        temporizador = new Temporizador(this);
        temporizador.start();

        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(1),
                        event -> temporizadorLabel.setText("Tiempo restante: " + temporizador.getTiempoRestante())
                )
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void handleAceptarButton(ActionEvent event) throws IOException {
        String nombreTarjetahabiente = nombreTarjetahabienteField.getText();
        String numeroTarjeta = numeroTarjetaField.getText();
        String fechaVencimiento = fechaVencimientoField.getText();
        String codigoVerificacion = codigoVerificacionField.getText();
        String direccion = direccionField.getText();
        String codigoPostal = codigoPostalField.getText();
        
        

        
    }

    private void detalle() {
        StringBuilder detalleCompra = new StringBuilder();
        double subtotal = 0;
        double porcentajeExtra = 0;
        double total = 0;

        detalleCompra.append("Evento: " + App.selectedEvent.getNombre() + "\n\n");
        for (AsientoSeleccionado asiento : App.lista.lista()) {
            detalleCompra.append("Asiento: ").append(asiento.getAsiento())
                    .append("\nSección: ").append(asiento.getSeccion())
                    .append("\nPrecio: ").append(asiento.getPrecio())
                    .append("\n\n");
            subtotal = subtotal + Double.parseDouble(asiento.getPrecio());
        }
        porcentajeExtra = subtotal * 0.05;
        total = subtotal + porcentajeExtra;

        detalleCompra.append("SubTotal: " + String.valueOf(subtotal) + "\n")
                .append("Sevicio compra en Linea: " + String.valueOf(porcentajeExtra) + "\n")
                .append("Total a Pagar: " + String.valueOf(total) + "\n");

        // Mostrar el detalle en el TextArea
        resumenCompraTextArea.setText(detalleCompra.toString());

    }

    private boolean camposCompletos() {
        return !nombreTarjetahabienteField.getText().isEmpty()
                && !numeroTarjetaField.getText().isEmpty()
                && !fechaVencimientoField.getText().isEmpty()
                && !codigoVerificacionField.getText().isEmpty()
                && !direccionField.getText().isEmpty()
                && !codigoPostalField.getText().isEmpty();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarAlertaInfo(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void pagar(ActionEvent event) {
        int eventoId = EventManager.buscarEvento(App.selectedEvent.getNombre());
        int usuarioId = UserManager.idUsuario(App.usuarioActivo.getEmail());
        LocalDateTime fechaHoy = LocalDateTime.now();

        // Validar que todos los campos estén completos
        if (camposCompletos()) {
            for (AsientoSeleccionado asientos : App.lista.lista()) {
                Boleto boleto = new Boleto(1, eventoId, usuarioId, asientos.getSeccion(), asientos.getAsiento(), fechaHoy);
                if (BoletoManager.agregarBoleto(boleto)) {
                    System.out.println("Boleto agregado exitosamente");
                } else {
                    System.out.println("Boleto NO agregado");
                }
            }
            mostrarAlertaInfo("Boletos Comprados", "Se realizo la compra de todos sus boletos\n y se enviaron los correos a los compradores");
            temporizador.detener();
            try {
                App.setRoot("panelUsuario");
            } catch (IOException ex) {
                Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            // Mostrar mensaje de alerta
            mostrarAlerta("Falta información", "Por favor, completa todos los campos antes de realizar el pago.");
        }
    }
    
    @Override
    public void tiempoAgotado() {
        // Para evitar problemas con el hilo de JavaFX, asegúrate de hacer esto en el hilo de la interfaz gráfica de usuario:
        Platform.runLater(() -> {
            mostrarAlerta("Tiempo Agotado", "Su tiempo se agoto, se le enviara al paso anterior.");
            try {
                App.setRoot("compraBoletos");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void regresar(ActionEvent event) {
        temporizador.detener();
        try {
                App.setRoot("comprador");
            } catch (IOException ex) {
                Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

}
