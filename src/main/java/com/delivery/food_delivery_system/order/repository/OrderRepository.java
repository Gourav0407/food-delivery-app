package com.delivery.food_delivery_system.order.repository;

import com.delivery.food_delivery_system.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Gourav
 **/
public interface OrderRepository extends JpaRepository<Order, Long>, OrderCustomRepo {
}
