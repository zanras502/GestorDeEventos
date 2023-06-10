package modelo;

import gestor.eventos.App;

public class Temporizador extends Thread {

    private TemporizadorListener listener;
    private volatile boolean corriendo = true;
    private volatile int tiempoRestante; 

    public Temporizador(TemporizadorListener listener) {
        this.listener = listener;
        this.tiempoRestante = App.tiempoLimite * 60;  // convertir minutos a segundos
    }

    @Override
    public void run() {
        while (corriendo && tiempoRestante > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            tiempoRestante--;
        }

        if (tiempoRestante <= 0) {
            listener.tiempoAgotado();
        }
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void detener() {
        corriendo = false;
    }
}

