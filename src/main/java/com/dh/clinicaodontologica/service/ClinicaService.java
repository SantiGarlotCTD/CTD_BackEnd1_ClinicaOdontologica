package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.exceptions.RecursoNoEncontradoException;
import com.dh.clinicaodontologica.persistence.DTO.PacienteDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface ClinicaService<T> {

    void guardar(T t);

    T buscarPorId(Long id);

    Collection<T> listarTodos();

    void borrarPorId(Long id) throws RecursoNoEncontradoException;

    void actualizar(T t) throws RecursoNoEncontradoException;
}
