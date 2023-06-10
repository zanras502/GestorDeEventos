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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author David
 */
public class ReportViewerController implements Initializable {

    @FXML
    private WebView webView;
    @FXML
    private Button btnRegresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        WebEngine webEngine = webView.getEngine();
        String urls = null;
        if (App.idReporte == 1) {
            urls = "file:///C:/reportes/reporte_eventos.html";
        } else if (App.idReporte == 2) {
            urls = "file:///C:/reportes/reporte_usuarios.html";
        } else if (App.idReporte == 3){
            urls = "file:///C:/reportes/reporte_boletos.html";
        }

        webEngine.load(urls);
    }    

    @FXML
    private void regresar(ActionEvent event) {
        try {
            App.setRoot("panelReportes");
        } catch (IOException e) {
            e.printStackTrace();
            // Aquí deberías manejar la excepción, quizá mostrando un mensaje al usuario.
        }
    }
    
}
