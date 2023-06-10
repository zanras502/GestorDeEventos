/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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
public class PanelUsuarioController implements Initializable {

    @FXML
    private Button btnEventos;
    @FXML
    private Button btnSalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void eventos(ActionEvent event) {
        
        try {
                    App.setRoot("listaEvento");
                } catch (IOException e) {
                    e.printStackTrace();
                    // Aquí deberías manejar la excepción, quizá mostrando un mensaje al usuario.
                }
    }

    @FXML
    private void salir(ActionEvent event) {
        System.exit(0);
    }
    
}
