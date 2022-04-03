package com.example.demo.repositries;

import com.example.demo.model.Department;
import com.example.demo.model.WareHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WareHouseRepo extends JpaRepository<WareHouse,Integer> {
    Optional<WareHouse> findByDepartment(Department department);
}
