/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDateTime;

/**
 *
 * @author David
 */
public class BoletoDetalle {

    private int id;
    private String nombreEvento;
    private String nombreUsuario;
    private String seccion;
    private String asiento;
    private LocalDateTime fechaHoraEvento;

    public BoletoDetalle(int id, String nombreEvento, String nombreUsuario, String seccion, String asiento, LocalDateTime fechaHoraEvento) {
        this.id = id;
        this.nombreEvento = nombreEvento;
        this.nombreUsuario = nombreUsuario;
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

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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
