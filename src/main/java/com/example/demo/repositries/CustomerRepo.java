package com.example.demo.repositries;


import com.example.demo.model.Customer;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    Optional<Customer> getByUser(User user);

    List<Customer> findAllByAddress(String address);

    Optional<Customer> findByUser(User user);
}
