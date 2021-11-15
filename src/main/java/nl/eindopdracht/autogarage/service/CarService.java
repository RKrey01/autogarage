package nl.eindopdracht.autogarage.service;

import nl.eindopdracht.autogarage.model.Car;

import java.util.List;

public interface CarService {

    void addNewCar(Car car);

    List<Car> getCars();

    Car findCarById(Long carId);

    void deleteCarById(Long carId);

}
