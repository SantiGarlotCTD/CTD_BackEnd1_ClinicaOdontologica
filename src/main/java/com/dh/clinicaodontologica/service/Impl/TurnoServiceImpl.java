package com.dh.clinicaodontologica.service.Impl;

import com.dh.clinicaodontologica.exceptions.RecursoNoEncontradoException;
import com.dh.clinicaodontologica.persistence.DTO.PacienteDTO;
import com.dh.clinicaodontologica.persistence.DTO.TurnoDTO;
import com.dh.clinicaodontologica.persistence.entities.Turno;
import com.dh.clinicaodontologica.persistence.repository.OdontologoRepository;
import com.dh.clinicaodontologica.persistence.repository.PacienteRepository;
import com.dh.clinicaodontologica.persistence.repository.TurnoRepository;
import com.dh.clinicaodontologica.service.ClinicaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TurnoServiceImpl implements ClinicaService<TurnoDTO> {

    @Autowired
    TurnoRepository turnoRepository;
    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    OdontologoRepository odontologoRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public void guardar(TurnoDTO turnoDTO) {
        Turno turno = mapper.convertValue(turnoDTO, Turno.class);
        turnoRepository.save(turno);
    }

    @Override
    public TurnoDTO buscarPorId(Long id) {
        TurnoDTO turnoDTO = null;
        Optional<Turno> turno = turnoRepository.findById(id);
        turnoDTO = mapper.convertValue(turno, TurnoDTO.class);
        return turnoDTO;
    }

    @Override
    public Collection<TurnoDTO> listarTodos() {
        List<Turno> turnos = turnoRepository.findAll();
        Set<TurnoDTO> turnoDTOSet = new HashSet<>();
        for(Turno turno : turnos){
            turnoDTOSet.add(mapper.convertValue(turno, TurnoDTO.class));
        }
        return turnoDTOSet;
    }

    @Override
    public void borrarPorId(Long id) throws RecursoNoEncontradoException {
        if(buscarPorId(id) == null){
            throw new RecursoNoEncontradoException("No existe el turno");
        }
        turnoRepository.deleteById(id);
    }

    @Override
    public void actualizar(TurnoDTO turnoDTO) throws RecursoNoEncontradoException {
        if(buscarPorId(turnoDTO.getId()) == null){
            throw new RecursoNoEncontradoException("No existe el turno");
        }
        Turno turno = mapper.convertValue(turnoDTO, Turno.class);
        turnoRepository.save(turno);
    }

}
