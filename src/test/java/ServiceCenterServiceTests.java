import lt.viko.eif.rdelinda.automobile.model.ServiceCenter;
import lt.viko.eif.rdelinda.automobile.repository.ServiceCenterRepository;
import lt.viko.eif.rdelinda.automobile.service.ServiceCenterService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ServiceCenterServiceTests {

    @Mock
    private ServiceCenterRepository serviceCenterRepository;

    @InjectMocks
    private ServiceCenterService serviceCenterService;

    public ServiceCenterServiceTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllServiceCenters() {

        ServiceCenter serviceCenter1 = new ServiceCenter("Test Center 1", "Test Address 1", "123456789");
        ServiceCenter serviceCenter2 = new ServiceCenter("Test Center 2", "Test Address 2", "987654321");
        List<ServiceCenter> serviceCenters = Arrays.asList(serviceCenter1, serviceCenter2);


        when(serviceCenterRepository.findAll()).thenReturn(serviceCenters);


        List<ServiceCenter> returnedServiceCenters = serviceCenterService.getAllServiceCenters();


        assertEquals(2, returnedServiceCenters.size());
    }

    @Test
    public void testGetServiceCenterById() {

        ServiceCenter serviceCenter = new ServiceCenter("Test Center", "Test Address", "123456789");


        when(serviceCenterRepository.findById(1L)).thenReturn(Optional.of(serviceCenter));


        ServiceCenter returnedServiceCenter = serviceCenterService.getServiceCenterById(1L);


        assertEquals("Test Center", returnedServiceCenter.getName());
    }

    @Test
    public void testSaveServiceCenter() {

        ServiceCenter newServiceCenter = new ServiceCenter("New Center", "New Address", "555555555");


        when(serviceCenterRepository.save(any(ServiceCenter.class))).thenReturn(newServiceCenter);


        ServiceCenter savedServiceCenter = serviceCenterService.saveServiceCenter(newServiceCenter);


        assertEquals("New Center", savedServiceCenter.getName());
    }

    @Test
    public void testDeleteServiceCenter() {

        doNothing().when(serviceCenterRepository).deleteById(1L);


        serviceCenterService.deleteServiceCenter(1L);


        verify(serviceCenterRepository, times(1)).deleteById(1L);
    }
}
