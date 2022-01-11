package nl.eindopdracht.autogarage.service;

import nl.eindopdracht.autogarage.repository.ReceiptRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ReceiptServiceImplTest {

    @Mock
    private ReceiptRepository repository;
    private ReceiptServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new ReceiptServiceImpl(repository);
    }

    @Test
    void getReceipts() {
        //when
        underTest.getReceipts();
        //then
        verify(repository).findAll();
    }

}