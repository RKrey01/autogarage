package nl.eindopdracht.autogarage.service;

import nl.eindopdracht.autogarage.model.Car;
import nl.eindopdracht.autogarage.repository.CarRepository;
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
class CarServiceImplTest {

    @Mock
    private CarRepository repository;
    private CarServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new CarServiceImpl(repository);
    }

    @Test
    void canAddNewCar() {
        //given
        Car car = new Car(
                "AB-123-X",
                "Audi",
                "Q5",
                2018
        );

        //when
        underTest.addNewCar(car);

        //then
        ArgumentCaptor<Car> carArgumentCaptor = ArgumentCaptor.forClass(Car.class);

        verify(repository).save(carArgumentCaptor.capture());

        Car capturedCar = carArgumentCaptor.getValue();

        assertThat(capturedCar).isEqualTo(car);
    }

    @Test
    void willThrowWhenLicensePlateIsTaken() {
        //given
        Car car = new Car(
                "AB-123-X",
                "Audi",
                "Q5",
                2018
        );

        given(repository.findCarByLicensePlate(car.getLicensePlate())).willReturn(Optional.of(car));

        //when
        //then
        assertThatThrownBy(() -> underTest.addNewCar(car))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Kenteken in gebruik.");

        //the repository (mock) never saves any cars
        verify(repository, never()).save(any());

    }

    @Test
    void canGetAllCars() {
        //when
        underTest.getCars();
        //then
        verify(repository).findAll();
    }

    @Test
    void willFindCarById() {
        //given
        Car car = new Car(
                "AB-123-X",
                "Audi",
                "Q5",
                2018
        );

        //when
        given(repository.findById(car.getId())).willReturn(Optional.of(car));
        underTest.findCarById(car.getId());

        //then
        verify(repository).findById(car.getId());
    }

    @Test
    void willThrowWhenIdDoesNotExist() throws Exception {
        //given
        Car car = new Car(
                "AB-123-X",
                "Audi",
                "Q5",
                2018
        );


        //when
        given(repository.findById(car.getId())).willReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> underTest.findCarById(car.getId()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Auto niet gevonden! ID - " + car.getId());
    }

    @Test
    void deleteCarById() {
        //given
        Car car = new Car(
                "AB-123-X",
                "Audi",
                "Q5",
                2018
        );

        //when
        given(repository.existsById(car.getId())).willReturn(true);
        underTest.deleteCarById(car.getId());

        //then
        verify(repository).deleteById(car.getId());
    }

    @Test
    void willThrowWhenDeleteFails() {
        //given
        Car car = new Car(
                "AB-123-X",
                "Audi",
                "Q5",
                2018
        );

        //when
        given(repository.existsById(car.getId())).willReturn(false);

        //then
        assertThatThrownBy(() -> underTest.deleteCarById(car.getId()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Auto met id " + car.getId() + " bestaat niet" );
    }
}