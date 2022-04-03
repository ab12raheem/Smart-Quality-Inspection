package com.example.demo.repositries;

import com.example.demo.model.Material;
import com.example.demo.model.MaterialSupplier;
import com.example.demo.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialSupplierRepo extends JpaRepository<MaterialSupplier,Integer> {
    List<MaterialSupplier> findAllByMaterial(Material material);

    List<MaterialSupplier> findAllBySupplier(Supplier supplier);
}
