package com.tuertu.spring.mongobucks.service;

import com.tuertu.spring.mongobucks.entity.CoffeeMongoEntity;
import com.tuertu.spring.mongobucks.entity.CoffeeOrderMongoEntity;
import com.tuertu.spring.mongobucks.entity.OrderState;
import com.tuertu.spring.mongobucks.repository.mongo.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Service
public class CoffeeOrderService {
    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;

    public CoffeeOrderMongoEntity saveOrder(CoffeeOrderMongoEntity coffeeOrder){
        CoffeeOrderMongoEntity order = coffeeOrderRepository.save(coffeeOrder);
        return order;
    }

    public CoffeeOrderMongoEntity createOrder(String name, CoffeeMongoEntity...coffees){
        CoffeeOrderMongoEntity coffeeOrder = CoffeeOrderMongoEntity.builder().customer(name)
                .items(new ArrayList<>(Arrays.asList(coffees)))
                .state(OrderState.INIT)
                .build();
        coffeeOrder = this.saveOrder(coffeeOrder);
        return coffeeOrder;
    }

    public Optional<CoffeeOrderMongoEntity> findOrderByCustomerName(String name){
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher(
                "name", ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase()
        );

        Optional<CoffeeOrderMongoEntity> coffeeOrder = coffeeOrderRepository.findOne(
                Example.of(CoffeeOrderMongoEntity.builder().customer(name).build(), matcher)
        );
        log.info("Find order: {}", coffeeOrder);
        return coffeeOrder;
    }

    public Boolean updateState(CoffeeOrderMongoEntity order, OrderState state){
        if (state.compareTo(order.getState()) < 0){
            log.warn("Wrong State order: {}, {}", state, order.getState());
            return false;
        }
        order.setState(state);
        this.saveOrder(order);
        log.info("Updated Order: {}", order);
        return true;
    }
}
