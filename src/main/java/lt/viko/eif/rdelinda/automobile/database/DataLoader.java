package lt.viko.eif.rdelinda.automobile.database;

import lt.viko.eif.rdelinda.automobile.model.Car;
import lt.viko.eif.rdelinda.automobile.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CarRepository carRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create car entries
        carRepository.save(new Car("Toyota", "Camry", 2022));
        carRepository.save(new Car("Honda", "Civic", 2023));
        carRepository.save(new Car("Ford", "Mustang", 2021));
        carRepository.save(new Car("Pieskomobilis", "Justinas", 2003));
        carRepository.save(new Car("BMW", "E46", 2002));
        carRepository.save(new Car("BMW", "E92", 2007));
        carRepository.save(new Car("Opel", "Astra", 2009));
    }
}