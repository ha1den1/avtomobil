package lt.viko.eif.rdelinda.automobile.controller;

import lt.viko.eif.rdelinda.automobile.model.Car;
import lt.viko.eif.rdelinda.automobile.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing cars.
 */
@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    /**
     * Retrieves all cars.
     *
     * @return List of all cars.
     */
    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    /**
     * Retrieves a car by its ID.
     *
     * @param id The ID of the car to retrieve.
     * @return The car with the specified ID.
     */
    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    /**
     * Adds a new car.
     *
     * @param car The car to add.
     * @return The added car.
     */
    @PostMapping
    public Car addCar(@RequestBody Car car) {
        return carService.saveCar(car);
    }

    /**
     * Deletes a car by its ID.
     *
     * @param id The ID of the car to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}
