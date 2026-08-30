package com.delivery.food_delivery_system.order.dto;

import lombok.NonNull;

/**
 * @author Gourav
 **/
public record OrderItemDto(@NonNull String menuItemId,
                           String name,
                           @NonNull
                           Integer quantity) {}
