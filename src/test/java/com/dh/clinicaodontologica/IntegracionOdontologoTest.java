package com.dh.clinicaodontologica;

import com.dh.clinicaodontologica.persistence.DTO.OdontologoDTO;
import com.dh.clinicaodontologica.service.Impl.OdontologoServiceImpl;
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


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionOdontologoTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    OdontologoServiceImpl odontologoService;
    @Autowired
    ObjectMapper mapper;

    public void cargarDataSet() {
        this.odontologoService.guardar(new OdontologoDTO("Marina", "Nautilus", 12345));
    }

    @SneakyThrows
    @Test
    public void listarOdontologos(){
        this.cargarDataSet();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/{id}", 1).accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void registrarOdontologo() throws Exception {
        cargarDataSet();
        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/odontologos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(odontologoService.buscarPorId(1L))))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
    }
}
