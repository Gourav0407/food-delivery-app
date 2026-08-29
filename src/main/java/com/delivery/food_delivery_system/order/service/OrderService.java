package com.delivery.food_delivery_system.order.service;

import com.delivery.food_delivery_system.order.entity.Order;
import com.delivery.food_delivery_system.order.enums.Status;
import com.delivery.food_delivery_system.order.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Gourav
 **/
@Service
public class OrderService {

    // Dependencies---
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository=orderRepository;
    }

    // Logic---

    public Optional<Order> getOrderById(Long id){
        return orderRepository.findById(id);
    }

    public Order createOrder(Order order){
        order.setStatus(Status.PENDING);
        return orderRepository.save(order);
    }


    public boolean cancelOrder(long id){
        if(orderRepository.cancelOrder(id)) {
            return true;
        }
        return false;
    }


}
