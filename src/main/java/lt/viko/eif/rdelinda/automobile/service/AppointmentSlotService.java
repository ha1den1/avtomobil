package lt.viko.eif.rdelinda.automobile.service;

import lt.viko.eif.rdelinda.automobile.model.AppointmentSlot;
import lt.viko.eif.rdelinda.automobile.repository.AppointmentSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentSlotService {

    @Autowired
    private AppointmentSlotRepository appointmentSlotRepository;

    public List<AppointmentSlot> getAllSlots() {
        return appointmentSlotRepository.findAll();
    }

    public List<AppointmentSlot> getAvailableSlots(String date) {
        return appointmentSlotRepository.findBySlotDateAndBookedFalse(LocalDate.parse(date));
    }

    public AppointmentSlot saveAppointmentSlot(AppointmentSlot slot) {
        return appointmentSlotRepository.save(slot);
    }

    public List<AppointmentSlot> getSlotsByDate(LocalDate date) {
        return appointmentSlotRepository.findBySlotDate(date);
    }
}
