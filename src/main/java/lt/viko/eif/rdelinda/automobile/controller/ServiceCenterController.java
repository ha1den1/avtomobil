package lt.viko.eif.rdelinda.automobile.controller;

import lt.viko.eif.rdelinda.automobile.model.ServiceCenter;
import lt.viko.eif.rdelinda.automobile.service.ServiceCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-centers")
@CrossOrigin(origins = "*")
public class ServiceCenterController {

    @Autowired
    private ServiceCenterService serviceCenterService;

    @GetMapping
    public List<ServiceCenter> getAllServiceCenters() {
        return serviceCenterService.getAllServiceCenters();
    }

    @GetMapping("/{id}")
    public ServiceCenter getServiceCenterById(@PathVariable Long id) {
        return serviceCenterService.getServiceCenterById(id);
    }

    @PostMapping
    public ServiceCenter addServiceCenter(@RequestBody ServiceCenter serviceCenter) {
        return serviceCenterService.saveServiceCenter(serviceCenter);
    }

    @DeleteMapping("/{id}")
    public void deleteServiceCenter(@PathVariable Long id) {
        serviceCenterService.deleteServiceCenter(id);
    }
}
