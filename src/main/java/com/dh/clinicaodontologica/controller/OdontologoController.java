package com.dh.clinicaodontologica.controller;

import com.dh.clinicaodontologica.persistence.DTO.OdontologoDTO;
import com.dh.clinicaodontologica.service.Impl.OdontologoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    OdontologoServiceImpl odontologoService;

    @PostMapping
    public ResponseEntity<?> guardarOdontolgo(@RequestBody OdontologoDTO odontologoDTO){
        odontologoService.guardar(odontologoDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Se creo correctamente el odontologo");
    }

    @GetMapping("/todos")
    public Collection<OdontologoDTO> consultarTodos(){
        return odontologoService.listarTodos();
    }

    @GetMapping("/{id}")
    public OdontologoDTO buscarPorId(@PathVariable Long id) {
        return odontologoService.buscarPorId(id);
    }

    @PutMapping()
    public ResponseEntity<?> actualizarOdontologo(@RequestBody OdontologoDTO odontologoDTO) {
        odontologoService.actualizar(odontologoDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente el odontologo");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) {
        odontologoService.borrarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente el odontologo");
    }
}
