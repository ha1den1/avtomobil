package lt.viko.eif.rdelinda.automobile.database;

import lt.viko.eif.rdelinda.automobile.model.AppointmentSlot;
import lt.viko.eif.rdelinda.automobile.model.Car;
import lt.viko.eif.rdelinda.automobile.repository.AppointmentSlotRepository;
import lt.viko.eif.rdelinda.automobile.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private AppointmentSlotRepository appointmentSlotRepository;

    @Value("${default.appointment.start.hour}")
    private int defaultAppointmentStartHour;

    @Value("${default.appointment.end.hour}")
    private int defaultAppointmentEndHour;

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

        // Specify the start and end dates for appointment slots
        LocalDate startDate = LocalDate.of(2024, 6, 20); // Change this to your desired start date
        LocalDate endDate = LocalDate.of(2024, 6, 25);   // Change this to your desired end date

        // Iterate over each date and create appointment slots if they don't already exist
        LocalDate date = startDate;
        while (!date.isAfter(endDate)) {
            createDefaultAppointmentSlots(date);
            date = date.plusDays(1); // Move to the next day
        }
    }

    private void createDefaultAppointmentSlots(LocalDate date) {
        // Check if appointment slots already exist for the given date
        boolean slotsExistForDate = appointmentSlotRepository.findBySlotDate(date).size() > 0;

        // If appointment slots do not exist for the date, create default slots with configurable times
        if (!slotsExistForDate) {
            createAppointmentSlots(date);
        }
    }

    private void createAppointmentSlots(LocalDate date) {
        // Create appointment slots with configurable start and end hours for the given date
        LocalTime startTime = LocalTime.of(defaultAppointmentStartHour, 0);
        LocalTime endTime = LocalTime.of(defaultAppointmentEndHour, 0);

        LocalDateTime startDateTime = LocalDateTime.of(date, startTime);
        LocalDateTime endDateTime = LocalDateTime.of(date, endTime);

        // Create appointment slots every hour from startTime to endTime
        while (startDateTime.isBefore(endDateTime)) {
            appointmentSlotRepository.save(new AppointmentSlot(startDateTime, startDateTime.plusHours(1), false, date));
            startDateTime = startDateTime.plusHours(1);
        }
    }
}
