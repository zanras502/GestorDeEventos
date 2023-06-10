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
import modelo.ReportGenerator;

/**
 * FXML Controller class
 *
 * @author David
 */
public class PanelAdministradorController implements Initializable {

    @FXML
    private Button btnAddEventos;
    @FXML
    private Button btnLista;
    @FXML
    private Button btnReportes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addEventos(ActionEvent event) {
        App.eventoCrud=1;
        try {
            App.setRoot("AddEvent");
        } catch (IOException e) {
            e.printStackTrace();
            // Aquí deberías manejar la excepción, quizá mostrando un mensaje al usuario.
        }
    }

    @FXML
    private void listarEventos(ActionEvent event) {
        try {
            App.setRoot("listaEvento");
        } catch (IOException e) {
            e.printStackTrace();
            // Aquí deberías manejar la excepción, quizá mostrando un mensaje al usuario.
        }
    }

    @FXML
    private void reportes(ActionEvent event) {
        
        
        try {
            App.setRoot("panelReportes");
        } catch (IOException e) {
            e.printStackTrace();
            // Aquí deberías manejar la excepción, quizá mostrando un mensaje al usuario.
        }
    }

}
