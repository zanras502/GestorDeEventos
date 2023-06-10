package gestor.eventos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import modelo.Cliente;
import modelo.Evento;
import modelo.ListaAsientoSeleccionado;
import modelo.Usuario;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static Evento selectedEvent; // Esto permite el acceso público a este campo
    public static Usuario usuarioActivo = null; //guarda el usuario activo de la aplicacion
    public static Integer idReporte;
    public static Integer eventoCrud;
    public static Integer tiempoLimite;
    public static ListaAsientoSeleccionado lista = new ListaAsientoSeleccionado();
    public static ArrayList<Cliente> listaClientes = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("inicio"), 800, 600);
        stage.setScene(scene);
//        stage.setMaximized(true);  // Para abrir la ventana maximizada.
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent root = fxmlLoader.load();

        if (root instanceof VBox) {
            VBox vbox = (VBox) root;
            vbox.setAlignment(Pos.CENTER);
        }

        return root;
    }

    public static void main(String[] args) {
        launch();
    }

}
