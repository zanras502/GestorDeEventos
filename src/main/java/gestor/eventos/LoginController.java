package gestor.eventos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modelo.UserManager;

public class LoginController implements Initializable {

    @FXML
    private TextField txtCorreo;
    @FXML
    private PasswordField txtConrasena;
    @FXML
    private Button btnIngresar;
    @FXML
    private ImageView imgLogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image("/imagenes/login.png");
        imgLogin.setImage(image);
    }

    @FXML
    private void click() throws IOException {
        String correo = txtCorreo.getText();
        String contrasena = txtConrasena.getText();

        String userRole = UserManager.loginUser(correo, contrasena);
        if (userRole != null) {
            if (userRole.equals("cliente")) {
                
                //aqui se llama a toda la parte de compra de boletos
                try {
                    App.setRoot("panelUsuario");
                } catch (IOException e) {
                    e.printStackTrace();
                    // Aquí deberías manejar la excepción, quizá mostrando un mensaje al usuario.
                }
            } else if (userRole.equals("Administrador")) {
                
                //aqui se llama a toda la parte de administracion
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información de Inicio de Sesión");
                alert.setHeaderText(null);
                alert.setContentText("Eres un administrador");
                alert.showAndWait();
                try {
                    App.setRoot("panelAdministrador");
                } catch (IOException e) {
                    e.printStackTrace();
                    // Aquí deberías manejar la excepción, quizá mostrando un mensaje al usuario.
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Inicio de Sesión");
            alert.setHeaderText(null);
            alert.setContentText("Correo electrónico o contraseña incorrectos. Inténtalo de nuevo.");
            alert.showAndWait();
        }
    }

}
