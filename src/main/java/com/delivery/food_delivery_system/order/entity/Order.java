package com.delivery.food_delivery_system.order.entity;

import com.delivery.food_delivery_system.order.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gourav
 **/


@Entity
@Table(name="orders") // "order" is a reserved keyword in SQL, so we must use "orders"
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String customerName;
    @NonNull
    private String customerEmail;
    @NonNull
    private String restaurantId;
    private String restaurantName;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private Status status;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    @Builder.Default
    private List<OrderItem> orderItemList= new ArrayList<>();


}
