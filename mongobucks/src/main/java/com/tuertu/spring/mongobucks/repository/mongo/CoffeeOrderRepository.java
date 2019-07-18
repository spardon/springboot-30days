package com.tuertu.spring.mongobucks.repository.mongo;

import com.tuertu.spring.mongobucks.entity.CoffeeOrderMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CoffeeOrderRepository extends MongoRepository<CoffeeOrderMongoEntity, String> {
}
