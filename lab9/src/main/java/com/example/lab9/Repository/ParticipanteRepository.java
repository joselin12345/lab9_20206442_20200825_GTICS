package com.example.lab9.Repository;

import com.example.lab9.Entity.Participante;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante,Integer> {

}
