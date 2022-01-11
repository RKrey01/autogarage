package nl.eindopdracht.autogarage.controller;

import nl.eindopdracht.autogarage.model.Car;
import nl.eindopdracht.autogarage.model.Inspection;
import nl.eindopdracht.autogarage.repository.InspectionRepository;
import nl.eindopdracht.autogarage.service.InspectionServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InspectionController.class)
class InspectionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private InspectionServiceImpl service;

    @Mock
    private InspectionRepository repository;

    @Test
    void shouldGetAllInspections() throws Exception {
        List<Inspection> inspections = repository.findAll();
        when(service.getInspections())
                .thenReturn(inspections);

        mvc.perform(get("/api/inspection")).andDo(print()).andExpect(status().isOk());

        //to know that the mock is being called
        verify(service).getInspections();
    }

    @Test
    void shouldGetInspectionById() throws Exception {
        Car car1 = new Car();

        Inspection inspection = new Inspection(
                1L,
                car1,
                LocalDate.of(2019,03,9),
                "Remschijf en rechter voorlamp"
        );

        when(service.findInspectionById(inspection.getId()))
                .thenReturn(inspection);

        mvc.perform(get("/api/inspection/{inspectionId}", inspection.getId())).andDo(print()).andExpect(status().isOk());

        verify(service).findInspectionById(inspection.getId());
    }

    @Test
    void deleteInspectionById() throws Exception {
        Car car1 = new Car();

        Inspection inspection = new Inspection(
                1L,
                car1,
                LocalDate.of(2019,03,9),
                "Remschijf en rechter voorlamp"
        );

        mvc.perform(delete("/api/inspection/{inspectionId}", inspection.getId())).andExpect(status().isOk());

        verify(service).deleteInspectionById(inspection.getId());
    }
}