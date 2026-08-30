package com.delivery.food_delivery_system.order.controller;

import com.delivery.food_delivery_system.order.dto.CreateOrderDto;
import com.delivery.food_delivery_system.order.entity.Order;
import com.delivery.food_delivery_system.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Gourav
 **/

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    @GetMapping("{orderId}")
    public ResponseEntity<Order> getOrders(@PathVariable Long orderId){

        Optional<Order> order= orderService.getOrderById(orderId);

        return order.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody CreateOrderDto order){

        Order savedOrder= orderService.createOrder(order);

        if (savedOrder!=null){
            String success= "Order successfully created with ID: "+ savedOrder.getId();
            return new ResponseEntity<>(success,HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Could not create",HttpStatus.BAD_REQUEST);


    }

    @PatchMapping("{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId){

        boolean status= orderService.cancelOrder(orderId);

        if(status) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
