package com.example.demo.repositries;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Integer> {

    Optional<Department> findByEmail(String email);

    Optional<Department> findByName(String name);

    Optional<Object> getByEmail(String email);

    Optional<Object> getByName(String name);
}
