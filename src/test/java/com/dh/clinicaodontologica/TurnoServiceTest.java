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
        DomicilioDTO domicilio = new DomicilioDTO("Av Santa fe", 444, "CABA", "Buenos Aires");
        pacienteService.guardar(new PacienteDTO("Santiago", "Paz", "88888888", new Date(), domicilio));
        this.odontologoService.guardar(new OdontologoDTO("Santiago", "Paz", 3455647));
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
