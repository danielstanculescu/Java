package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FullStackEngineerServicePostgreSQL {

    private final FullStackEngineerPostgreSQLRepository fullStackEngineerPostgreSQLRepository;

    public FullStackEngineerServicePostgreSQL(FullStackEngineerPostgreSQLRepository fullStackEngineerPostgreSQLRepository) {

        this.fullStackEngineerPostgreSQLRepository = fullStackEngineerPostgreSQLRepository;
    }

    public List<FullStackEngineer> getAllFullStackEngineers() {
        return fullStackEngineerPostgreSQLRepository.findAll();
    }

    public void insertFullStackEngineer(FullStackEngineer fullStackEngineer) {
        fullStackEngineerPostgreSQLRepository.save(fullStackEngineer);
    }

    public FullStackEngineer getFullStackEngineerById(Integer id) {
        return fullStackEngineerPostgreSQLRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(id + "not found"));
    }

    public void updateFullStackEngineerById(FullStackEngineer fullStackEngineer) {

        if(fullStackEngineerPostgreSQLRepository.findById(fullStackEngineer.getId()).isPresent()){
            FullStackEngineer fullStackEngineerToSave = fullStackEngineerPostgreSQLRepository.findById(fullStackEngineer.getId()).get();
            fullStackEngineerToSave.setName(fullStackEngineer.getName());
            fullStackEngineerToSave.setTechStack(fullStackEngineer.getTechStack());
            fullStackEngineerPostgreSQLRepository.save(fullStackEngineerToSave);
        }
    }

    public void deleteFullStackEngineer(FullStackEngineer fullStackEngineer) {
        fullStackEngineerPostgreSQLRepository.delete(fullStackEngineer);
    }

    public void deleteFullStackEngineerById(Integer id) {
        if(fullStackEngineerPostgreSQLRepository.findById(id).isPresent()) {
            FullStackEngineer fullStackEngineer = fullStackEngineerPostgreSQLRepository.findById(id).get();
            fullStackEngineerPostgreSQLRepository.delete(fullStackEngineer);
        }
    }
}
