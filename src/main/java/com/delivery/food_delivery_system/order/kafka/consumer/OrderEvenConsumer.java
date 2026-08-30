package com.delivery.food_delivery_system.order.kafka.consumer;

import com.delivery.food_delivery_system.order.enums.Status;
import com.delivery.food_delivery_system.order.service.OrderService;
import org.bson.types.ObjectId;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author Gourav
 **/

@Service
public class OrderEvenConsumer {

    private OrderService orderService;

    public OrderEvenConsumer(OrderService orderService){
        this.orderService=orderService;
    }

    @KafkaListener(topics = "order-events", groupId = "order-processing-group", concurrency = "3")
    public void consume(String orderId){
        orderService.updateOrderStatus(Long.valueOf(orderId), Status.CONFIRMED);
    }

}
