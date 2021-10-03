package com.dh.clinicaodontologica;

import com.dh.clinicaodontologica.persistence.DTO.OdontologoDTO;
import com.dh.clinicaodontologica.service.Impl.OdontologoServiceImpl;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class OdontologoServiceTest {
    @Autowired
    private OdontologoServiceImpl odontologoService;


    public void cargarDataSet() {
        this.odontologoService.guardar(new OdontologoDTO("Santiago", "Paz", 3455647));
    }

    @Test
    public void agregarOdontologo() {
        this.cargarDataSet();
        OdontologoDTO odontologo = new OdontologoDTO("Juan", "Ramirez", 348971960);
        odontologoService.guardar(odontologo);
        Assert.assertNotNull(odontologoService.buscarPorId(2L));

    }

    @Test
    public void eliminarOdontologoTest() {
        odontologoService.borrarPorId(2L);
        Assert.assertNull(odontologoService.buscarPorId(2L));

    }

    @Test
    public void traerTodos() {
        Collection<OdontologoDTO> odontologos = odontologoService.listarTodos();
        Assert.assertEquals(1, odontologos.size());
        OdontologoDTO odontologo = new OdontologoDTO("Juan", "Ramirez", 348971960);
        odontologoService.guardar(odontologo);
        odontologos = odontologoService.listarTodos();
        Assert.assertEquals(2, odontologos.size());
        System.out.println(odontologos);
    }

}
