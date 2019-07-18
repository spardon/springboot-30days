package com.tuertu.spring.mongobucks.service;

import com.tuertu.spring.mongobucks.convert.MoneyReadMongoConverter;
import com.tuertu.spring.mongobucks.entity.CoffeeMongoEntity;
import com.tuertu.spring.mongobucks.repository.mongo.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;


@Service
@Slf4j
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    public CoffeeMongoEntity save(CoffeeMongoEntity coffee){
        coffee = coffeeRepository.save(coffee);
        return coffee;
    }

    public CoffeeMongoEntity createCoffee(String name, Double price){
        CoffeeMongoEntity coffee = CoffeeMongoEntity.builder().name(name)
                .price(Money.of(CurrencyUnit.of("CNY"), price)).build();
        coffee = this.save(coffee);
        return coffee;
    }

    public Optional<CoffeeMongoEntity> findOneCoffee(String name){
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", exact().ignoreCase());
        Optional<CoffeeMongoEntity> coffee = coffeeRepository.findOne(
                Example.of(CoffeeMongoEntity.builder().name(name).build(), matcher)
        );

        log.info("Coffee Found: {}", coffee);
        return coffee;
    }

    public void delete(CoffeeMongoEntity coffee){
        coffeeRepository.delete(coffee);
    }

    public List<CoffeeMongoEntity> findAllCoffeeById(List<String> ids){
        Iterable<CoffeeMongoEntity> coffees = coffeeRepository.findAllById(ids);
        List<CoffeeMongoEntity> coffeeList = new ArrayList<>();
        coffees.forEach(coffeeList::add);
        return coffeeList;
    }

    public List<CoffeeMongoEntity> findAllCoffee(){
        return coffeeRepository.findAll();
    }

    public Boolean deleteById(String id) {
        if (coffeeRepository.existsById(id)) {
            try {
                coffeeRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                log.error(e.getMessage());
                return false;
            }
        } else {
            return false;
        }
    }

}
