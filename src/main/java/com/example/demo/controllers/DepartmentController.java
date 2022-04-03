package com.example.demo.controllers;

import com.example.demo.model.Department;
import com.example.demo.serveces.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/department")
public class DepartmentController {
    private final DepartmentService departmentService;
    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }
    @GetMapping("ById/{id}")
    public Department getById(@PathVariable Integer id){
        return departmentService.getById(id);

    }
    @PostMapping("addDepartment")
    public void addDepartment(@RequestBody Department department){
        departmentService.addDepartment(department);
    }
    @DeleteMapping("deleteByID/{id}")
    public void deleteByID(@PathVariable Integer id){
        departmentService.deleteByID(id);
    }
    @PutMapping("update/{id}")
    public void updateDepartment(@PathVariable Integer id,
                                 @RequestParam (required = false) String phone,
                                 @RequestParam (required = false) String fax,
                                 @RequestParam (required = false) String name,
                                 @RequestParam (required = false) String email){
        departmentService.updateDepartment(id,phone,fax,name,email);
    }

}
