package gestor.eventos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import modelo.Cliente;

public class CorreoElectronicoController implements Initializable {
    @FXML
    private TextField campoPara;
    @FXML
    private TextField campoAsunto;
    @FXML
    private TextArea campoMensaje;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicialización de la vista
        StringBuilder destinatarios = new StringBuilder();
        for (Cliente cliente : App.listaClientes) {
            destinatarios.append(cliente.getNombre()).append(" <").append(cliente.getCorreo()).append(">, ");
        }
        campoPara.setText(destinatarios.toString());
    }

    @FXML
    private void enviarCorreo(ActionEvent event) {
        // Aquí puedes implementar la lógica para enviar el correo electrónico
        String para = campoPara.getText();
        String asunto = campoAsunto.getText();
        String mensaje = campoMensaje.getText();

        // Implementa el envío del correo con los datos proporcionados

        // Puedes mostrar un mensaje de confirmación o realizar otras acciones después de enviar el correo
    }
}
