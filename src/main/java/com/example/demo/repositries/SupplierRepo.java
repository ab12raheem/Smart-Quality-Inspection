package com.example.demo.repositries;

import com.example.demo.model.Supplier;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier,Integer> {
    Optional<Supplier> getByUser(User user);
}
