package modelo;

public class Seccion {
    private int id;
    private String nombre;
    private int eventoId;
    
    
    // Constructor, getters y setters

    public Seccion(int id, String nombre, int eventoId) {
        this.id = id;
        this.nombre = nombre;
        this.eventoId = eventoId;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEventoId() {
        return eventoId;
    }

    public void setEventoId(int eventoId) {
        this.eventoId = eventoId;
    }

    @Override
    public String toString() {
        return "Seccion{" + "id=" + id + ", nombre=" + nombre + ", eventoId=" + 
                eventoId + '}';
    }
    
    
}
