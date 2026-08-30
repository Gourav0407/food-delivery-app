package com.delivery.food_delivery_system.restaurant.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gourav
 **/

@Data
@Document(collection = "restaurants")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurant implements Serializable {

    @Id
    @Field("_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    @NonNull
    private String name;
    private String location;
    @CreatedDate
    private Instant createdOn;
    @Builder.Default
    private List<MenuItem> menu= new ArrayList<>();

}
