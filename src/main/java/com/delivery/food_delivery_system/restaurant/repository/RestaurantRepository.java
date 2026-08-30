package com.delivery.food_delivery_system.restaurant.repository;

import com.delivery.food_delivery_system.restaurant.entity.Restaurant;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Gourav
 **/
public interface RestaurantRepository extends MongoRepository<Restaurant, ObjectId>, RestaurantCustomRepo {
}
