package lt.viko.eif.rdelinda.automobile.controller;

import lt.viko.eif.rdelinda.automobile.model.Appointment;
import lt.viko.eif.rdelinda.automobile.model.AppointmentSlot;
import lt.viko.eif.rdelinda.automobile.service.AppointmentService;
import lt.viko.eif.rdelinda.automobile.service.AppointmentSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AppointmentSlotService appointmentSlotService;

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @PostMapping
    public Appointment addAppointment(@RequestBody Appointment appointment) {
        AppointmentSlot slot = appointment.getAppointmentSlot();
        slot.setBooked(true);
        appointmentSlotService.saveAppointmentSlot(slot);
        return appointmentService.saveAppointment(appointment);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }

    @GetMapping("/slots")
    public List<AppointmentSlot> getAllSlots() {
        return appointmentSlotService.getAllSlots();
    }

    @GetMapping("/availableSlots")
    public List<AppointmentSlot> getAvailableSlots(@RequestParam("date") String date) {
        return appointmentSlotService.getAvailableSlots(date);
    }
}
