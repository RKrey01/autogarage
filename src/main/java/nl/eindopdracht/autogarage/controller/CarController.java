package nl.eindopdracht.autogarage.controller;

import nl.eindopdracht.autogarage.model.Car;
import nl.eindopdracht.autogarage.service.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/car")
public class CarController {
    private final CarServiceImpl carService;

    @Autowired
    public CarController(CarServiceImpl carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getCars();
    }

    @GetMapping(path = "{carId}")
    public Car getCarById(@PathVariable("carId") Long carId) {
        return carService.findCarById(carId);
    }

    @PostMapping
    public void registerNewCar(@RequestBody Car car) {
        carService.addNewCar(car);
    }

    @DeleteMapping(path = "{carId}")
    public void deleteCarById(@PathVariable("carId") Long carId) {
        carService.deleteCarById(carId);
    }


}
