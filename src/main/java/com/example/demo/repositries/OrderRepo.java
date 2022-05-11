package com.example.demo.repositries;

import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {
    List<Order> findAllByCustomer(Customer customer);

    List<Order> findAllByActivate(Boolean Activate);
    @Query(value = "SELECT * FROM public.order WHERE customer_id= ?1 and activate=false",
    nativeQuery = true)
    Optional<Order> getCard(Integer customerId);
    @Query(
            value = "SELECT * FROM public.order ORDER BY order_date DESC LIMIT 5 ",
            nativeQuery = true
    )
    List<Order> getLast();
}
