package com.example.demo.repositries;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
    @Query(
            value="SELECT * FROM product ORDER BY price DESC",
            nativeQuery = true
    )
    List<Product> findByOrderPriceDesc();

    @Query(
           value = "SELECT * FROM product ORDER BY price ASC",
            nativeQuery = true
    )
    List<Product> findByOrderPriceAsc();
}
