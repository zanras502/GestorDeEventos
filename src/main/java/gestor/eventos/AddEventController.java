package gestor.eventos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import modelo.EventManager;
import modelo.Evento;
import modelo.PrecioManager;
import modelo.SeccionManager;

public class AddEventController implements Initializable {

    @FXML
    private TextField nombreField;
    @FXML
    private TextArea sinopsisField;
    @FXML
    private DatePicker fechaInicioField;
    @FXML
    private DatePicker fechaFinField;
    @FXML
    private TextField imagenField;
    @FXML
    private TextField responsableField;
    @FXML
    private DatePicker publicacionField;
    @FXML
    private DatePicker ocultarField;
    @FXML
    private ImageView imagenView;
    @FXML
    private Button seleccionarImagenButton;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnRegresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(App.eventoCrud==1){
            btnEditar.setDisable(true);
            btnEliminar.setDisable(true);
        }
        else if(App.eventoCrud==2){
            cargarEvento();
            btnAgregar.setDisable(true);
            btnEliminar.setDisable(true);
        }
        else if(App.eventoCrud==3){
            cargarEvento();
            btnAgregar.setDisable(true);
            btnEditar.setDisable(true);
        }
    }

    @FXML
    private void addEvent() {
        String nombre = nombreField.getText();
        String sinopsis = sinopsisField.getText();
        LocalDateTime fechaInicio = LocalDateTime.of(fechaInicioField.getValue(), LocalTime.MIDNIGHT);
        LocalDateTime fechaFin = LocalDateTime.of(fechaFinField.getValue(), LocalTime.MIDNIGHT);
        String imagen = imagenField.getText();
        String responsable = responsableField.getText();
        LocalDateTime fechaPublicacion = LocalDateTime.of(publicacionField.getValue(), LocalTime.MIDNIGHT);
        LocalDateTime fechaOcultar = LocalDateTime.of(ocultarField.getValue(), LocalTime.MIDNIGHT);

        Evento newEvent = new Evento(0, nombre, sinopsis, fechaInicio, fechaFin, imagen, responsable, fechaPublicacion, fechaOcultar);
        EventManager.addEvent(newEvent);

        clearFields();
        
        try {
            App.setRoot("precioView");
        } catch (IOException ex) {
            Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void clearFields() {
        nombreField.clear();
        sinopsisField.clear();
        fechaInicioField.setValue(null);
        fechaFinField.setValue(null);
        imagenField.clear();
        responsableField.clear();
        publicacionField.setValue(null);
        ocultarField.setValue(null);
    }
    
    private void cargarEvento(){
        Evento evento = App.selectedEvent;
        nombreField.setText(evento.getNombre());
        sinopsisField.setText(evento.getSinopsis());
        fechaInicioField.setValue(evento.getFechaInicio().toLocalDate());
        fechaFinField.setValue(evento.getFechaFin().toLocalDate());
        imagenField.setText(evento.getImagenPublicitaria());
        responsableField.setText(evento.getResponsable());
        publicacionField.setValue(evento.getFechaHoraPublicacion().toLocalDate());
        ocultarField.setValue(evento.getFechaHoraOcultar().toLocalDate());
        
        File file = new File(evento.getImagenPublicitaria());
    }

    @FXML
    private void seleccionarImagen() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Images (*.png, *.jpg, *.jpeg)", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                Image image = new Image(new FileInputStream(file));
                imagenView.setImage(image);
                imagenField.setText(file.toURI().toString()); // suponiendo que 'imagenField' es el TextField para la URL de la imagen.
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void editEvent(ActionEvent event) {
        int id = EventManager.buscarEvento(nombreField.getText());
        String nombre = nombreField.getText();
        String sinopsis = sinopsisField.getText();
        LocalDateTime fechaInicio = LocalDateTime.of(fechaInicioField.getValue(), LocalTime.MIDNIGHT);
        LocalDateTime fechaFin = LocalDateTime.of(fechaFinField.getValue(), LocalTime.MIDNIGHT);
        String imagen = imagenField.getText();
        String responsable = responsableField.getText();
        LocalDateTime fechaPublicacion = LocalDateTime.of(publicacionField.getValue(), LocalTime.MIDNIGHT);
        LocalDateTime fechaOcultar = LocalDateTime.of(ocultarField.getValue(), LocalTime.MIDNIGHT);

        Evento newEvent = new Evento(id, nombre, sinopsis, fechaInicio, fechaFin, imagen, responsable, fechaPublicacion, fechaOcultar);
        EventManager.modificarEvento(newEvent);
        
        clearFields();
    }

    @FXML
    private void deleteEvent(ActionEvent event) {
        int id = EventManager.buscarEvento(nombreField.getText());
        String nombre = nombreField.getText();
        String sinopsis = sinopsisField.getText();
        LocalDateTime fechaInicio = LocalDateTime.of(fechaInicioField.getValue(), LocalTime.MIDNIGHT);
        LocalDateTime fechaFin = LocalDateTime.of(fechaFinField.getValue(), LocalTime.MIDNIGHT);
        String imagen = imagenField.getText();
        String responsable = responsableField.getText();
        LocalDateTime fechaPublicacion = LocalDateTime.of(publicacionField.getValue(), LocalTime.MIDNIGHT);
        LocalDateTime fechaOcultar = LocalDateTime.of(ocultarField.getValue(), LocalTime.MIDNIGHT);

        Evento newEvent = new Evento(id, nombre, sinopsis, fechaInicio, fechaFin, imagen, responsable, fechaPublicacion, fechaOcultar);
        PrecioManager.eliminaPrecio(id);
        SeccionManager.eliminaSeccion(id);
        EventManager.eliminarEvento(newEvent);
        
        
        clearFields();
    }

    @FXML
    private void regresar(ActionEvent event) {
        try {
            App.setRoot("panelAdministrador");
        } catch (IOException ex) {
            Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

}
