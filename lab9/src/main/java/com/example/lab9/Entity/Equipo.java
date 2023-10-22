package com.example.lab9.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "equipo", schema = "partidossdci")
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idequipo", nullable = false)
    private Integer idequipo;

    @Size(max = 45)
    @NotNull
    @Column(name = "nombreEquipo", nullable = false, length = 45)
    private String nombreEquipo;

    @Size(max = 45)
    @NotNull
    @Column(name = "colorEquipo", nullable = false, length = 45)
    private String colorEquipo;

    @Size(max = 45)
    @NotNull
    @Column(name = "mascota", nullable = false, length = 45)
    private String mascota;

}