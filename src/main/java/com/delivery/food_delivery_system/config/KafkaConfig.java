package com.delivery.food_delivery_system.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @author Gourav
 **/

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic orderEventTopic(){
        return TopicBuilder.name("order-events")
                .partitions(3)
                .replicas(1).build();
    }
}
