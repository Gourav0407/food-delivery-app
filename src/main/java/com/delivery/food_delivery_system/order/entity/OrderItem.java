package com.delivery.food_delivery_system.order.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Gourav
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String menuItemId;
    private String name;
    @NonNull
    private Integer quantity;
    private Double price;

}
