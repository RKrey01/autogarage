package nl.eindopdracht.autogarage.controller;

import nl.eindopdracht.autogarage.model.Part;
import nl.eindopdracht.autogarage.repository.PartRepository;
import nl.eindopdracht.autogarage.service.PartServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PartController.class)
class PartControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PartServiceImpl service;

    @Mock
    private PartRepository repository;

    @Test
    void shouldGetAllParts() throws Exception {
        List<Part> parts = repository.findAll();
        when(service.getParts())
                .thenReturn(parts);

        mvc.perform(get("/api/part")).andDo(print()).andExpect(status().isOk());

        //to know that the mock is being called
        verify(service).getParts();
    }

    @Test
    void shouldGetPartById() throws Exception {
        Part part = new Part(
                1L,
                "remschijf",
                35.50,
                7
        );

        when(service.findPartById(part.getId()))
                .thenReturn(part);

        mvc.perform(get("/api/part/{partId}", part.getId())).andDo(print()).andExpect(status().isOk());

        verify(service).findPartById(part.getId());
    }

    @Test
    void shouldDeletePartById() throws Exception {
        Part part = new Part(
                1L,
                "remschijf",
                35.50,
                7
        );

        mvc.perform(delete("/api/part/{partId}", part.getId())).andExpect(status().isOk());

        verify(service).deletePartById(part.getId());
    }
}