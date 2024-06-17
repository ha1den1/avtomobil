package lt.viko.eif.rdelinda.automobile.controller;

import lt.viko.eif.rdelinda.automobile.model.ServiceCenter;
import lt.viko.eif.rdelinda.automobile.service.ServiceCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing service centers.
 */
@RestController
@RequestMapping("/service-centers")
@CrossOrigin(origins = "*")
public class ServiceCenterController {

    @Autowired
    private ServiceCenterService serviceCenterService;

    /**
     * Retrieves all service centers.
     *
     * @return List of all service centers.
     */
    @GetMapping
    public List<ServiceCenter> getAllServiceCenters() {
        return serviceCenterService.getAllServiceCenters();
    }

    /**
     * Retrieves a service center by its ID.
     *
     * @param id The ID of the service center to retrieve.
     * @return The service center with the specified ID.
     */
    @GetMapping("/{id}")
    public ServiceCenter getServiceCenterById(@PathVariable Long id) {
        return serviceCenterService.getServiceCenterById(id);
    }

    /**
     * Adds a new service center.
     *
     * @param serviceCenter The service center to add.
     * @return The added service center.
     */
    @PostMapping
    public ServiceCenter addServiceCenter(@RequestBody ServiceCenter serviceCenter) {
        return serviceCenterService.saveServiceCenter(serviceCenter);
    }

    /**
     * Deletes a service center by its ID.
     *
     * @param id The ID of the service center to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteServiceCenter(@PathVariable Long id) {
        serviceCenterService.deleteServiceCenter(id);
    }
}
