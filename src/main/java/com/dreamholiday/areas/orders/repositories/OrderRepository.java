package com.dreamholiday.areas.orders.repositories;

import com.dreamholiday.areas.orders.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findOneById(Long id);

    List<Order> findByUserId(Long id);
}
