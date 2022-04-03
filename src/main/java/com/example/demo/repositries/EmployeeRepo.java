package com.example.demo.repositries;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {

    Optional<Employee> getByRole(Integer role);

    List<Employee> findAllByRole(Integer role);

    Optional<Employee> getByUser(User user);

    List<Employee> findAllByDepartment(Department department);

    List<Employee> findAllByDepartmentAndRole(Department department, Integer role);

    List<Employee> findAllBySalary(Integer salary);
}
