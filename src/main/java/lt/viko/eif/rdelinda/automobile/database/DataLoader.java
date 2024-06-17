package lt.viko.eif.rdelinda.automobile.database;

import lt.viko.eif.rdelinda.automobile.model.Car;
import lt.viko.eif.rdelinda.automobile.model.ServiceCenter;
import lt.viko.eif.rdelinda.automobile.repository.CarRepository;
import lt.viko.eif.rdelinda.automobile.repository.ServiceCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Component to preload data into the database on application startup.
 */
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ServiceCenterRepository serviceCenterRepository;

    /**
     * Preloads data into the database.
     *
     * @param args Command line arguments.
     * @throws Exception If an error occurs during data loading.
     */
    @Override
    public void run(String... args) throws Exception {

        carRepository.save(new Car("Toyota", "Camry", 2022));
        carRepository.save(new Car("Honda", "Civic", 2023));
        carRepository.save(new Car("Ford", "Mustang", 2021));
        carRepository.save(new Car("Pieskomobilis", "Justinas", 2003));
        carRepository.save(new Car("BMW", "E46", 2002));
        carRepository.save(new Car("BMW", "E92", 2007));
        carRepository.save(new Car("Opel", "Astra", 2009));


        serviceCenterRepository.save(new ServiceCenter("Tire Change", "Žvalgų g. 51, Vilnius", "069829311"));
        serviceCenterRepository.save(new ServiceCenter("Autoresvice", "Verkių g. 52, Vilnius", "067756523"));
        serviceCenterRepository.save(new ServiceCenter("Stop-Servis", "J. Kazlausko g. 1, Vilnius", "567-890-1234"));
    }
}
