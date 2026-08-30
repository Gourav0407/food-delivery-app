package com.delivery.food_delivery_system.restaurant.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.NumberFormat;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

import java.text.Format;

/**
 * @author Gourav
 **/


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {

    @Id
    @Field("_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id; // Auto-generate an ID for the item
    private String name;
    private String description;
    private Boolean isVeg;
    private Double price;


}
