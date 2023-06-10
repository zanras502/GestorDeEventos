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
public class PanelReportesController implements Initializable {

    @FXML
    private Button btnEventos;
    @FXML
    private Button btnUsuarios;
    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnBoletos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void RepEventos(ActionEvent event) {
        ReportGenerator.generarReporteEventos();
        App.idReporte=1;
        try {
            App.setRoot("reportViewer");
        } catch (IOException e) {
            e.printStackTrace();
            // Aquí deberías manejar la excepción, quizá mostrando un mensaje al usuario.
        }
    }

    @FXML
    private void RepUsuarios(ActionEvent event) {
        ReportGenerator.generarReporteUsuarios();
        App.idReporte=2;
        
        try {
            App.setRoot("reportViewer");
        } catch (IOException e) {
            e.printStackTrace();
            // Aquí deberías manejar la excepción, quizá mostrando un mensaje al usuario.
        }
    }

    @FXML
    private void regresar(ActionEvent event) {
        try {
            App.setRoot("PanelAdministrador");
        } catch (IOException e) {
            e.printStackTrace();
            // Aquí deberías manejar la excepción, quizá mostrando un mensaje al usuario.
        }
    }

    @FXML
    private void RepBoletos(ActionEvent event) {
        ReportGenerator.generarReporteBoletos();
        App.idReporte=3;
        
        try {
            App.setRoot("reportViewer");
        } catch (IOException e) {
            e.printStackTrace();
            // Aquí deberías manejar la excepción, quizá mostrando un mensaje al usuario.
        }
    }
    
}
