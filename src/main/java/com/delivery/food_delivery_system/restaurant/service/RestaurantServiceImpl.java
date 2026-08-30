package com.delivery.food_delivery_system.restaurant.service;

import com.delivery.food_delivery_system.restaurant.entity.MenuItem;
import com.delivery.food_delivery_system.restaurant.entity.Restaurant;
import com.delivery.food_delivery_system.restaurant.repository.RestaurantRepository;
import org.bson.types.ObjectId;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.awt.*;
import java.util.List;
import java.util.Optional;

/**
 * @author Gourav
 **/

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository){
        this.restaurantRepository=restaurantRepository;
    }

    @Override
    public Restaurant registerRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    @Cacheable(value = "restaurants", key = "#id")
    public Optional<Restaurant> getRestaurant(ObjectId id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    @CacheEvict(value = "restaurants", key = "#restaurantId")
    public boolean addMenuItem(MenuItem item, ObjectId restaurantId) {
        item.setId(new ObjectId());
        return restaurantRepository.addMenuItem(item,restaurantId);
    }

    @Override
    @CacheEvict(value = "restaurants", key = "#restaurantId")
    public boolean deleteMenuItem(ObjectId itemId, ObjectId restaurantId) {
        return restaurantRepository.removeMenuItem(itemId,restaurantId);
    }

    @Override
    @CacheEvict(value = "restaurants", key = "#restaurantId")
    public boolean updateMenuItem(MenuItem item, ObjectId restaurantId) {
        return restaurantRepository.updateMenuItem(item,restaurantId);
    }
}
