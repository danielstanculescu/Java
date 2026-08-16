package com.amigoscode;

//import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftwareEngineerServiceMongoDB {
/*
    private final SoftwareEngineerMongoRepository softwareEngineerMongoRepository;

    public SoftwareEngineerServiceMongoDB(SoftwareEngineerMongoRepository softwareEngineerMongoRepository) {

        this.softwareEngineerMongoRepository = softwareEngineerMongoRepository;
    }

    public List<SoftwareEngineer> getAllSoftwareEngineers() {
        return softwareEngineerMongoRepository.findAll();
    }

    public void insertSoftwareEngineer(SoftwareEngineer softwareEngineer) {
        softwareEngineerMongoRepository.save(softwareEngineer);
    }

    public SoftwareEngineer getSoftwareEngineerById(Integer id) {
        return softwareEngineerMongoRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(id + "not found"));
    }

    public void updateSoftwareEngineerById(SoftwareEngineer softwareEngineer) {

        if(softwareEngineerMongoRepository.findById(softwareEngineer.getId()).isPresent()){
            SoftwareEngineer se = softwareEngineerMongoRepository.findById(softwareEngineer.getId()).get();
            se.setName(softwareEngineer.getName());
            se.setTechStack(softwareEngineer.getTechStack());
            softwareEngineerMongoRepository.save(se);
        }
    }

    public void deleteSoftwareEngineer(SoftwareEngineer softwareEngineer) {
        softwareEngineerMongoRepository.delete(softwareEngineer);
    }

    public void deleteSoftwareEngineerByIdOld(Integer id) {
        softwareEngineerMongoRepository.deleteById(id);
    }

    public void deleteSoftwareEngineerById(Integer id) {
        if(softwareEngineerMongoRepository.findById(id).isPresent()) {
            SoftwareEngineer se = softwareEngineerMongoRepository.findById(id).get();
            softwareEngineerMongoRepository.delete(se);
        }
    }

 */
}
