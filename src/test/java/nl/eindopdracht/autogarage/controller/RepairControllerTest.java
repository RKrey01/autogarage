/*
package nl.eindopdracht.autogarage.controller;

import nl.eindopdracht.autogarage.Enumeration.RepairStatus;
import nl.eindopdracht.autogarage.model.Repair;
import nl.eindopdracht.autogarage.repository.PartRepository;
import nl.eindopdracht.autogarage.repository.RepairActionRepository;
import nl.eindopdracht.autogarage.repository.RepairRepository;
import nl.eindopdracht.autogarage.service.RepairServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RepairController.class)
class RepairControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RepairServiceImpl service;

    @Mock
    private RepairRepository repository;
    @Mock
    private PartRepository partRepository;
    @Mock
    private RepairActionRepository repairActionRepository;

    @Test
    void shouldGetAllReparations() throws Exception {
        List<Repair> repairs = repository.findAll();
        when(service.getReparations())
                .thenReturn(repairs);

        mvc.perform(get("/api/reparation")).andDo(print()).andExpect(status().isOk());

        //to know that the mock is being called
        verify(service).getReparations();
    }

    @Test
    void shouldGetReparationById() throws Exception {
        Repair repair = new Repair(
                1L,
                partRepository.findAll(),
                repairActionRepository.findAll(),
                RepairStatus.IN_PROGRESS
        );

        when(service.findReparationById(repair.getId()))
                .thenReturn(repair);

        mvc.perform(get("/api/reparation/{repairId}", repair.getId()))
                .andDo(print())
                .andExpect(status().isOk());

        verify(service).findReparationById(repair.getId());
    }

    @Test
    void shouldGetReparationByStatus() throws Exception {
        Repair repair = new Repair(
                1L,
                partRepository.findAll(),
                repairActionRepository.findAll(),
                RepairStatus.IN_PROGRESS
        );

        when(service.findReparationByStatus(repair.getStatus()))
                .thenReturn(Optional.of(repair));

        mvc.perform(get("/api/reparation/{repairId}/{repairStatus}?repairStatus=IN_PROGRESS", repair.getId(), repair.getStatus()))
                .andDo(print())
                .andExpect(status().isOk());

        verify(service).findReparationByStatus(repair.getStatus());
    }

    @Test
    void shouldDeleteReparationById() throws Exception {
        Repair repair = new Repair(
                1L,
                partRepository.findAll(),
                repairActionRepository.findAll(),
                RepairStatus.IN_PROGRESS
        );

        mvc.perform(delete("/api/reparation/{repairId}", repair.getId())).andExpect(status().isOk());

        verify(service).deleteReparationById(repair.getId());
    }
}*/
