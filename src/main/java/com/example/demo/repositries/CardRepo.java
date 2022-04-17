package com.example.demo.repositries;

import com.example.demo.model.Card;
import com.example.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepo extends JpaRepository<Card,Integer> {

    Optional<Card> findByCustomer(Customer customer);
}
