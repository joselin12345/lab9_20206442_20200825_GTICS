package com.example.lab9.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "partido", schema = "partidossdci")
public class Partido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpartido", nullable = false)
    private Integer idpartido;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "equipoA", nullable = false)
    private Equipo equipoA;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "equipoB", nullable = false)
    private Equipo equipoB;

    @NotNull
    @Column(name = "scoreA", nullable = false)
    private Integer scoreA;

    @NotNull
    @Column(name = "scoreB", nullable = false)
    private Integer scoreB;

}