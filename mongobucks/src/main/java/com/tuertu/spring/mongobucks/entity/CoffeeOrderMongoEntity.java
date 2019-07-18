package com.tuertu.spring.mongobucks.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@ToString
@Builder
public class CoffeeOrderMongoEntity implements Serializable {
    @Id
    private String id;
    private String customer;
    private List<CoffeeMongoEntity> items;
    private Date createDate;
    private Date updateDate;
    private OrderState state;
}
