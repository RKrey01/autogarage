package nl.eindopdracht.autogarage.service;

import nl.eindopdracht.autogarage.model.Part;
import nl.eindopdracht.autogarage.repository.PartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PartServiceImplTest {

    @Mock
    private PartRepository repository;
    private PartServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new PartServiceImpl(repository);
    }

    @Test
    void canGetParts() {
        //when
        underTest.getParts();
        //then
        verify(repository).findAll();
    }

    @Test
    void willFindPartById() {
        //given
        Part part = new Part(
                "remschijf",
                35.50,
                7
        );

        //when
        given(repository.findById(part.getId())).willReturn(Optional.of(part));
        underTest.findPartById(part.getId());

        //then
        verify(repository).findById(part.getId());
    }

    @Test
    void willThrowWhenIdDoesNotExist() throws Exception {
        //given
        Part part = new Part(
                "remschijf",
                35.50,
                7
        );


        //when
        given(repository.findById(part.getId())).willReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> underTest.findPartById(part.getId()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Onderdeel niet gevonden! ID - " + part.getId());
    }

    @Test
    void canAddNewPart() {
        //given
        Part part = new Part(
                "remschijf",
                35.50,
                7
        );

        //when
        underTest.addNewPart(part);

        //then
        ArgumentCaptor<Part> partArgumentCaptor = ArgumentCaptor.forClass(Part.class);

        verify(repository).save(partArgumentCaptor.capture());

        Part capturedPart = partArgumentCaptor.getValue();

        assertThat(capturedPart).isEqualTo(part);
    }

    @Test
    void willThrowWhenNameIsTaken() {
        //given
        Part part = new Part(
                "remschijf",
                35.50,
                7
        );

        given(repository.findPartByName(part.getName())).willReturn(Optional.of(part));

        //when
        //then
        assertThatThrownBy(() -> underTest.addNewPart(part))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("naam in gebruik");

        //the repository (mock) never saves any parts
        verify(repository, never()).save(any());
    }

    @Test
    void willDeletePartById() {
        //given
        Part part = new Part(
                "remschijf",
                35.50,
                7
        );

        //when
        given(repository.existsById(part.getId())).willReturn(true);
        underTest.deletePartById(part.getId());

        //then
        verify(repository).deleteById(part.getId());
    }

    @Test
    void willThrowWhenDeleteFails() {
        //given
        Part part = new Part(
                "remschijf",
                35.50,
                7
        );

        //when
        given(repository.existsById(part.getId())).willReturn(false);

        //then
        assertThatThrownBy(() -> underTest.deletePartById(part.getId()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Onderdeel met id " + part.getId() + " bestaat niet");
    }
}