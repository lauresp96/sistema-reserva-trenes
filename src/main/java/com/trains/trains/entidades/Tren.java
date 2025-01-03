package com.trains.trains.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "trenes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Tren {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String nombreTren;
    private Integer capacidad;
    private String tipoTren;
    private String modelo;

    @JsonBackReference
    @OneToMany(mappedBy = "tren", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Viaje> viajes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreTren() {
        return nombreTren;
    }

    public void setNombreTren(String nombreTren) {
        this.nombreTren = nombreTren;
    }

    public String getTipoTren() {
        return tipoTren;
    }

    public void setTipoTren(String tipoTren) {
        this.tipoTren = tipoTren;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public List<Viaje> getViajes() {
        return viajes;
    }

    public void setViajes(List<Viaje> viajes) {
        this.viajes = viajes;
    }
}
