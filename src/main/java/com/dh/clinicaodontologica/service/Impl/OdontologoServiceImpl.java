package com.dh.clinicaodontologica.service.Impl;

import com.dh.clinicaodontologica.persistence.DTO.OdontologoDTO;
import com.dh.clinicaodontologica.persistence.entities.Odontologo;
import com.dh.clinicaodontologica.persistence.repository.OdontologoRepository;
import com.dh.clinicaodontologica.service.ClinicaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OdontologoServiceImpl implements ClinicaService<OdontologoDTO> {

    @Autowired
    OdontologoRepository odontologoRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public void guardar(OdontologoDTO odontologoDTO) {
        Odontologo odontologo = mapper.convertValue(odontologoDTO, Odontologo.class);
        odontologoRepository.save(odontologo);
    }

    @Override
    public OdontologoDTO buscarPorId(Long id) {
        OdontologoDTO odontologoDTO = null;
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        odontologoDTO = mapper.convertValue(odontologo, OdontologoDTO.class);
        return odontologoDTO;
    }

    @Override
    public Collection<OdontologoDTO> listarTodos() {
        List<Odontologo> odontologos = odontologoRepository.findAll();
        Set<OdontologoDTO> odontologoDTOSet = new HashSet<>();
        for(Odontologo odontologo : odontologos){
            OdontologoDTO odontologoDTO = mapper.convertValue(odontologo, OdontologoDTO.class);
            odontologoDTOSet.add(odontologoDTO);
        }
        return odontologoDTOSet;
    }

    @Override
    public void borrarPorId(Long id) {
        odontologoRepository.deleteById(id);
    }

    @Override
    public void actualizar(OdontologoDTO odontologoDTO) {
        Odontologo odontologo = mapper.convertValue(odontologoDTO, Odontologo.class);
        odontologoRepository.save(odontologo);
    }
}
