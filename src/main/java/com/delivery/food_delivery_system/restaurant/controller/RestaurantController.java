package com.delivery.food_delivery_system.restaurant.controller;

import com.delivery.food_delivery_system.restaurant.entity.MenuItem;
import com.delivery.food_delivery_system.restaurant.entity.Restaurant;
import com.delivery.food_delivery_system.restaurant.service.RestaurantService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Gourav
 **/

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService){
        this.restaurantService=restaurantService;
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getRestaurant(){
        List<Restaurant> restaurants= restaurantService.getAllRestaurant();
        if(restaurants!=null && !restaurants.isEmpty()){
            return ResponseEntity.ok(restaurants);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> getResturant(@PathVariable ObjectId restaurantId){
        Optional<Restaurant> restaurant= restaurantService.getRestaurant(restaurantId);

        return restaurant.map(value->ResponseEntity.ok(value))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> registerRestaurant(@RequestBody Restaurant restaurant){

        Restaurant savedRestaurant = restaurantService.registerRestaurant(restaurant);

        if (savedRestaurant!=null){
            String success= "Restaurant successfully registered with ID: "+ savedRestaurant.getId();
            return new ResponseEntity<>(success, HttpStatus.CREATED);
        }

        return ResponseEntity.badRequest().body("Could not create");

    }

    @PostMapping("/add-menu-item/{restaurantId}")
    public ResponseEntity<String> addMenuItem(@RequestBody MenuItem item, @PathVariable ObjectId restaurantId){

        if(restaurantService.addMenuItem(item,restaurantId)){
            String success= item.getName()+ " added successfully in your menu";
            return new ResponseEntity<>(success, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().body("Could not add item");

    }

    @PostMapping("/add-menu-items/{restaurantId}")
    public ResponseEntity<String> addMenuItem(@RequestBody List<MenuItem> items, @PathVariable ObjectId restaurantId){

        if(restaurantService.addMenuItem(items,restaurantId)){
            String success= "All the items added successfully in your menu";
            return new ResponseEntity<>(success, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().body("Could not add item");

    }

    @DeleteMapping("{restaurantId}/{itemId}")
    public ResponseEntity<String> removeMenuItem(@PathVariable ObjectId restaurantId, @PathVariable ObjectId itemId){

        if(restaurantService.deleteMenuItem(itemId,restaurantId)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();

    }

    @PatchMapping("{restaurantId}")
    public ResponseEntity<String> updateMenuItem(@PathVariable ObjectId restaurantId, @RequestBody MenuItem item){

        if(restaurantService.updateMenuItem(item,restaurantId)){
            String success= item.getName()+ " updated successfully in your menu";
            return ResponseEntity.ok(success);
        }
        return ResponseEntity.badRequest().build();
    }

}
