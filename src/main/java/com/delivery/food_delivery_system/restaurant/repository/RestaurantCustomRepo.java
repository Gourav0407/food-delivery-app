package com.delivery.food_delivery_system.restaurant.repository;

import com.delivery.food_delivery_system.restaurant.entity.MenuItem;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * @author Gourav
 **/
public interface RestaurantCustomRepo {

    boolean addMenuItem(List<MenuItem> items, ObjectId restaurantId);

    boolean removeMenuItem(ObjectId itemId, ObjectId restaurantId);

    boolean updateMenuItem(MenuItem item, ObjectId restaurantId);
}
