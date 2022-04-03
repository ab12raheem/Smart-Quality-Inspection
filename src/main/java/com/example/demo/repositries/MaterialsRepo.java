package com.example.demo.repositries;

import com.example.demo.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialsRepo extends JpaRepository<Material,Integer> {
}
