package com.delivery.food_delivery_system.restaurant.repository;

import com.delivery.food_delivery_system.restaurant.entity.MenuItem;
import com.delivery.food_delivery_system.restaurant.entity.Restaurant;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Gourav
 **/

@Repository
public class RestaurantCustomRepoImpl implements RestaurantCustomRepo {

    private final MongoTemplate mongoTemplate;

    public RestaurantCustomRepoImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate=mongoTemplate;
    }


    public boolean addMenuItem(List<MenuItem> items, ObjectId restaurantId){
        Query query=new Query();

        query.addCriteria(Criteria.where("_id").is(restaurantId));

        Update update = new Update();

        update.push("menu").each(items);

        UpdateResult result=mongoTemplate.updateFirst(query,update, Restaurant.class);

        return result.getModifiedCount()>0;
    }

    public boolean removeMenuItem(ObjectId itemId, ObjectId restaurantId){
        Query query=new Query();

        query.addCriteria(Criteria.where("_id").is(restaurantId));

        Update update = new Update();

        update.pull("menu",new Document("_id",itemId));

        UpdateResult result=mongoTemplate.updateFirst(query,update, Restaurant.class);

        return result.getModifiedCount()>0;
    }

    public boolean updateMenuItem(MenuItem item, ObjectId restaurantId){
        Query query=new Query();

        query.addCriteria(Criteria.where("_id").is(restaurantId)
                .and("menu._id").is(item.getId()));

        Update update = new Update();

        if(item.getName()!= null){
            update.set("menu.$.name",item.getName());
        }
        if(item.getIsVeg()!=null){
            update.set("menu.$.isVeg",item.getIsVeg());
        }
        if(item.getPrice()!=null){
            update.set("menu.$.price",item.getPrice());
        }
        if(item.getDescription()!=null){
            update.set("menu.$.description",item.getDescription());
        }

        if(update.getUpdateObject().isEmpty()){
            return false;
        }

        UpdateResult result=mongoTemplate.updateFirst(query,update, Restaurant.class);



        return result.getModifiedCount()>0;
    }
}
