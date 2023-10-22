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
    final ParticipanteRepository participanteRepository;
    final PartidoRepository partidoRepository;
    final ParticipantespartidoRepository participantespartidoRepository;
    final HistoalpartidoRepository histoalpartidoRepository;

    public SDCIController(DeporteRepository deporteRepository, EquipoRepository equipoRepository, ParticipanteRepository participanteRepository,
                          ParticipantespartidoRepository participantespartidoRepository, HistoalpartidoRepository histoalpartidoRepository,
                          PartidoRepository partidoRepository){
        this.deporteRepository = deporteRepository;
        this.equipoRepository = equipoRepository;
        this.participanteRepository = participanteRepository;
        this.participantespartidoRepository =participantespartidoRepository;
        this.histoalpartidoRepository = histoalpartidoRepository;
        this.partidoRepository =partidoRepository;
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
            @RequestBody Map<String, Object> jsonData,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseJson = new HashMap<>();

        Map<String, Object> partidoData = (Map<String, Object>) jsonData.get("partido");

        Partido partido = new Partido();
        partido.setEquipoA((Equipo) partidoData.get("equipoA"));
        partido.setEquipoB((Equipo) partidoData.get("equipoB"));
        partido.setScoreA((int) partidoData.get("scoreA"));
        partido.setScoreB((int) partidoData.get("scoreB"));
        partidoRepository.save(partido);

        Map<String, Object> historialpartidoData = (Map<String, Object>) jsonData.get("historialpartido");
        Historialpartido historialpartido = new Historialpartido();
        historialpartido.setPartidoIdpartido(partido);
        historialpartido.setDeporteIddeporte((Deporte) historialpartidoData.get("deporte_iddeporte"));
        historialpartido.setHoraFecha((Date) historialpartidoData.get("horaFecha"));
        histoalpartidoRepository.save(historialpartido);

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
    public List<Participantespartido> buscarparticipantes(@PathVariable("idequipo") int idequipo) {

        Optional<Equipo> byId = equipoRepository.findById(idequipo);
        if (byId.isPresent()) {
            return participantespartidoRepository.participantePartido();
        } else {
            return null;
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
