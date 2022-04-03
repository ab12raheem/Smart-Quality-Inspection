package com.example.demo.repositries;

import com.example.demo.model.Product;
import com.example.demo.model.ProductsEnrolled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsEnrolledRepo  extends JpaRepository<ProductsEnrolled,Integer> {
    List<ProductsEnrolled> findAllByProduct(Product product);

    void deleteAllByProduct(Product product);
}
