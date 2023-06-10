
package gestor.eventos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author David
 */
public class InicioController implements Initializable {

    @FXML
    private Button btnVerEventos;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnResitrarse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    

    @FXML
    private void cargaLogin()throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void cargaRegistro()throws IOException {
        App.setRoot("registro");
    }

    @FXML
    private void cargaEventos()throws IOException {
        App.setRoot("listaEvento");
    }

    
}
