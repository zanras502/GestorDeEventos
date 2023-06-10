
package modelo;

public class ControladorCompraBoletos implements TemporizadorListener {
    private Temporizador temporizador;

    public void iniciarProcesoCompra() {
        temporizador = new Temporizador(this);
        temporizador.start();
        // iniciar proceso de compra
    }

    public void finalizarProcesoCompra() {
        temporizador.detener();
        // finalizar proceso de compra
    }

    @Override
    public void tiempoAgotado() {
         //Platform.runLater(() -> {
        // cancelar la compra y actualizar la interfaz de usuario
    //});
    }
}

