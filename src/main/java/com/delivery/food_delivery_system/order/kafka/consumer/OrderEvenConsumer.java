package com.delivery.food_delivery_system.order.kafka.consumer;

import com.delivery.food_delivery_system.notification.service.MailService;
import com.delivery.food_delivery_system.order.entity.Order;
import com.delivery.food_delivery_system.order.enums.Status;
import com.delivery.food_delivery_system.order.service.OrderService;
import org.bson.types.ObjectId;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Gourav
 **/

@Service
public class OrderEvenConsumer {

    private final OrderService orderService;
    private final MailService mailService;

    public OrderEvenConsumer(OrderService orderService, MailService mailService){
        this.orderService=orderService;
        this.mailService=mailService;
    }

    @KafkaListener(topics = "order-events", groupId = "order-processing-group", concurrency = "3")
    @Transactional
    public void consume(String orderId){
        orderService.updateOrderStatus(Long.valueOf(orderId), Status.CONFIRMED);
        Optional<Order> optionalOrder=orderService.getOrderById(Long.valueOf(orderId));

        optionalOrder.ifPresent(order -> mailService
                .sendMail(order.getCustomerEmail(), "Thanks for odering for Fake Zomato", order));
    }

}
