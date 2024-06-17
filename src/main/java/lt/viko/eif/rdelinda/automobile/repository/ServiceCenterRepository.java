package lt.viko.eif.rdelinda.automobile.repository;

import lt.viko.eif.rdelinda.automobile.model.ServiceCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing service centers.
 */
@Repository
public interface ServiceCenterRepository extends JpaRepository<ServiceCenter, Long> {
}
