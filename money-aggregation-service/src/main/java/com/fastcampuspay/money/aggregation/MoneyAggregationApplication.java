package com.fastcampuspay.money.aggregation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;

@SpringBootApplication(exclude = KafkaAutoConfiguration.class)
public class MoneyAggregationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyAggregationApplication.class, args);
    }


}
