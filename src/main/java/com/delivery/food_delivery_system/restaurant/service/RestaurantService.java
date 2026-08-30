package com.delivery.food_delivery_system.restaurant.service;

import com.delivery.food_delivery_system.restaurant.entity.MenuItem;
import com.delivery.food_delivery_system.restaurant.entity.Restaurant;
import org.bson.types.ObjectId;
import java.util.List;
import java.util.Optional;

/**
 * @author Gourav
 **/
public interface RestaurantService {

    Restaurant registerRestaurant(Restaurant restaurant);

    Optional<Restaurant> getRestaurant(ObjectId id);

    List<Restaurant> getAllRestaurant();

    boolean addMenuItem(MenuItem item, ObjectId id);

    boolean deleteMenuItem(ObjectId itemId, ObjectId restaurantId);

    boolean updateMenuItem(MenuItem item, ObjectId restaurantId);
}
