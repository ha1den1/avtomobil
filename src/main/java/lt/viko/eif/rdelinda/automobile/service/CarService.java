package lt.viko.eif.rdelinda.automobile.service;

import lt.viko.eif.rdelinda.automobile.model.Car;
import lt.viko.eif.rdelinda.automobile.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing cars.
 */
@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    /**
     * Retrieves all cars.
     *
     * @return List of all cars.
     */
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    /**
     * Retrieves a car by its ID.
     *
     * @param id The ID of the car to retrieve.
     * @return The car with the specified ID.
     */
    public Car getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Car not found"));
    }

    /**
     * Saves a car.
     *
     * @param car The car to save.
     * @return The saved car.
     */
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    /**
     * Deletes a car by its ID.
     *
     * @param id The ID of the car to delete.
     */
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
