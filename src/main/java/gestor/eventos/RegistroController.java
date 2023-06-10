package gestor.eventos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modelo.UserManager;

public class RegistroController implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private PasswordField txtContrasena;
    @FXML
    private Button btnRegistrarse;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button btnInicio;
    @FXML
    private ImageView imgRgistro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image("/imagenes/registration.png");
        imgRgistro.setImage(image);
    }

    @FXML
    private void register(ActionEvent event) {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String contrasena = txtContrasena.getText();
        String telefono = txtTelefono.getText();
        String email = txtEmail.getText();

        boolean registrationSuccessful = UserManager.registerUser(nombre, apellido, email, telefono, contrasena, "cliente");
        if (registrationSuccessful) {
            // Mostrar mensaje de éxito
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Registro de Usuario");
            alert.setHeaderText(null);
            alert.setContentText("Registro de usuario satisfactorio");
            alert.showAndWait();
            limpiaForm();
        } else {
            // Mostrar mensaje de error
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Registro de Usuario");
            alert.setHeaderText(null);
            alert.setContentText("Registro de usuario no realizado");
            alert.showAndWait();
            limpiaForm();
        }
    }

    @FXML
    private void begin(ActionEvent event) throws IOException {
        App.setRoot("inicio");
    }

    private void limpiaForm() {
        txtNombre.clear();
        txtApellido.clear();
        txtContrasena.clear();
        txtTelefono.clear();
        txtEmail.clear();
    }

}
