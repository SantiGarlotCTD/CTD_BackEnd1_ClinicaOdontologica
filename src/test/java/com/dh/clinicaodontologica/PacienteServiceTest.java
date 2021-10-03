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
        DomicilioDTO domicilio = new DomicilioDTO("Av Santa fe", 444, "CABA", "Buenos Aires");
        pacienteService.guardar(new PacienteDTO("Santiago", "Paz", "88888888", new Date(), domicilio));
        DomicilioDTO domicilio1 = new DomicilioDTO("Av Avellaneda", 333, "CABA", "Buenos Aires");
        pacienteService.guardar(new PacienteDTO("Micaela", "Perez", "99999999", new Date(), domicilio1));

    }

    @Test
    public void agregarYBuscarPacienteTest() {
        this.cargarDataSet();
        pacienteService.guardar(new PacienteDTO("Tomas", "Pereyra", "12345678", new Date(), new DomicilioDTO("Calle", 123, "Temperley", "Buenos Aires")));

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
