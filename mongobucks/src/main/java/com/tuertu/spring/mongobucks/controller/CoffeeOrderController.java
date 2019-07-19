package com.tuertu.spring.mongobucks.controller;

import com.tuertu.spring.mongobucks.controller.dto.CoffeOrderUpdateInfo;
import com.tuertu.spring.mongobucks.entity.CoffeeMongoEntity;
import com.tuertu.spring.mongobucks.entity.CoffeeOrderMongoEntity;
import com.tuertu.spring.mongobucks.service.CoffeeOrderService;
import com.tuertu.spring.mongobucks.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class CoffeeOrderController {
    @Resource
    private CoffeeOrderService coffeeOrderServiceImpl;

    @Resource
    private CoffeeService coffeeService;

    @RequestMapping(value = "/api/orders", method = RequestMethod.GET)
    public List<CoffeeOrderMongoEntity> getOrders(HttpServletRequest request){
        Optional<String> name = Optional.ofNullable(request.getParameter("name"));
        List<CoffeeOrderMongoEntity> coffeeOrders = new ArrayList<>();
        if (name.isPresent()) {
            Optional<CoffeeOrderMongoEntity> coffeOrder = coffeeOrderServiceImpl.findOrderByCustomerName(name.get());
            coffeeOrders.add(coffeOrder.get());
        } else {
            coffeeOrders = coffeeOrderServiceImpl.getOrderList();
        }
        return coffeeOrders;
    }

    @RequestMapping(value = "/api/order", method = RequestMethod.POST)
    public CoffeeOrderMongoEntity createOrder(HttpServletRequest request){
        Optional<String> customer = Optional.ofNullable(request.getParameter("customer"));
        Optional<String> coffeeIds = Optional.ofNullable(request.getParameter("coffee_ids"));

        List<String> ids = Arrays.asList(coffeeIds.get().split(","));

        List<CoffeeMongoEntity> coffees = coffeeService.findAllCoffeeById(ids);

        CoffeeOrderMongoEntity order = coffeeOrderServiceImpl.createOrder(customer.get(), coffees);
        return order;
    }

    @RequestMapping(value = "/api/order/state", method = RequestMethod.POST)
    public Boolean updateOrderState(@RequestBody CoffeOrderUpdateInfo stateParam){

         return coffeeOrderServiceImpl.updateStateByOrderId(stateParam.getId(), stateParam.getState());
    }
}
