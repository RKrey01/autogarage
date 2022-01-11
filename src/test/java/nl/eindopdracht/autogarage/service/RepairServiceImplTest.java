package nl.eindopdracht.autogarage.service;

import nl.eindopdracht.autogarage.Enumeration.RepairStatus;
import nl.eindopdracht.autogarage.model.Repair;
import nl.eindopdracht.autogarage.repository.PartRepository;
import nl.eindopdracht.autogarage.repository.RepairActionRepository;
import nl.eindopdracht.autogarage.repository.RepairRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RepairServiceImplTest {

    @Mock
    private RepairRepository repository;
    private RepairServiceImpl underTest;

    @Mock
    private PartRepository partRepository;
    @Mock
    private RepairActionRepository repairActionRepository;

    @BeforeEach
    void setUp() {
        underTest = new RepairServiceImpl(repository);
    }

    @Test
    void canAddNewReparation() {
        //given
        Repair repair = new Repair(
                partRepository.findAll(),
                repairActionRepository.findAll(),
                RepairStatus.IN_PROGRESS
        );

        //when
        underTest.addNewReparation(repair);

        //then
        ArgumentCaptor<Repair> repairArgumentCaptor = ArgumentCaptor.forClass(Repair.class);

        verify(repository).save(repairArgumentCaptor.capture());

        Repair capturedRepair = repairArgumentCaptor.getValue();

        assertThat(capturedRepair).isEqualTo(repair);
    }

    @Test
    void canGetReparations() {
        //when
        underTest.getReparations();
        //then
        verify(repository).findAll();
    }

    @Test
    void willFindReparationById() {
        //given
        Repair repair = new Repair(
                partRepository.findAll(),
                repairActionRepository.findAll(),
                RepairStatus.IN_PROGRESS
        );

        //when
        given(repository.findById(repair.getId())).willReturn(Optional.of(repair));
        underTest.findReparationById(repair.getId());

        //then
        verify(repository).findById(repair.getId());
    }

    @Test
    void willThrowWhenIdDoesNotExist() throws Exception {
        //given
        Repair repair = new Repair(
                partRepository.findAll(),
                repairActionRepository.findAll(),
                RepairStatus.IN_PROGRESS
        );

        //when
        given(repository.findById(repair.getId())).willReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> underTest.findReparationById(repair.getId()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Reparatie niet gevonden! ID - " + repair.getId());
    }

    @Test
    void willFindReparationByStatus() {
        //given
        Repair repair = new Repair(
                partRepository.findAll(),
                repairActionRepository.findAll(),
                RepairStatus.IN_PROGRESS
        );

        //when
        given(repository.findReparationByStatus(repair.getStatus())).willReturn(Optional.of(repair));
        underTest.findReparationByStatus(repair.getStatus());

        //then
        verify(repository).findReparationByStatus(repair.getStatus());
    }

    @Test
    void willDeleteReparationById() {
        //given
        Repair repair = new Repair(
                partRepository.findAll(),
                repairActionRepository.findAll(),
                RepairStatus.IN_PROGRESS
        );

        //when
        given(repository.existsById(repair.getId())).willReturn(true);
        underTest.deleteReparationById(repair.getId());

        //then
        verify(repository).deleteById(repair.getId());
    }

    @Test
    void willThrowWhenDeleteFails() {
        //given
        Repair repair = new Repair(
                partRepository.findAll(),
                repairActionRepository.findAll(),
                RepairStatus.IN_PROGRESS
        );

        //when
        given(repository.existsById(repair.getId())).willReturn(false);

        //then
        assertThatThrownBy(() -> underTest.deleteReparationById(repair.getId()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Reparatie met id " + repair.getId() + " bestaat niet");
    }
}