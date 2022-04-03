package com.example.demo.repositries;


import com.example.demo.model.AssemblyLine;
import com.example.demo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssemblyLineRepo extends JpaRepository<AssemblyLine,Integer> {
    Optional<AssemblyLine> findByDepartment(Department department);

    Optional<AssemblyLine> findByStage(String stage);
}
