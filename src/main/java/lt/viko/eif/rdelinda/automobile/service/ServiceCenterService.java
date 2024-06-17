package lt.viko.eif.rdelinda.automobile.service;

import lt.viko.eif.rdelinda.automobile.model.ServiceCenter;
import lt.viko.eif.rdelinda.automobile.repository.ServiceCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCenterService {

    @Autowired
    private ServiceCenterRepository serviceCenterRepository;

    public List<ServiceCenter> getAllServiceCenters() {
        return serviceCenterRepository.findAll();
    }

    public ServiceCenter getServiceCenterById(Long id) {
        return serviceCenterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Service Center not found"));
    }

    public ServiceCenter saveServiceCenter(ServiceCenter serviceCenter) {
        return serviceCenterRepository.save(serviceCenter);
    }

    public void deleteServiceCenter(Long id) {
        serviceCenterRepository.deleteById(id);
    }
}
