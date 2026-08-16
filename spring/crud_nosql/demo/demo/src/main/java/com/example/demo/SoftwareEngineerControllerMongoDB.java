package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/software-engineers/mongo")
public class SoftwareEngineerControllerMongoDB {

    @Autowired
    private final SoftwareEngineerServiceMongoDB softwareEngineerServiceMongoDB;
    public SoftwareEngineerControllerMongoDB(SoftwareEngineerServiceMongoDB softwareEngineerServiceMongoDB){
        this.softwareEngineerServiceMongoDB = softwareEngineerServiceMongoDB;
    }

    // use DTO = data transform objects

    @GetMapping
    public List<SoftwareEngineer> getEngineers() {
        return softwareEngineerServiceMongoDB.getAllSoftwareEngineers();
    }

    @GetMapping("{id}")
    public SoftwareEngineer getEngineersById(@PathVariable Integer id) {
        return softwareEngineerServiceMongoDB.getSoftwareEngineerById(id);
    }

    @PostMapping
    public void addNewSoftwareEngineer(@RequestBody SoftwareEngineer softwareEngineer){
        softwareEngineerServiceMongoDB.insertSoftwareEngineer(softwareEngineer);
    }

    @PutMapping
    public void updateSoftwareEngineer(@RequestBody SoftwareEngineer softwareEngineer){
        softwareEngineerServiceMongoDB.updateSoftwareEngineerById(softwareEngineer);
    }

    @DeleteMapping
    public void deleteSoftwareEngineer(@RequestBody SoftwareEngineer softwareEngineer){
        softwareEngineerServiceMongoDB.deleteSoftwareEngineer(softwareEngineer);
    }

    @DeleteMapping("{id}")
    public void deleteSoftwareEngineerById(@PathVariable Integer id){
        softwareEngineerServiceMongoDB.deleteSoftwareEngineerById(id);
    }
}
