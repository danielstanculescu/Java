package com.amigoscode;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftwareEngineerService {

    private final SoftwareEngineerRepository softwareEngineerRepository;

    public SoftwareEngineerService(SoftwareEngineerRepository softwareEngineerRepository) {
        this.softwareEngineerRepository = softwareEngineerRepository;
    }

    public List<SoftwareEngineer> getAllSoftwareEngineers() {
        return softwareEngineerRepository.findAll();
    }

    public void insertSoftwareEngineer(SoftwareEngineer softwareEngineer) {
        softwareEngineerRepository.save(softwareEngineer);
    }

    public SoftwareEngineer getSoftwareEngineerById(Integer id) {
        return softwareEngineerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(id + "not found"));
    }

    public void updateSoftwareEngineerById(SoftwareEngineer softwareEngineer) {

        if(softwareEngineerRepository.findById(softwareEngineer.getId()).isPresent()){
            SoftwareEngineer se = softwareEngineerRepository.findById(softwareEngineer.getId()).get();
            se.setName(softwareEngineer.getName());
            se.setTechStack(softwareEngineer.getTechStack());
            softwareEngineerRepository.save(se);
        }

    }

    public void deleteSoftwareEngineer(SoftwareEngineer softwareEngineer) {
        softwareEngineerRepository.delete(softwareEngineer);
    }

    public void deleteSoftwareEngineerById(Integer id) {
        if(softwareEngineerRepository.findById(id).isPresent()) {
            SoftwareEngineer se = softwareEngineerRepository.findById(id).get();
            softwareEngineerRepository.delete(se);
        }
    }
}
