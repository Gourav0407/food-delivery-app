package com.delivery.food_delivery_system.restaurant.repository;

import com.delivery.food_delivery_system.restaurant.entity.MenuItem;
import org.bson.types.ObjectId;

/**
 * @author Gourav
 **/
public interface RestaurantCustomRepo {

    boolean addMenuItem(MenuItem item, ObjectId id);

    boolean removeMenuItem(ObjectId itemId, ObjectId restaurantId);

    boolean updateMenuItem(MenuItem item, ObjectId restaurantId);
}
