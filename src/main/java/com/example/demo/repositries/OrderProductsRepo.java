package com.example.demo.repositries;

import com.example.demo.model.Order;
import com.example.demo.model.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderProductsRepo extends JpaRepository<OrderProducts,Integer> {
    Optional<OrderProducts> findByOrder(Order order);

    List<OrderProducts> findAllByOrder(Order order);

    void deleteAllByOrder(Order order);
}
