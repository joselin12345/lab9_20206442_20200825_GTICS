package com.example.lab9.Repository;

import com.example.lab9.Entity.Participante;
import com.example.lab9.Entity.Participantespartido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantespartidoRepository extends JpaRepository<Participantespartido, Integer> {

    @Query(value = "select p.idparticipantesPartido, p.partido_idpartido, p.participante_idparticipante, p.horaFecha\n" +
            "            FROM participantespartido p \n" +
            "            inner join participante t on (p.participante_idparticipante = t.idparticipante)\n" +
            "            inner join partido d on (p.partido_idpartido = d.idpartido)\n" +
            "            inner join equipo eA on (d.equipoA = eA.idequipo)\n" +
            "            inner join equipo eB on (d.equipoB = eB.idequipo)\n" +
            "            where eA.idequipo = 5 or eB.idequipo = 5;", nativeQuery = true)
    List<Participantespartido> participantePartido();

}
