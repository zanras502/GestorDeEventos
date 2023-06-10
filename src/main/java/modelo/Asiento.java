
package modelo;

public class Asiento {
    private int id;
    private String fila;
    private int numero;
    private int seccionId;
    private EstadoAsiento estado; // Enum que represente el estado del asiento (Disponible, Ocupado)
    
    // Constructor, getters y setters

    public Asiento(int id, String fila, int numero, int seccionId, EstadoAsiento estado) {
        this.id = id;
        this.fila = fila;
        this.numero = numero;
        this.seccionId = seccionId;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getSeccionId() {
        return seccionId;
    }

    public void setSeccionId(int seccionId) {
        this.seccionId = seccionId;
    }

    public EstadoAsiento getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsiento estado) {
        this.estado = estado;
    }
    
}
