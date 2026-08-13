package com.amigoscode;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/software-engineers")
public class SoftwareEngineerController {

    private final SoftwareEngineerService softwareEngineerService;

    public SoftwareEngineerController(SoftwareEngineerService softwareEngineerService){
        this.softwareEngineerService = softwareEngineerService;
    }

    // use DTO = data transform objects
    @GetMapping
    public List<SoftwareEngineer> getEngineers() {
        return softwareEngineerService.getAllSoftwareEngineers();
    }

    @GetMapping("{id}")
    public SoftwareEngineer getEngineersById(@PathVariable Integer id) {
        return softwareEngineerService.getSoftwareEngineerById(id);
    }

    @PostMapping
    public void addNewSoftwareEngineer(@RequestBody SoftwareEngineer softwareEngineer){
        softwareEngineerService.insertSoftwareEngineer(softwareEngineer);
    }

    @PutMapping
    public void updateSoftwareEngineer(@RequestBody SoftwareEngineer softwareEngineer){
        softwareEngineerService.updateSoftwareEngineerById(softwareEngineer);
    }

    @DeleteMapping
    public void deleteSoftwareEngineer(@RequestBody SoftwareEngineer softwareEngineer){
        softwareEngineerService.deleteSoftwareEngineer(softwareEngineer);
    }

    @DeleteMapping("{id}")
    public void deleteSoftwareEngineerById(@PathVariable Integer id){
        softwareEngineerService.deleteSoftwareEngineerById(id);
    }
}
