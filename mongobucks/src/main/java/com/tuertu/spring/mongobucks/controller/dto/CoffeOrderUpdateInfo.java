package com.tuertu.spring.mongobucks.controller.dto;

import com.tuertu.spring.mongobucks.entity.OrderState;
import lombok.Data;

@Data
public class CoffeOrderUpdateInfo {
    private String id;
    private OrderState state;
}
