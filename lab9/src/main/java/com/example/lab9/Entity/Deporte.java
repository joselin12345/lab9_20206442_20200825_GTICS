package com.example.lab9.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "deporte", schema = "partidossdci")
public class Deporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddeporte", nullable = false)
    private Integer iddeporte;

    @Size(max = 45)
    @NotNull
    @Column(name = "nombreDeporte", nullable = false, length = 45)
    private String nombreDeporte;

    @NotNull
    @Column(name = "pesoDeporte", nullable = false)
    private Integer pesoDeporte;

}