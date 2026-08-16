package com.amigoscode;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/fullstack-engineers/postgreSQL")
public class FullStackEngineerControllerPostgreSQL {

    private final FullStackEngineerServicePostgreSQL fullStackEngineerServicePostgreSQL;

    public FullStackEngineerControllerPostgreSQL(FullStackEngineerServicePostgreSQL fullStackEngineerServicePostgreSQL){
        this.fullStackEngineerServicePostgreSQL = fullStackEngineerServicePostgreSQL;
    }

    // use DTO = data transform objects

    @GetMapping
    public List<FullStackEngineer> getEngineers() {
        return fullStackEngineerServicePostgreSQL.getAllFullStackEngineers();
    }

    @GetMapping("{id}")
    public FullStackEngineer getEngineersById(@PathVariable Integer id) {
        return fullStackEngineerServicePostgreSQL.getFullStackEngineerById(id);
    }

    @PostMapping
    public void addNewSoftwareEngineer(@RequestBody FullStackEngineer fullStackEngineer){
        fullStackEngineerServicePostgreSQL.insertFullStackEngineer(fullStackEngineer);
    }

    @PutMapping
    public void updateFullStackEngineer(@RequestBody FullStackEngineer fullStackEngineer){
        fullStackEngineerServicePostgreSQL.updateFullStackEngineerById(fullStackEngineer);
    }

    @DeleteMapping
    public void deleteFullstackEngineer(@RequestBody FullStackEngineer fullStackEngineer){
        fullStackEngineerServicePostgreSQL.deleteFullStackEngineer(fullStackEngineer);
    }

    @DeleteMapping("{id}")
    public void deleteFullStackEngineerById(@PathVariable Integer id){
        fullStackEngineerServicePostgreSQL.deleteFullStackEngineerById(id);
    }
}
