package com.dh.clinicaodontologica;

import com.dh.clinicaodontologica.persistence.DTO.DomicilioDTO;
import com.dh.clinicaodontologica.persistence.DTO.OdontologoDTO;
import com.dh.clinicaodontologica.persistence.DTO.PacienteDTO;
import com.dh.clinicaodontologica.persistence.DTO.TurnoDTO;
import com.dh.clinicaodontologica.service.Impl.OdontologoServiceImpl;
import com.dh.clinicaodontologica.service.Impl.PacienteServiceImpl;
import com.dh.clinicaodontologica.service.Impl.TurnoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnoTest {
    @Autowired
    private PacienteServiceImpl pacienteService;
    @Autowired
    private OdontologoServiceImpl odontologoService;
    @Autowired
    private TurnoServiceImpl turnoService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @SneakyThrows
    public void cargarDataSet() {
        DomicilioDTO domicilio = new DomicilioDTO("Bv. Chacabuco", 123, "Cordoba", "Cordoba");
        pacienteService.guardar(new PacienteDTO("Pedro", "Paniza", "12345", new Date(), domicilio));
        odontologoService.guardar(new OdontologoDTO("Juan", "Perez", 12345));
        turnoService.guardar(new TurnoDTO(pacienteService.buscarPorId(1L),odontologoService.buscarPorId(1L),new Date()));

    }
    @Test
    public void listarTurnos() throws Exception {
        this.cargarDataSet();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/turnos/todos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @SneakyThrows
    @Test
    public void registrarTurno() {
        cargarDataSet();
        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/turnos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(turnoService.buscarPorId(1L))))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }
}
