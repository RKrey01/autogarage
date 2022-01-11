package nl.eindopdracht.autogarage.service;

import nl.eindopdracht.autogarage.model.RepairAction;
import nl.eindopdracht.autogarage.repository.RepairActionRepository;
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
class RepairActionServiceImplTest {

    @Mock
    private RepairActionRepository repository;
    private RepairActionServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new RepairActionServiceImpl(repository);
    }

    @Test
    void canGetRepairActions() {
        //when
        underTest.getRepairActions();
        //then
        verify(repository).findAll();
    }

    @Test
    void willFindRepairActionById() {
        //given
        RepairAction repairAction = new RepairAction(
                "remschijf verwisselen",
                50.0
        );

        //when
        given(repository.findById(repairAction.getId())).willReturn(Optional.of(repairAction));
        underTest.findRepairActionById(repairAction.getId());

        //then
        verify(repository).findById(repairAction.getId());
    }

    @Test
    void willThrowWhenIdDoesNotExist() throws Exception {
        //given
        RepairAction repairAction = new RepairAction(
                "remschijf verwisselen",
                50.0
        );


        //when
        given(repository.findById(repairAction.getId())).willReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> underTest.findRepairActionById(repairAction.getId()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Reparatiehandeling  niet gevonden! ID - " + repairAction.getId());
    }

    @Test
    void addNewRepairAction() {
        //given
        RepairAction repairAction = new RepairAction(
                "remschijf verwisselen",
                50.0
        );

        //when
        underTest.addNewRepairAction(repairAction);

        //then
        ArgumentCaptor<RepairAction> repairActionArgumentCaptor = ArgumentCaptor.forClass(RepairAction.class);

        verify(repository).save(repairActionArgumentCaptor.capture());

        RepairAction capturedRepairAction = repairActionArgumentCaptor.getValue();

        assertThat(capturedRepairAction).isEqualTo(repairAction);
    }

    @Test
    void willThrowWhenNameIsTaken() {
        //given
        RepairAction repairAction = new RepairAction(
                "remschijf verwisselen",
                50.0
        );

        given(repository.findRepairActionByName(repairAction.getName())).willReturn(Optional.of(repairAction));

        //when
        //then
        assertThatThrownBy(() -> underTest.addNewRepairAction(repairAction))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("naam in gebruik");

        //the repository (mock) never saves any repairactions
        verify(repository, never()).save(any());
    }

    @Test
    void deleteRepairActionById() {
        //given
        RepairAction repairAction = new RepairAction(
                "remschijf verwisselen",
                50.0
        );

        //when
        given(repository.existsById(repairAction.getId())).willReturn(true);
        underTest.deleteRepairActionById(repairAction.getId());

        //then
        verify(repository).deleteById(repairAction.getId());
    }

    @Test
    void willThrowWhenDeleteFails() {
        //given
        RepairAction repairAction = new RepairAction(
                "remschijf verwisselen",
                50.0
        );

        //when
        given(repository.existsById(repairAction.getId())).willReturn(false);

        //then
        assertThatThrownBy(() -> underTest.deleteRepairActionById(repairAction.getId()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Reparatiehandeling met id " + repairAction.getId() + " bestaat niet");
    }

}