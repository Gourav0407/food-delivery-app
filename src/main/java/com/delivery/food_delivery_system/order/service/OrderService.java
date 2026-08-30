package com.delivery.food_delivery_system.order.service;

import com.delivery.food_delivery_system.order.dto.CreateOrderDto;
import com.delivery.food_delivery_system.order.entity.Order;
import com.delivery.food_delivery_system.order.enums.Status;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Gourav
 **/

public interface OrderService{

    Optional<Order> getOrderById(Long id);

    Order createOrder(CreateOrderDto order);

    boolean cancelOrder(Long id);

    void updateOrderStatus(Long id, Status status);

}
