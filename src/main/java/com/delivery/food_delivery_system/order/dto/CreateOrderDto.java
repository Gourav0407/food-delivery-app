package com.delivery.food_delivery_system.order.dto;

import lombok.NonNull;

import java.util.List;

/**
 * @author Gourav
 **/

public record CreateOrderDto(String customerName,
                             @NonNull String customerEmail,
                             @NonNull String restaurantId,
                             List<OrderItemDto> orderItemList){}
