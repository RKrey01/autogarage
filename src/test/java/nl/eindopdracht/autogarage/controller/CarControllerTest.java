package nl.eindopdracht.autogarage.controller;

import nl.eindopdracht.autogarage.model.Car;
import nl.eindopdracht.autogarage.repository.CarRepository;
import nl.eindopdracht.autogarage.service.CarServiceImpl;
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

@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarServiceImpl service;

    @Mock
    private CarRepository repository;

    @Test
    void shouldGetAllCars() throws Exception {
        List<Car> cars = repository.findAll();
        when(service.getCars())
                .thenReturn(cars);

        mvc.perform(get("/api/car")).andDo(print()).andExpect(status().isOk());

        //to know that the mock is being called
        verify(service).getCars();
    }

    @Test
    void shouldGetCarById() throws Exception {
        Car car = new Car(
                1L,
                "AB-123-X",
                "Audi",
                "Q5",
                2018
        );

        when(service.findCarById(car.getId()))
                .thenReturn(car);

        mvc.perform(get("/api/car/{carId}", car.getId())).andDo(print()).andExpect(status().isOk());

        verify(service).findCarById(car.getId());
    }

    /*@Test
    void registerNewCar() {
    }*/

    @Test
    void shouldDeleteCarById() throws Exception {
        Car car = new Car(
                1L,
                "AB-123-X",
                "Audi",
                "Q5",
                2018
        );

        mvc.perform(delete("/api/car/{carId}", car.getId())).andExpect(status().isOk());

        verify(service).deleteCarById(car.getId());
    }
}