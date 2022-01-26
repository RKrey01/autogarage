/*
package nl.eindopdracht.autogarage.controller;

import nl.eindopdracht.autogarage.model.Receipt;
import nl.eindopdracht.autogarage.repository.ReceiptRepository;
import nl.eindopdracht.autogarage.service.ReceiptServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReceiptController.class)
class ReceiptControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReceiptServiceImpl service;

    @Mock
    private ReceiptRepository repository;

    @Test
    void shouldGetAllReceipts() throws Exception {
        List<Receipt> receipts = repository.findAll();
        when(service.getReceipts())
                .thenReturn(receipts);

        mvc.perform(get("/api/receipt")).andDo(print()).andExpect(status().isOk());

        //to know that the mock is being called
        verify(service).getReceipts();
    }
}*/
