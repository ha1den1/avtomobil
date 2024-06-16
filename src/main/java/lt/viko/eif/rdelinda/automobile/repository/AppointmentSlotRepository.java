package lt.viko.eif.rdelinda.automobile.repository;

import lt.viko.eif.rdelinda.automobile.model.AppointmentSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentSlotRepository extends JpaRepository<AppointmentSlot, Long> {
    List<AppointmentSlot> findBySlotDate(LocalDate slotDate);

    List<AppointmentSlot> findBySlotDateAndBookedFalse(LocalDate slotDate);
}
