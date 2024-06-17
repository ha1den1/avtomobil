import lt.viko.eif.rdelinda.automobile.model.Appointment;
import lt.viko.eif.rdelinda.automobile.repository.AppointmentRepository;
import lt.viko.eif.rdelinda.automobile.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AppointmentServiceTests {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    public AppointmentServiceTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAppointments() {
        // Test logic
    }

    @Test
    public void testGetAppointmentById() {
        // Test logic
    }

    @Test
    public void testSaveAppointment() {
        // Test logic
    }

    @Test
    public void testDeleteAppointment() {
        // Test logic
    }
}
