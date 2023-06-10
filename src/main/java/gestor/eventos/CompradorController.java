package gestor.eventos;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import modelo.Cliente;
import modelo.ClienteManager;
import modelo.Temporizador;
import modelo.TemporizadorListener;

public class CompradorController implements Initializable, TemporizadorListener {

    private int total = App.lista.cantidadAsientos();
    private int contador = 0;

    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidoField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField confEmailField;
    @FXML
    private TextField telefonoField;
    @FXML
    private Button comprarButton;
    @FXML
    private Label temporizadorLabel;

    private Temporizador temporizador;
    @FXML
    private Button btnRegresar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App.tiempoLimite = 10;
        try {
            autoCompletar();
        } catch (IOException ex) {
            Logger.getLogger(CompradorController.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    @FXML
    private void handleCompraButton(ActionEvent event) throws IOException {
        if (contador < total) {

            String nombre = nombreField.getText();
            String apellido = apellidoField.getText();
            String email = emailField.getText();
            String confEmail = confEmailField.getText();
            String telefono = telefonoField.getText();

            Cliente cliente = new Cliente(1, nombre, apellido, email, telefono);
            if (ClienteManager.agregarCliente(cliente)) {
                System.out.println("Cliente ingresado");
                App.listaClientes.add(cliente);
            } else {
                System.out.println("El cliente ya existe");
                App.listaClientes.add(cliente);
            }
            contador++;
            autoCompletar();

        } else {
            temporizador.detener();
            App.setRoot("pagoTarjeta");
        }

    }

    private void autoCompletar() throws IOException {
        if (contador < total) {
            nombreField.setText(App.usuarioActivo.getNombre());
            apellidoField.setText(App.usuarioActivo.getApellido());
            emailField.setText(App.usuarioActivo.getEmail());
            confEmailField.setText(App.usuarioActivo.getEmail());
            telefonoField.setText(App.usuarioActivo.getTelefono());
        } else {
            nombreField.clear();
            apellidoField.clear();
            emailField.clear();
            confEmailField.clear();
            telefonoField.clear();

            temporizador.detener();
            App.setRoot("pagoTarjeta");
        }

    }

    @Override
    public void tiempoAgotado() {
        // Para evitar problemas con el hilo de JavaFX, asegúrate de hacer esto en el hilo de la interfaz gráfica de usuario:
        Platform.runLater(() -> {
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
            App.setRoot("compraBoletos");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
