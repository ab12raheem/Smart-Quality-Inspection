package com.example.demo.controllers;

import com.example.demo.model.AssemblyLine;
import com.example.demo.model.Employee;
import com.example.demo.model.WareHouse;
import com.example.demo.serveces.AssemblyLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/assemblyLine")
@CrossOrigin(origins = "http://localhost:3000")

public class AssemblyLineController {
    private final AssemblyLineService assemblyLineService;
    @Autowired
    public AssemblyLineController(AssemblyLineService assemblyLineService) {
        this.assemblyLineService = assemblyLineService;
    }

    @GetMapping
    @PreAuthorize("hasRole('Admin')")
    public List<AssemblyLine> getAll(){
        return assemblyLineService.getAll();
    }
    @GetMapping("byName/{name}")
    @PreAuthorize("hasRole('Admin')")
    public AssemblyLine getByName(@PathVariable String name){
        return assemblyLineService.getByName(name);
    }
    @GetMapping("byDepartmentId/{departmentId}")
    @PreAuthorize("hasRole('Admin')")
    public AssemblyLine getByDepartmentId(@PathVariable Integer departmentId){
        return assemblyLineService.getByDepartmentId(departmentId);
    }
    @GetMapping("byStage/{stage}")
    @PreAuthorize("hasRole('Admin')")
    public AssemblyLine getByStage(@PathVariable String stage){
        return assemblyLineService.getByStage(stage);
    }
    @GetMapping("getEmployees/{name}")
    @PreAuthorize("hasRole('Admin')")
    public Set<Employee>getEmployees(@PathVariable String name){
        return assemblyLineService.getEmployees(name);
    }
    @PostMapping("add")
    @PreAuthorize("hasRole('Admin')")
    public void addAssemblyLine(@RequestBody  AssemblyLine assemblyLine){
        assemblyLineService.addAssemblyLine(assemblyLine);
    }
    @DeleteMapping("deleteById/{name}")
    @PreAuthorize("hasRole('Admin')")
    public void deleteById(@PathVariable String name){
        assemblyLineService.deleteByName(name);
    }
    @PutMapping("update/{name}")
    @PreAuthorize("hasAnyRole('Admin','Head')")
    public void updateAssemblyLine(@PathVariable String name,
                                @RequestParam  String stage
    ){
        assemblyLineService.updateAssemblyLine(name,stage);
    }

}
