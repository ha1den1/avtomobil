package lt.viko.eif.rdelinda.automobile.repository;

import lt.viko.eif.rdelinda.automobile.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing cars.
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
