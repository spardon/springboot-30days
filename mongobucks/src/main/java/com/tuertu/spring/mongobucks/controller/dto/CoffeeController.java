package com.tuertu.spring.mongobucks.controller.dto;

import com.tuertu.spring.mongobucks.entity.CoffeeMongoEntity;
import com.tuertu.spring.mongobucks.service.CoffeeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class CoffeeController {
    @Resource
    private CoffeeService coffeeServiceImpl;

    @RequestMapping(value = "/api/coffees", method = RequestMethod.GET)
    public List<CoffeeMongoEntity> getCoffeeByName(HttpServletRequest request){
        Optional<String> name = Optional.ofNullable(request.getParameter("name"));

        List<CoffeeMongoEntity> coffees = new ArrayList<>();

        if (name.isPresent()){
            Optional<CoffeeMongoEntity> coffee = coffeeServiceImpl.findOneCoffee(name.get());

            if (coffee.isPresent()){
                coffees.add(coffee.get());
            }

        } else {
            coffees = coffeeServiceImpl.findAllCoffee();
        }
        return coffees;
    }

    @RequestMapping(value = "/api/coffee", method = RequestMethod.POST)
    public CoffeeMongoEntity createCoffee(HttpServletRequest request){
        String name = request.getParameter("name");
        Double price = Double.parseDouble(request.getParameter("price"));
        CoffeeMongoEntity coffee = coffeeServiceImpl.createCoffee(name, price);
        return coffee;
    }

    @DeleteMapping(value = "/api/coffee")
    public Boolean deleteCoffeeById(HttpServletRequest request){
        String id = request.getParameter("id");
        return coffeeServiceImpl.deleteById(id);
    }
}
