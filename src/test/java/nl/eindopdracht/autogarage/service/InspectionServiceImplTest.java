package nl.eindopdracht.autogarage.service;

import nl.eindopdracht.autogarage.model.Car;
import nl.eindopdracht.autogarage.model.Inspection;
import nl.eindopdracht.autogarage.repository.InspectionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InspectionServiceImplTest {

    @Mock
    private InspectionRepository repository;
    private InspectionServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new InspectionServiceImpl(repository);
    }

    @Test
    void canAddNewInspection() {
        //given
        Car car1 = new Car();

        Inspection inspection = new Inspection(
                car1,
                LocalDate.of(2019,03,9),
                "Remschijf en rechter voorlamp"
        );

        //when
        underTest.addNewInspection(inspection);

        //then
        ArgumentCaptor<Inspection> inspectionArgumentCaptor = ArgumentCaptor.forClass(Inspection.class);

        verify(repository).save(inspectionArgumentCaptor.capture());

        Inspection capturedInspection = inspectionArgumentCaptor.getValue();

        assertThat(capturedInspection).isEqualTo(inspection);
    }

    @Test
    void canGetInspections() {
        //when
        underTest.getInspections();
        //then
        verify(repository).findAll();
    }

    @Test
    void willFindInspectionById() {
        //given
        Car car1 = new Car();

        Inspection inspection = new Inspection(
                car1,
                LocalDate.of(2019,03,9),
                "Remschijf en rechter voorlamp"
        );

        //when
        given(repository.findById(inspection.getId())).willReturn(Optional.of(inspection));
        underTest.findInspectionById(inspection.getId());

        //then
        verify(repository).findById(inspection.getId());
    }

    @Test
    void willThrowWhenIdDoesNotExist() throws Exception {
        //given
        Car car1 = new Car();

        Inspection inspection = new Inspection(
                car1,
                LocalDate.of(2019,03,9),
                "Remschijf en rechter voorlamp"
        );

        //when
        given(repository.findById(inspection.getId())).willReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> underTest.findInspectionById(inspection.getId()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Keuring niet gevonden! ID - " + inspection.getId());
    }

    @Test
    void willDeleteInspectionById() {
        //given
        Car car1 = new Car();

        Inspection inspection = new Inspection(
                car1,
                LocalDate.of(2019,03,9),
                "Remschijf en rechter voorlamp"
        );

        //when
        given(repository.existsById(inspection.getId())).willReturn(true);
        underTest.deleteInspectionById(inspection.getId());

        //then
        verify(repository).deleteById(inspection.getId());
    }

    @Test
    void willThrowWhenDeleteFails() {
        //given
        Car car1 = new Car();

        Inspection inspection = new Inspection(
                car1,
                LocalDate.of(2019,03,9),
                "Remschijf en rechter voorlamp"
        );

        //when
        given(repository.existsById(inspection.getId())).willReturn(false);

        //then
        assertThatThrownBy(() -> underTest.deleteInspectionById(inspection.getId()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Keuring met id " + inspection.getId() + " bestaat niet");
    }
}