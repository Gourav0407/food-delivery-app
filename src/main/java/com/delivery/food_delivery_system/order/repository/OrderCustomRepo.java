package com.delivery.food_delivery_system.order.repository;

import com.delivery.food_delivery_system.order.enums.Status;

/**
 * @author Gourav
 **/
interface OrderCustomRepo {
    boolean changeStatus(Long id, Status status);
}
