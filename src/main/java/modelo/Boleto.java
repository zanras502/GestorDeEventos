package modelo;

import java.time.LocalDateTime;

public class Boleto {

    private int id;
    private int eventoId;
    private int usuarioId;
    private String seccion;
    private String asiento;
    private LocalDateTime fechaHoraEvento;

    public Boleto(int id, int eventoId, int usuarioId, String seccion, String asiento, LocalDateTime fechaHoraEvento) {
        this.id = id;
        this.eventoId = eventoId;
        this.usuarioId = usuarioId;
        this.seccion = seccion;
        this.asiento = asiento;
        this.fechaHoraEvento = fechaHoraEvento;
    }

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

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public LocalDateTime getFechaHoraEvento() {
        return fechaHoraEvento;
    }

    public void setFechaHoraEvento(LocalDateTime fechaHoraEvento) {
        this.fechaHoraEvento = fechaHoraEvento;
    }

}
