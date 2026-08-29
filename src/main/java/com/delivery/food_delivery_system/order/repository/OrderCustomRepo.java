package com.delivery.food_delivery_system.order.repository;

/**
 * @author Gourav
 **/
interface OrderCustomRepo {
    boolean cancelOrder(Long id);
}
