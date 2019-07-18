package com.tuertu.spring.mongobucks.entity;

import lombok.*;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Document(value = "coffee")
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class CoffeeMongoEntity implements Serializable {
    @Id
    private String id;
    private String name;
    private Money price;
    private Date createTime;
    private Date updateTime;

    public Double getPrice() {
        return Double.parseDouble(price.getAmount().toString());
    }
}
