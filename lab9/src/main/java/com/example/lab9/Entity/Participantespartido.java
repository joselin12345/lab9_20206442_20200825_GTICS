package com.example.lab9.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "participantespartido", schema = "partidossdci")
public class Participantespartido {
    @Id
    @Column(name = "idparticipantesPartido", nullable = false)
    private Integer idparticipantesPartido;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "partido_idpartido", nullable = false)
    private Partido partidoIdpartido;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "participante_idparticipante", nullable = false)
    private Participante participanteIdparticipante;

    @NotNull
    @Column(name = "horaFecha", nullable = false)
    private Instant horaFecha;

}