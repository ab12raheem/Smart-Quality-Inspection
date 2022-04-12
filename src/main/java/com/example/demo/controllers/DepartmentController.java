package com.example.demo.controllers;

import com.example.demo.model.Department;
import com.example.demo.serveces.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/department")
@CrossOrigin(origins = "http://localhost:3000")

public class DepartmentController {
    private final DepartmentService departmentService;
    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    @PreAuthorize("hasRole('Admin')")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }
    @GetMapping("ByName/{name}")
    @PreAuthorize("hasRole('Admin')")
    public Department getById(@PathVariable String name){
        return departmentService.getByName(name);

    }
    @PostMapping("addDepartment")
    @PreAuthorize("hasRole('Admin')")
    public void addDepartment(@RequestBody Department department){
        departmentService.addDepartment(department);
    }
    @DeleteMapping("deleteByName/{name}")
    @PreAuthorize("hasRole('Admin')")
    public void deleteByName(@PathVariable String name){
        departmentService.deleteByName(name);
    }
    @PutMapping("update/{name1}")
    @PreAuthorize("hasRole('Admin')")
    public void updateDepartment(@PathVariable String name1,
                                 @RequestParam (required = false) String phone,
                                 @RequestParam (required = false) String fax,
                                 @RequestParam (required = false) String name,
                                 @RequestParam (required = false) String email){
        departmentService.updateDepartment(name1,phone,fax,name,email);
    }

}
