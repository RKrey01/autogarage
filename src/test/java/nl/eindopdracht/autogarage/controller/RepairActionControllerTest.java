/*
package nl.eindopdracht.autogarage.controller;

import nl.eindopdracht.autogarage.model.RepairAction;
import nl.eindopdracht.autogarage.repository.RepairActionRepository;
import nl.eindopdracht.autogarage.service.RepairActionServiceImpl;
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

@WebMvcTest(RepairActionController.class)
class RepairActionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RepairActionServiceImpl service;

    @Mock
    private RepairActionRepository repository;

    @Test
    void shouldGetAllRepairActions() throws Exception {
        List<RepairAction> repairActions = repository.findAll();
        when(service.getRepairActions())
                .thenReturn(repairActions);

        mvc.perform(get("/api/repairAction")).andDo(print()).andExpect(status().isOk());

        //to know that the mock is being called
        verify(service).getRepairActions();
    }

    @Test
    void shouldGetRepairActionById() throws Exception {
        RepairAction repairAction = new RepairAction(
                1L,
                "remschijf verwisselen",
                50.0
        );

        when(service.findRepairActionById(repairAction.getId()))
                .thenReturn(repairAction);

        mvc.perform(get("/api/repairAction/{repairActionId}", repairAction.getId())).andDo(print()).andExpect(status().isOk());

        verify(service).findRepairActionById(repairAction.getId());
    }

    @Test
    void shouldDeleteRepairActionById() throws Exception {
        RepairAction repairAction = new RepairAction(
                1L,
                "remschijf verwisselen",
                50.0
        );

        mvc.perform(delete("/api/repairAction/{repairActionId}", repairAction.getId())).andExpect(status().isOk());

        verify(service).deleteRepairActionById(repairAction.getId());
    }
}*/
