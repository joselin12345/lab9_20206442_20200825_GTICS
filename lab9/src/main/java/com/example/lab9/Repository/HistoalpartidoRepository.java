package com.example.lab9.Repository;

import com.example.lab9.Entity.Historialpartido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoalpartidoRepository extends JpaRepository<Historialpartido,Integer> {
}
