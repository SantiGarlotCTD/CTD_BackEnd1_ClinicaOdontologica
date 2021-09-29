package com.dh.clinicaodontologica.service.Impl;

import com.dh.clinicaodontologica.exceptions.RecursoNoEncontradoException;
import com.dh.clinicaodontologica.persistence.DTO.PacienteDTO;
import com.dh.clinicaodontologica.persistence.entities.Paciente;
import com.dh.clinicaodontologica.persistence.repository.PacienteRepository;
import com.dh.clinicaodontologica.service.ClinicaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PacienteServiceImpl implements ClinicaService<PacienteDTO> {

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public void guardar(PacienteDTO pacienteDTO) {
        Paciente paciente = mapper.convertValue(pacienteDTO, Paciente.class);
        pacienteRepository.save(paciente);
    }

    @Override
    public PacienteDTO buscarPorId(Long id) {
        PacienteDTO pacienteDTO = null;
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if(paciente.isPresent()){
            pacienteDTO = mapper.convertValue(paciente, PacienteDTO.class);
        }
        return pacienteDTO;
    }

    @Override
    public Collection<PacienteDTO> listarTodos() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        Set<PacienteDTO> pacienteDTOSet = new HashSet<>();
        for(Paciente paciente: pacientes){
            pacienteDTOSet.add(mapper.convertValue(paciente, PacienteDTO.class));
        }
        return pacienteDTOSet;
    }

    @Override
    public void borrarPorId(Long id) throws RecursoNoEncontradoException {
        if(buscarPorId(id) == null){
            throw new RecursoNoEncontradoException("No existe el paciente, por favor intente crearlo pirmiero");
        }
        pacienteRepository.deleteById(id);
    }

    @Override
    public void actualizar(PacienteDTO pacienteDTO) throws RecursoNoEncontradoException {
        if(buscarPorId(pacienteDTO.getId()) == null){
            throw new RecursoNoEncontradoException("No existe el paciente, por favor intente crearlo pirmiero");
        }
        Paciente paciente = mapper.convertValue(pacienteDTO, Paciente.class);
        pacienteRepository.save(paciente);
    }
}
