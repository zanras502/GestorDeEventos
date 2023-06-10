package modelo;

import modelo.Seccion;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Evento {
    private int id;
    private String nombre;
    private String sinopsis;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String imagenPublicitaria;
    private String responsable;
    private LocalDateTime fechaHoraPublicacion;
    private LocalDateTime fechaHoraOcultar;

    public Evento(int id, String nombre, String sinopsis, LocalDateTime fechaInicio, LocalDateTime fechaFin, String imagenPublicitaria, String responsable, LocalDateTime fechaHoraPublicacion, LocalDateTime fechaHoraOcultar) {
        this.id = id;
        this.nombre = nombre;
        this.sinopsis = sinopsis;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.imagenPublicitaria = imagenPublicitaria;
        this.responsable = responsable;
        this.fechaHoraPublicacion = fechaHoraPublicacion;
        this.fechaHoraOcultar = fechaHoraOcultar;
        
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

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getImagenPublicitaria() {
        return imagenPublicitaria;
    }

    public void setImagenPublicitaria(String imagenPublicitaria) {
        this.imagenPublicitaria = imagenPublicitaria;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public LocalDateTime getFechaHoraPublicacion() {
        return fechaHoraPublicacion;
    }

    public void setFechaHoraPublicacion(LocalDateTime fechaHoraPublicacion) {
        this.fechaHoraPublicacion = fechaHoraPublicacion;
    }

    public LocalDateTime getFechaHoraOcultar() {
        return fechaHoraOcultar;
    }

    public void setFechaHoraOcultar(LocalDateTime fechaHoraOcultar) {
        this.fechaHoraOcultar = fechaHoraOcultar;
    }
    
    public List<Seccion> getSecciones(){
        // Este metodo debe completarse para generar la lista de todas las secciones que tiene asociado
        return new ArrayList<Seccion>();
    } 
}
