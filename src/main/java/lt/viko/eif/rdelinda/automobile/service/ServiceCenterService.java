package lt.viko.eif.rdelinda.automobile.service;

import lt.viko.eif.rdelinda.automobile.model.ServiceCenter;
import lt.viko.eif.rdelinda.automobile.repository.ServiceCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing service centers.
 */
@Service
public class ServiceCenterService {

    @Autowired
    private ServiceCenterRepository serviceCenterRepository;

    /**
     * Retrieves all service centers.
     *
     * @return List of all service centers.
     */
    public List<ServiceCenter> getAllServiceCenters() {
        return serviceCenterRepository.findAll();
    }

    /**
     * Retrieves a service center by its ID.
     *
     * @param id The ID of the service center to retrieve.
     * @return The service center with the specified ID.
     */
    public ServiceCenter getServiceCenterById(Long id) {
        return serviceCenterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Service Center not found"));
    }

    /**
     * Saves a service center.
     *
     * @param serviceCenter The service center to save.
     * @return The saved service center.
     */
    public ServiceCenter saveServiceCenter(ServiceCenter serviceCenter) {
        return serviceCenterRepository.save(serviceCenter);
    }

    /**
     * Deletes a service center by its ID.
     *
     * @param id The ID of the service center to delete.
     */
    public void deleteServiceCenter(Long id) {
        serviceCenterRepository.deleteById(id);
    }
}
