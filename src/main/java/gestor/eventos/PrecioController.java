package gestor.eventos;

import java.io.IOException;
import modelo.TipoBoleto;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import modelo.EventManager;
import modelo.Evento;
import modelo.Precio;
import modelo.PrecioManager;
import modelo.EventManager;
import modelo.Seccion;
import modelo.SeccionManager;

public class PrecioController implements Initializable {

    @FXML
    private ComboBox<String> eventoBox;
    @FXML
    private ComboBox<String> tipoBoletoBox;
    @FXML
    private TextField precioField;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnContinuar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("VIP+M&G", "VIP", "PLATEA A", "PLATEA B");
        tipoBoletoBox.setItems(items);

        eventoBox.setItems(cargaEventos());
    }

    @FXML
    public void guardarPrecio() {

        String nombreSeccion = tipoBoletoBox.getValue();
        String nombreEvento = eventoBox.getValue();
        int idEvento = (int) EventManager.buscarEvento(nombreEvento);
        int idSeccion = SeccionManager.getMaxId() + 1;

        Seccion seccion = new Seccion(idSeccion, nombreSeccion, idEvento);
        if (SeccionManager.addSeccion(seccion)) {
            System.out.println("Insercion de seccion exitosa");
        } else {
            System.out.println("Insercion de seccion fallida");
        }

        Precio precio = new Precio(1, idEvento, idSeccion, Double.parseDouble(precioField.getText()));
        if (PrecioManager.guardarPrecio(precio)) {
            System.out.println("Insercion de precio exitosa");
        } else {
            System.out.println("Insercion de precio fallida");
        }

    }

    private ObservableList<String> cargaEventos() {
        ObservableList<String> items = FXCollections.observableArrayList();
        List<Evento> listaEventos = EventManager.getEvents();
        for (Evento evento : listaEventos) {
            items.add(evento.getNombre());
        }
        return items;
    }

    @FXML
    private void continuar(ActionEvent event) {
        try {
            App.setRoot("panelAdministrador");
        } catch (IOException ex) {
            Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
