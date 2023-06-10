package modelo;

public enum TipoBoleto {
    VIP_MG("VIP+M&G"),
    VIP("VIP"),
    PLATEA_A("Platea A"),
    PLATEA_B("Platea B");

    private final String nombre;

    TipoBoleto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
