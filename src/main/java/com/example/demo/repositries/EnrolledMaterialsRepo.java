package com.example.demo.repositries;

import com.example.demo.model.EnrolledMaterials;
import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrolledMaterialsRepo extends JpaRepository<EnrolledMaterials,Integer> {
    List<EnrolledMaterials> findAllByProduct(Product product);

    void deleteAllByProduct(Product product);
}
