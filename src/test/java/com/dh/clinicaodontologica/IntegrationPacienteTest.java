package com.dh.clinicaodontologica;

import com.dh.clinicaodontologica.persistence.DTO.DomicilioDTO;
import com.dh.clinicaodontologica.persistence.DTO.OdontologoDTO;
import com.dh.clinicaodontologica.persistence.DTO.PacienteDTO;
import com.dh.clinicaodontologica.persistence.entities.Domicilio;
import com.dh.clinicaodontologica.service.Impl.PacienteServiceImpl;
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
public class IntegrationPacienteTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PacienteServiceImpl pacienteService;
    @Autowired
    ObjectMapper mapper;


    public void cargarDataSet() {
        DomicilioDTO domicilio = new DomicilioDTO("Av. Velez Sarsfield", 999, "Cordoba", "Cordoba");
        pacienteService.guardar(new PacienteDTO("Marcelo", "Montoto", "12345", new Date(), domicilio));
    }

    @SneakyThrows
    @Test
    public void listarPacientes(){
        this.cargarDataSet();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/{id}", 1).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @SneakyThrows
    @Test
    public void registrarPaciente() {
        cargarDataSet();
        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/pacientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(pacienteService.buscarPorId(1L))))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }
}
