package com.example.lab9.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "historialpartidos", schema = "partidossdci")
public class Historialpartido {
    @Id
    @GeneratedValue
    @Column(name = "idhistorialPartidos", nullable = false)
    private Integer idhistorialPartidos;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "partido_idpartido", nullable = false)
    private Partido partidoIdpartido;


    @NotNull
    @Column(name = "horaFecha", nullable = false)
    private Date horaFecha;

}