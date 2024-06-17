package lt.viko.eif.rdelinda.automobile.repository;

import lt.viko.eif.rdelinda.automobile.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing appointments.
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
