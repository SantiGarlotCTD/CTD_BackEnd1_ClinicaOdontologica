package com.dh.clinicaodontologica;

import com.dh.clinicaodontologica.persistence.DTO.DomicilioDTO;
import com.dh.clinicaodontologica.persistence.DTO.OdontologoDTO;
import com.dh.clinicaodontologica.persistence.DTO.PacienteDTO;
import com.dh.clinicaodontologica.persistence.DTO.TurnoDTO;
import com.dh.clinicaodontologica.service.Impl.OdontologoServiceImpl;
import com.dh.clinicaodontologica.service.Impl.PacienteServiceImpl;
import com.dh.clinicaodontologica.service.Impl.TurnoServiceImpl;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TurnoServiceTest {

    @Autowired
    private PacienteServiceImpl pacienteService;
    @Autowired
    private OdontologoServiceImpl odontologoService;
    @Autowired
    private TurnoServiceImpl turnoService;

    public void cargarDataSet() {
        DomicilioDTO domicilio = new DomicilioDTO("Bv San Juan", 1252, "Capital", "Cordoba");
        pacienteService.guardar(new PacienteDTO("Dolores", "deMuela", "123452", new Date(), domicilio));
        odontologoService.guardar(new OdontologoDTO("Martina", "Molar", 9876));
    }
    @SneakyThrows
    @Test
    public void altaTurnoTest(){
        this.cargarDataSet();
        turnoService.guardar(new TurnoDTO(pacienteService.buscarPorId(1L),odontologoService.buscarPorId(1L),new Date()));
        Assert.assertNotNull(turnoService.buscarPorId(1L));
    }
    @Test
    public void buscarTurnoTest(){
        Assert.assertNotNull(turnoService.buscarPorId(1L));
    }

    @SneakyThrows
    @Test
    public void eliminarTurnoTest(){
        turnoService.borrarPorId(1L);
        Assert.assertNull(turnoService.buscarPorId(1L));
    }
}
