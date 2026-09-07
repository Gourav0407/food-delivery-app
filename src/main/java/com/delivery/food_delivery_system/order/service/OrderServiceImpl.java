package com.delivery.food_delivery_system.order.service;

import com.delivery.food_delivery_system.order.dto.CreateOrderDto;
import com.delivery.food_delivery_system.order.entity.Order;
import com.delivery.food_delivery_system.order.entity.OrderItem;
import com.delivery.food_delivery_system.order.enums.Status;
import com.delivery.food_delivery_system.order.repository.OrderRepository;
import com.delivery.food_delivery_system.restaurant.entity.MenuItem;
import com.delivery.food_delivery_system.restaurant.entity.Restaurant;
import com.delivery.food_delivery_system.restaurant.service.RestaurantService;
import org.bson.types.ObjectId;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Gourav
 **/
@Service
public class OrderServiceImpl implements OrderService{

    // Dependencies---
    private final OrderRepository orderRepository;
    private final RestaurantService restaurantService;
    private final KafkaTemplate<String,String> kafkaTemplate;

    public OrderServiceImpl(OrderRepository orderRepository, RestaurantService restaurantService, KafkaTemplate<String,String> kafkaTemplate){
        this.orderRepository=orderRepository;
        this.restaurantService=restaurantService;
        this.kafkaTemplate=kafkaTemplate;
    }

    // Logic---

    public Optional<Order> getOrderById(Long id){
        return orderRepository.findById(id);
    }

    public Order createOrder(CreateOrderDto order){

        Restaurant restaurant = restaurantService.getRestaurant(new ObjectId(order.restaurantId()))
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        Map<String, MenuItem> menuMap = restaurant.getMenu().stream()
                .collect(Collectors.toMap(item -> item.getId().toString(), item -> item));


        List<OrderItem> orderItemList= order.orderItemList().stream().
                filter(element-> menuMap.containsKey(element.menuItemId()))
                .map(element->
                        OrderItem.builder()
                                .price( menuMap.get(element.menuItemId()).getPrice()*element.quantity())
                                .name(menuMap.get(element.menuItemId()).getName())
                                .menuItemId(element.menuItemId())
                                .quantity(element.quantity()).build())
                .collect(Collectors.toList());

        if(orderItemList.isEmpty()){
            throw new RuntimeException("No valid menu items found in the order");
        }

        Order actualOrder=Order.builder().restaurantName(restaurant.getName()).restaurantId(order.restaurantId()).customerName(order.customerName()).customerEmail(order.customerEmail()).orderItemList(orderItemList).amount(orderItemList.stream().mapToDouble(OrderItem::getPrice).sum()).build();
        actualOrder.setStatus(Status.PENDING);

        Order postSave= orderRepository.save(actualOrder);
        String orderId= postSave.getId().toString();

        kafkaTemplate.send("order-events",orderId,orderId);
        return postSave;
    }


    public boolean cancelOrder(Long id){
        return orderRepository.changeStatus(id, Status.CANCELLED);
    }

    public void updateOrderStatus(Long id, Status status){
        orderRepository.changeStatus(id, status);
    }
}
