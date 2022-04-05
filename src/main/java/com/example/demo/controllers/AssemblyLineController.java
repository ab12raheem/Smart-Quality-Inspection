package com.example.demo.controllers;

import com.example.demo.model.AssemblyLine;
import com.example.demo.model.Employee;
import com.example.demo.model.WareHouse;
import com.example.demo.serveces.AssemblyLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/assemblyLine")
public class AssemblyLineController {
    private final AssemblyLineService assemblyLineService;
    @Autowired
    public AssemblyLineController(AssemblyLineService assemblyLineService) {
        this.assemblyLineService = assemblyLineService;
    }

    @GetMapping
    public List<AssemblyLine> getAll(){
        return assemblyLineService.getAll();
    }
    @GetMapping("byName/{name}")
    public AssemblyLine getByName(@PathVariable String name){
        return assemblyLineService.getByName(name);
    }
    @GetMapping("byDepartmentId/{departmentId}")
    public AssemblyLine getByDepartmentId(@PathVariable Integer departmentId){
        return assemblyLineService.getByDepartmentId(departmentId);
    }
    @GetMapping("byStage/{stage}")
    public AssemblyLine getByStage(@PathVariable String stage){
        return assemblyLineService.getByStage(stage);
    }
    @GetMapping("getEmployees/{name}")
    public Set<Employee>getEmployees(@PathVariable String name){
        return assemblyLineService.getEmployees(name);
    }
    @PostMapping("add")
    public void addAssemblyLine(@RequestBody  AssemblyLine assemblyLine){
        assemblyLineService.addAssemblyLine(assemblyLine);
    }
    @DeleteMapping("deleteById/{name}")
    public void deleteById(@PathVariable String name){
        assemblyLineService.deleteByName(name);
    }
    @PutMapping("update/{name}")
    public void updateAssemblyLine(@PathVariable String name,
                                @RequestParam  String stage
    ){
        assemblyLineService.updateAssemblyLine(name,stage);
    }

}
