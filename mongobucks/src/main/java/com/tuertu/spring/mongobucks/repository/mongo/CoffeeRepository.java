package com.tuertu.spring.mongobucks.repository.mongo;

import com.tuertu.spring.mongobucks.entity.CoffeeMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CoffeeRepository extends MongoRepository<CoffeeMongoEntity, String> {
}
