package com.trains.trains.DTO;

import java.util.List;

public class RutaDTO {
    private Long id;
    private String nombreRuta;
    private String descripcion;
    private List<EstacionDTO> estaciones;


    public RutaDTO(Long id, String nombreRuta, String descripcion, List<EstacionDTO> estaciones) {
        this.id = id;
        this.nombreRuta = nombreRuta;
        this.descripcion = descripcion;
        this.estaciones = estaciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreRuta() {
        return nombreRuta;
    }

    public void setNombreRuta(String nombreRuta) {
        this.nombreRuta = nombreRuta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<EstacionDTO> getEstaciones() {
        return estaciones;
    }

    public void setEstaciones(List<EstacionDTO> estaciones) {
        this.estaciones = estaciones;
    }
}
