package modelo;

public class PrecioDetalle {
    private int id;
    private String nombreEvento;
    private String nombreSeccion;
    private double precio;

    public PrecioDetalle(int id, String nombreEvento, String nombreSeccion, double precio) {
        this.id = id;
        this.nombreEvento = nombreEvento;
        this.nombreSeccion = nombreSeccion;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public String getNombreSeccion() {
        return nombreSeccion;
    }

    public double getPrecio() {
        return precio;
    }
}
