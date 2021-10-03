package com.dh.clinicaodontologica;


import com.dh.clinicaodontologica.persistence.DTO.DomicilioDTO;
import com.dh.clinicaodontologica.persistence.DTO.PacienteDTO;
import com.dh.clinicaodontologica.service.Impl.PacienteServiceImpl;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.Date;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class PacienteServiceTest {
    @Autowired
    private PacienteServiceImpl pacienteService;


    public void cargarDataSet() {
        DomicilioDTO domicilio = new DomicilioDTO("Av Colon", 123, "Cordoba", "Cordoba");
        pacienteService.guardar(new PacienteDTO("Santiago", "Paz", "88888888", new Date(), domicilio));
        DomicilioDTO domicilio1 = new DomicilioDTO("La Tabalada", 333, "Cordoba", "Cordoba");
        pacienteService.guardar(new PacienteDTO("Marcos", "Dipre", "99999999", new Date(), domicilio1));

    }

    @Test
    public void agregarYBuscarPacienteTest() {
        this.cargarDataSet();
        DomicilioDTO domicilio = new DomicilioDTO("Arturo M. Bas", 567, "Cordoba", "Cordoba");
        pacienteService.guardar(new PacienteDTO("Martin", "Marley", "12345678", new Date(), domicilio));

        Assert.assertNotNull(pacienteService.buscarPorId(3L));
    }

    @SneakyThrows
    @Test
    public void eliminarPacienteTest() {
        pacienteService.borrarPorId(3L);
        Assert.assertFalse((pacienteService.buscarPorId(3L) != null));

    }

    @SneakyThrows
    @Test
    public void traerTodos() {
        Collection<PacienteDTO> pacientes = pacienteService.listarTodos();
        Assert.assertEquals(2, pacientes.size());
        pacienteService.borrarPorId(2L);
        pacientes = pacienteService.listarTodos();
        Assert.assertEquals(1, pacientes.size());
    }


}
