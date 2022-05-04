package com.example.demo.controllers;

import com.example.demo.model.Employee;
import com.example.demo.serveces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/employee")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
    private final EmployeeService employeeService;


    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;

    }
    @GetMapping
    @PreAuthorize("hasRole('Admin')")
    public List<Employee> getEmployees(){
        return employeeService.getEmployees();
    }

    @GetMapping("byId/{id}")
    @PreAuthorize("hasRole('Admin')")
    public Employee getById(@PathVariable Integer id){
        return employeeService.getById(id);
    }

    @GetMapping("byRole/{role}")
    @PreAuthorize("hasAnyRole('Admin','Head')")
    public List<Employee> getByRole(@PathVariable Integer role){
        return employeeService.getByRole(role);
    }
    @GetMapping("byUserName/{userName}")
    @PreAuthorize("hasAnyRole('Admin','Head','Employee')")
    public Employee getByUserName(@PathVariable String userName){
        return employeeService.getByUserName(userName);
    }
    @GetMapping("byDepartment/{departmentName}")
    @PreAuthorize("hasAnyRole('Admin','Head')")
    public List<Employee> getByDepartment(@PathVariable String departmentName ){
        return employeeService.getByDepartment(departmentName);
    }
    @GetMapping("byDepartmentAndRole/{departmentName}/{role}")
    @PreAuthorize("hasAnyRole('Admin','Head')")
    public List<Employee> getByDepartment(@PathVariable String departmentName,
                                          @PathVariable Integer role){
        return employeeService.getByDepartmentAndRole(departmentName,role);
    }
    @GetMapping("BySalary/{salary}")
    @PreAuthorize("hasRole('Admin')")
    public List<Employee> getBySalary(@PathVariable Integer salary){
        return employeeService.getBySalary(salary);
    }
    @DeleteMapping("deleteEmployee/{id}")
    @PreAuthorize("hasRole('Admin')")
    public void deleteByUserName(@PathVariable Integer id){
        employeeService.deleteById(id);
    }
    @DeleteMapping("deleteEmployeeByUserName/{userName}")
    @PreAuthorize("hasRole('Admin')")
    public void deleteById(@PathVariable String userName){
        employeeService.deleteByUserName(userName);
    }
    @PostMapping("/addEmployee/{departmentName}")
    @PreAuthorize("hasRole('Admin')")
    public void addEmployee(@RequestBody Employee employee,
                            @PathVariable String departmentName){

        employeeService.addEmployee(employee,departmentName);
    }/*
    @PostMapping("/addHeadEmployee/{departmentId}/{userId}")
    public void addHeadEmployee(@RequestBody Employee employee,
                            @PathVariable Integer departmentId,
                            @PathVariable Integer userId ){

        employeeService.addHeadEmployee(employee,departmentId,userId);
    }
    @PostMapping("/addAdmin/{departmentId}/{userId}")
    public void addAdmin(@RequestBody Employee employee,
                                @PathVariable Integer departmentId,
                                @PathVariable Integer userId ){

        employeeService.addAdmin(employee,departmentId,userId);
    }
*/
    @PutMapping("updateByUserName/{userName}")
    @PreAuthorize("hasAnyRole('Admin','Head','Employee')")
    public void updateEmployee(
            @PathVariable  String userName ,
            @RequestParam (required = false) Date startingDate,
            @RequestParam (required = false) Double salary,
            @RequestParam (required = false) Integer role,
            @RequestParam (required = false) String image,
            @RequestParam (required = false) Integer departmentId){
        employeeService.updateEmployee(userName,startingDate,image,salary,departmentId,role);

    }



}
