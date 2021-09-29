package com.dh.clinicaodontologica.controller;

import com.dh.clinicaodontologica.exceptions.RecursoNoEncontradoException;
import com.dh.clinicaodontologica.persistence.DTO.PacienteDTO;
import com.dh.clinicaodontologica.service.Impl.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteServiceImpl pacienteService;

    @PostMapping
    public ResponseEntity<?> guardarPaciente(@RequestBody PacienteDTO pacienteDTO){
        pacienteService.guardar(pacienteDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Se creo correctamente el paciente");
    }

    @GetMapping("/todos")
    public Collection<PacienteDTO> consultarTodos(){
        return pacienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public PacienteDTO buscarPorId(@PathVariable Long id) {
        return pacienteService.buscarPorId(id);
    }

    @PutMapping()
    public ResponseEntity<?> actualizarPaciente(@RequestBody PacienteDTO pacienteDTO) throws RecursoNoEncontradoException {
        pacienteService.actualizar(pacienteDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente el paciente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) throws RecursoNoEncontradoException {
        pacienteService.borrarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente el paciente");
    }
}
