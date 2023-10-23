package com.example.lab9.Controllers;

import com.example.lab9.Entity.*;
import com.example.lab9.Repository.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping("/sdci")
public class SDCIController {

    final DeporteRepository deporteRepository;
    final EquipoRepository equipoRepository;
    final HistoalpartidoRepository histoalpartidoRepository;
    final ParticipanteRepository participanteRepository;
    final ParticipantespartidoRepository participantespartidoRepository;
    final PartidoRepository partidoRepository;

    public SDCIController(DeporteRepository deporteRepository, EquipoRepository equipoRepository, HistoalpartidoRepository histoalpartidoRepository,
                          ParticipanteRepository participanteRepository, ParticipantespartidoRepository participantespartidoRepository, PartidoRepository partidoRepository) {

        this.deporteRepository = deporteRepository;
        this.equipoRepository = equipoRepository;
        this.histoalpartidoRepository = histoalpartidoRepository;
        this.participanteRepository = participanteRepository;
        this.partidoRepository= partidoRepository;
        this. participantespartidoRepository=participantespartidoRepository;
    }

    //                       ACTIVIDAD 1

    // a) EQUIPO
    @PostMapping( "/equipo/registro")
    public ResponseEntity<HashMap<String, Object>> registroequipo(
            @RequestBody Equipo equipo,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseJson = new HashMap<>();

        equipoRepository.save(equipo);
        if (fetchId) {
            responseJson.put("id", equipo.getIdequipo());
        }
        responseJson.put("estado", "Creado exitosamente");
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }


    // b) PARTICIPANTE
    @PostMapping("/participante/registro")
    public ResponseEntity<HashMap<String, Object>> registroparticipante(
            @RequestBody Participante participante,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseJson = new HashMap<>();

        participanteRepository.save(participante);
        if (fetchId) {
            responseJson.put("id", participante.getIdparticipante());
        }
        responseJson.put("estado", "Creado exitosamente");
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }


    // c) DEPORTES
    @PostMapping( "/deporte/registro")
    public ResponseEntity<HashMap<String, Object>> guardarDeporte(
            @RequestBody Deporte deporte,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseJson = new HashMap<>();

        deporteRepository.save(deporte);
        if (fetchId) {
            responseJson.put("id", deporte.getIddeporte());
        }
        responseJson.put("estado", "Creado exitosamente");
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    //                ACTIVIDAD 2

    // a) REGISTRO PARTIDOS Y EN LA TABLA HISTORIAL
    @PostMapping( "/partido/registro")
    public ResponseEntity<HashMap<String, Object>> guardarPartido(
            @RequestBody Partido partido,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseJson = new HashMap<>();

        partidoRepository.save(partido);
        if (fetchId) {
            responseJson.put("id", partido.getIdpartido());
        }
        responseJson.put("estado", "Creado exitosamente");
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }



    // b) LISTA PARTICPANTES DE UN PARTIDO GENERAL
    // b.1)
    @GetMapping("/partido/getparticipantes")
    public List<Participantespartido> listaParticipantes() {

        return participantespartidoRepository.findAll();
    }

    // LISTA DE PARICIPANTES DE UN PARTIDO SI SE INDICA UNO DE LOS EQUIPOS JUGADORES
    // b.2)
    @GetMapping("/partido/getparticipantes/{idequipo}")
    public ResponseEntity<List<Participantespartido>> buscarparticipantes(@PathVariable("idequipo") String  idequipo) {

        try {
            int idequipoInt = Integer.parseInt(idequipo);
            Optional<Equipo> equipoOptional = equipoRepository.findById(idequipoInt);

            if (equipoOptional.isPresent()) {
                List<Participantespartido> participantes = participantespartidoRepository.participantePartido(idequipoInt);
                return ResponseEntity.ok(participantes);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(null);
        }

    }


    // c) HISTOROIAL DE PARTIDO GENERAL
    // c.1)
    @GetMapping("/partido/gethistorialpartido")
    public List<Historialpartido> listaPartidos() {

        return histoalpartidoRepository.findAll();
    }

    // HISTORIAL DE PARTIDO DE UN EQUIPO
    // c.2
    @GetMapping(value = "/partido/gethistorialpartido/{idEquipo}")
    public ResponseEntity<List<Historialpartido>> buscarHistoralEquipo(@PathVariable("idEquipo") String idEquipo) {

        try {
            int id = Integer.parseInt(idEquipo);
            Optional<Equipo> equipoOptional = equipoRepository.findById(id);

            if (equipoOptional.isPresent()) {
                List<Historialpartido> historialpartidos = histoalpartidoRepository.Historial(id);
                return ResponseEntity.ok(historialpartidos);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String, String>> gestionException(HttpServletRequest request) {
        HashMap<String, String> responseMap = new HashMap<>();
        if (request.getMethod().equals("POST") || request.getMethod().equals("PUT")) {
            responseMap.put("estado", "Hubo un error");
            responseMap.put("msg", "Asegurese de enviar correctamente los datos");
        }
        return ResponseEntity.badRequest().body(responseMap);
    }

}
