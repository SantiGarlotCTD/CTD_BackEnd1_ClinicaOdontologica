package com.dh.clinicaodontologica.controller;

import com.dh.clinicaodontologica.exceptions.RecursoNoEncontradoException;
import com.dh.clinicaodontologica.persistence.DTO.TurnoDTO;
import com.dh.clinicaodontologica.service.Impl.TurnoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    TurnoServiceImpl turnoService;

    @PostMapping
    public ResponseEntity<?> guardarTurno(@RequestBody TurnoDTO turnoDTO) throws RecursoNoEncontradoException {
        turnoService.guardar(turnoDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Se creo correctamente el turno");
    }

    @GetMapping("/todos")
    public Collection<TurnoDTO> consultarTodos(){
        return turnoService.listarTodos();
    }

    @GetMapping("/{id}")
    public TurnoDTO buscarPorId(@PathVariable Long id) {
        return turnoService.buscarPorId(id);
    }

    @PutMapping()
    public ResponseEntity<?> actualizarTurno(@RequestBody TurnoDTO turnoDTO) throws RecursoNoEncontradoException {
        turnoService.actualizar(turnoDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente el turno");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id) throws RecursoNoEncontradoException {
        turnoService.borrarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente el turno");
    }
}
