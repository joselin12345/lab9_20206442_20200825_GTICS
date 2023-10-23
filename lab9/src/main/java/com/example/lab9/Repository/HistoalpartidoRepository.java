package com.example.lab9.Repository;

import com.example.lab9.Entity.Historialpartido;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoalpartidoRepository extends JpaRepository<Historialpartido,Integer> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "insert into historialpartidos (partido_idpartido,horaFecha) values (?1,current_timestamp());")
    void guardarEnHistorial(int idPartido);


    @Query(value = "select h.idhistorialPartidos, h.partido_idpartido, h.horaFecha\n" +
            "                        FROM historialpartidos h \n" +
            "                        inner join partido p on (h.partido_idpartido = p.idpartido)\n" +
            "                        inner join equipo eA on (p.equipoA = eA.idequipo)\n" +
            "                        inner join equipo eB on (p.equipoB = eB.idequipo)\n" +
            "                        where eA.idequipo =?1 or eB.idequipo = ?1", nativeQuery = true)
    List<Historialpartido> Historial(int equipo);


}
