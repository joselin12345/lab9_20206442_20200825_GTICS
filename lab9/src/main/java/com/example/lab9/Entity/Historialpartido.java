package com.example.lab9.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "historialpartidos", schema = "partidossdci")
public class Historialpartido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhistorialPartidos", nullable = false)
    private Integer idhistorialPartidos;

    @NotNull
    @Column(name = "horaFecha", nullable = false)
    private Instant horaFecha;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "partido_idpartido", nullable = false)
    private Partido partido_idpartido;
}