package lt.viko.eif.rdelinda.automobile.controller;

import lt.viko.eif.rdelinda.automobile.model.Appointment;
import lt.viko.eif.rdelinda.automobile.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing appointments.
 */
@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    /**
     * Retrieves all appointments.
     *
     * @return List of all appointments.
     */
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    /**
     * Retrieves an appointment by its ID.
     *
     * @param id The ID of the appointment to retrieve.
     * @return The appointment with the specified ID.
     */
    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    /**
     * Adds a new appointment.
     *
     * @param appointment The appointment to add.
     * @return The added appointment.
     */
    @PostMapping
    public Appointment addAppointment(@RequestBody Appointment appointment) {
        return appointmentService.saveAppointment(appointment);
    }

    /**
     * Deletes an appointment by its ID.
     *
     * @param id The ID of the appointment to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }
}
