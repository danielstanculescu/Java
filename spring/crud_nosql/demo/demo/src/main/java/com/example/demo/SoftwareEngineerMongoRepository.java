package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SoftwareEngineerMongoRepository extends MongoRepository<SoftwareEngineer, Integer> {
}
