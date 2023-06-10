package modelo;

public class AsientoSeleccionado {

    private String asiento;
    private String seccion;
    private String precio;

    public AsientoSeleccionado(String asiento, String seccion, String precio) {
        this.asiento = asiento;
        this.seccion = seccion;
        this.precio = precio;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
