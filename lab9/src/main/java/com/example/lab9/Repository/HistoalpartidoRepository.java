package com.example.lab9.Repository;

import com.example.lab9.Entity.Historialpartido;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoalpartidoRepository extends JpaRepository<Historialpartido,Integer> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "insert into historialpartidos (partido_idpartido,horaFecha) values (?1,current_timestamp());")
    void guardarEnHistorial(int idPartido);
}
