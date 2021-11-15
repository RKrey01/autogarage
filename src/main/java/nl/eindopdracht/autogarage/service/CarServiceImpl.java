package nl.eindopdracht.autogarage.service;

import nl.eindopdracht.autogarage.model.Car;
import nl.eindopdracht.autogarage.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void addNewCar(Car car) {
        Optional<Car> carOptional = carRepository.findCarByLicensePlate(car.getLicensePlate());
        if (carOptional.isPresent()) {
            throw new IllegalStateException("kenteken in gebruik.");
        }
        carRepository.save(car);
    }

    @Override
    public List<Car> getCars(){
        return carRepository.findAll();
    }

    @Override
    public Car findCarById(Long carId){
        Optional<Car> result = carRepository.findById(carId);

        Car car = null;
        if (result.isPresent()){
            car = result.get();
        } else {
            throw new RuntimeException("Auto niet gevonden! ID - " + carId);
        }

        return car;
    }

    @Override
    public void deleteCarById(Long carId) {
        boolean exists = carRepository.existsById(carId);
        if (!exists) {
            throw new IllegalStateException("Klant met id " + carId + " bestaat niet");
        }
        carRepository.deleteById(carId);
    }

}
