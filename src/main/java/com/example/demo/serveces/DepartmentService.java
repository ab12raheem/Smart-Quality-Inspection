package com.example.demo.serveces;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.User;
import com.example.demo.repositries.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class DepartmentService {
    private final DepartmentRepo departmentRepo;


    @Autowired
    public DepartmentService(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    public List<Department> getAllDepartments() {
       return departmentRepo.findAll();
    }

    public Department getById(Integer id) {
        Optional<Department> departmentOptional=departmentRepo.findById(id);
        if(!departmentOptional.isPresent()){
            throw new IllegalStateException("departmentNotFound");

        }
        return departmentOptional.get();
    }

    public void addDepartment(Department department) {
        Optional<Department>departmentEmail=departmentRepo.findByEmail(department.getEmail());
        Optional<Department>departmentName=departmentRepo.findByName(department.getName());
        if(departmentEmail.isPresent()){
            throw new IllegalStateException("email has been used before");
        }
        if(departmentName.isPresent()){
            throw new IllegalStateException("name has been used before");
        }
        departmentRepo.save(department);
    }

    public void deleteByID(Integer id) {
        Optional<Department>department=departmentRepo.findById(id);
        if(!department.isPresent()){
            throw new IllegalStateException("department not found");
        }
        Department department1=department.get();
        Set<Employee> employees=department1.getEmployeeSet();
        for(Employee employee : employees){
            employee.setDepartment(null);
        }
        departmentRepo.delete(department1);

    }

    @Transactional
    public void updateDepartment(Integer id, String phone, String fax, String name, String email) {
        Optional<Department> department = departmentRepo.findById(id);
        if (department.isPresent()) {
            if (departmentRepo.getByEmail(email).isPresent()) {
                throw new IllegalStateException("email name have been used before");
            }
            if (departmentRepo.getByName(name).isPresent()) {
                throw new IllegalStateException("Email have been used before");
            }

            Department department1 = department.get();
            if (phone != null &&
                    !Objects.equals(department1.getPhone(), phone)) {
                department1.setPhone(phone);
            }
            if (fax != null &&
                    fax.length() > 0 &&
                    !Objects.equals(department1.getFax(), fax)) {
                department1.setFax(fax);
            }
            if (email != null &&
                    email.length() > 0 &&
                    !Objects.equals(department1.getEmail(), email)) {
                department1.setEmail(email);
            }
            if (name != null &&
                    name.length() > 0 &&
                    !Objects.equals(department1.getName(), name)) {
                department1.setName(name);
            }
        }else throw new IllegalStateException("department not found");
    }
}
