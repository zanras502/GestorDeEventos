
package modelo;
public class Precio {
    private int id;
    private int eventoId;
    private int seccionId;
    private double precio;

    // Constructor
    public Precio(int id, int eventoId, int seccionId, double precio) {
        this.id = id;
        this.eventoId = eventoId;
        this.seccionId = seccionId;
        this.precio = precio;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventoId() {
        return eventoId;
    }

    public void setEventoId(int eventoId) {
        this.eventoId = eventoId;
    }

    public int getSeccionId() {
        return seccionId;
    }

    public void setSeccionId(int seccionId) {
        this.seccionId = seccionId;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}

