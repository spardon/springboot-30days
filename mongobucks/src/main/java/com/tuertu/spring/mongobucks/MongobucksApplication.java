package com.tuertu.spring.mongobucks;

import com.tuertu.spring.mongobucks.convert.MoneyReadMongoConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;

@SpringBootApplication
@EnableMongoRepositories
public class MongobucksApplication {

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList(new MoneyReadMongoConverter()));
    }

    public static void main(String[] args) {
        SpringApplication.run(MongobucksApplication.class, args);
    }

}
