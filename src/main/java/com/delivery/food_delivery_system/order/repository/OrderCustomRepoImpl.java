package com.delivery.food_delivery_system.order.repository;

import com.delivery.food_delivery_system.order.entity.Order;
import com.delivery.food_delivery_system.order.enums.Status;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gourav
 **/
@Repository
public class OrderCustomRepoImpl implements OrderCustomRepo {

    private final EntityManager entityManager;

    public OrderCustomRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public boolean changeStatus(Long id, Status status) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        // 1. Tell the builder we want to do an UPDATE on the Order entity
        CriteriaUpdate<Order> update = cb.createCriteriaUpdate(Order.class);

        // 2. Define the Root (This is the equivalent of "FROM orders o")
        Root<Order> root = update.from(Order.class);

        // 3. The SET clause: o.status = CANCELLED
        update.set(root.get("status"), status);


        update.where(
                cb.and(
                        cb.equal(root.get("id"),id),
                        cb.notEqual(root.get("status"),Status.CANCELLED),
                        cb.notEqual(root.get("status"),Status.DELIVERED)
                )
        );

        int rowUpdate= entityManager.createQuery(update).executeUpdate();

        return rowUpdate>0;
    }
}
