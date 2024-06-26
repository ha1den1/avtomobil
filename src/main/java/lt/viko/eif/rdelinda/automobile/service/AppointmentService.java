package lt.viko.eif.rdelinda.automobile.service;

import lt.viko.eif.rdelinda.automobile.model.Appointment;
import lt.viko.eif.rdelinda.automobile.repository.AppointmentRepository;
import lt.viko.eif.rdelinda.automobile.repository.ServiceCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing appointments.
 */
@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ServiceCenterRepository serviceCenterRepository;

    /**
     * Retrieves all appointments.
     *
     * @return List of all appointments.
     */
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    /**
     * Retrieves an appointment by its ID.
     *
     * @param id The ID of the appointment to retrieve.
     * @return The appointment with the specified ID.
     */
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));
    }

    /**
     * Saves an appointment.
     *
     * @param appointment The appointment to save.
     * @return The saved appointment.
     */
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    /**
     * Deletes an appointment by its ID.
     *
     * @param id The ID of the appointment to delete.
     */
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
