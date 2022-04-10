package com.example.demo.serveces;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.User;
import com.example.demo.repositries.DepartmentRepo;
import com.example.demo.repositries.EmployeeRepo;
import com.example.demo.repositries.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final UserRepo userRepo;
    private final DepartmentRepo departmentRepo;
    private final UserService userService;
    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo, UserRepo userRepo, DepartmentRepo departmentRepo, UserService userService) {
        this.employeeRepo = employeeRepo;
        this.userRepo = userRepo;
        this.departmentRepo = departmentRepo;
        this.userService = userService;
    }

    public List<Employee> getEmployees() {
        return employeeRepo.findAll();
    }



    public Employee getById(Integer id) {
        if(!employeeRepo.findById(id).isPresent()){
            throw new IllegalStateException("Employee not Found");
        }
        return employeeRepo.findById(id).get();
    }
    public void addEmployee(Employee employee,String departmentName) {

        Optional<User>user=userRepo.getByUserName(employee.getUser().getUserName());
        Optional<User>user1=userRepo.getByEmail(employee.getUser().getEmail());

        Optional<Department>department=departmentRepo.findByName(departmentName);
        if(user.isPresent()){
            throw new IllegalStateException("userName used before");

        }
        if(user1.isPresent()){
            throw new IllegalStateException("email used before");

        }
        if(!department.isPresent()){
            throw new IllegalStateException("department not found");
        }



        userService.addUser(employee.getUser());
        Department department1=department.get();
        employee.setUser(employee.getUser());
        employee.setDepartment(department1);
        employeeRepo.save(employee);
    }

/*
    public void addHeadEmployee(Employee employee, Integer departmentId, Integer userId) {

        Optional<User>user=userRepo.findById(userId);
        Optional<Department>department=departmentRepo.findById(departmentId);
        if(!user.isPresent()){
            throw new IllegalStateException("user not found");

        }
        if(!department.isPresent()){
            throw new IllegalStateException("department not found");
        }
        if(user.get().getRole()!=0){
            throw new IllegalStateException("user has been used before");
        }
        User user1=user.get();
        user1.setRole(1);
        userRepo.save(user1);
        Department department1=department.get();
        employee.setUser(user1);
        employee.setDepartment(department1);
        employee.setRole(2);
        employeeRepo.save(employee);
    }

    public void addAdmin(Employee employee, Integer departmentId, Integer userId) {
        Optional<User>user=userRepo.findById(userId);
        Optional<Department>department=departmentRepo.findById(departmentId);
        if(!user.isPresent()){
            throw new IllegalStateException("user not found");

        }
        if(!department.isPresent()){
            throw new IllegalStateException("department not found");
        }

        User user1=user.get();

        user1.setRole(1);
        userRepo.save(user1);
        Department department1=department.get();
        employee.setUser(user1);
        employee.setDepartment(department1);
        employee.setRole(3);
        employeeRepo.save(employee);
    }
*/
    public List<Employee> getByRole(Integer role) {
        return employeeRepo.findAllByRole(role);

    }

    public Employee getByUserName(String userName) {
        Optional<User>user=userRepo.getByUserName(userName);
        if(!user.isPresent()){
            throw new IllegalStateException("employee not found");
        }
        Optional<Employee>employeeOptional=employeeRepo.getByUser(user.get());
        if(!employeeOptional.isPresent()){
            throw new IllegalStateException("employee not found");
        }
        return employeeOptional.get();
    }

    public List<Employee> getByDepartment(String departmentName) {
        Optional<Department>department=departmentRepo.findByName(departmentName);
        if(!department.isPresent()){
            throw new IllegalStateException("employee not found");
        }
        return employeeRepo.findAllByDepartment(department.get());
    }

    public List<Employee> getByDepartmentAndRole(String departmentName, Integer role) {
        Optional<Department>department=departmentRepo.findByName(departmentName);
        if(!department.isPresent()){
            throw new IllegalStateException("employee not found");
        }
        return employeeRepo.findAllByDepartmentAndRole(department.get(),role);

    }

    public List<Employee> getBySalary(Integer salary) {
        return employeeRepo.findAllBySalary(salary);
    }

    public void deleteById(Integer id) {
        Optional<Employee> employee=employeeRepo.findById(id);


        if(!employee.isPresent()){
            throw new IllegalStateException("employee not found ");
        }
        Optional<User>user=userRepo.findById(employee.get().getUser().getId());
        if(!user.isPresent()){
            throw new IllegalStateException("user not found ");
        }
        employeeRepo.deleteById(id);
        userRepo.delete(user.get());

    }

    public void deleteByUserName(String userName) {
        Optional<User>user=userRepo.getByUserName(userName);
        if(!user.isPresent()){
            throw new IllegalStateException("employee not found");
        }
        Optional<Employee>employeeOptional=employeeRepo.getByUser(user.get());
        if(!employeeOptional.isPresent()){
            throw new IllegalStateException("employee not found");
        }
        employeeRepo.delete(employeeOptional.get());
        userRepo.delete(user.get());

    }
    @Transactional
    public void updateEmployee(String userName, Date startingDate, Integer salary, Integer departmentId,Integer role) {
        Employee employee=getByUserName(userName);

        if(startingDate!=null &&
                !Objects.equals(employee.getStartingDate(),startingDate)){
            employee.setStartingDate(startingDate);
        }
        if(salary!=null &&
                !Objects.equals(employee.getSalary(),salary)){
            employee.setSalary(salary);
        }
        if(role!=null &&
                !Objects.equals(employee.getRole(),role)){
            employee.setRole(role);
        }
        if(departmentId!=null &&
        departmentRepo.existsById(departmentId)){
            employee.setDepartment(departmentRepo.getById(departmentId));
        }
    }
}
