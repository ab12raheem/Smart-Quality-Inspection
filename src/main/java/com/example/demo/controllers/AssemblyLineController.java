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
    @GetMapping("byId/{id}")
    public AssemblyLine getById(@PathVariable Integer id){
        return assemblyLineService.getById(id);
    }
    @GetMapping("byDepartmentId/{departmentId}")
    public AssemblyLine getByDepartmentId(@PathVariable Integer departmentId){
        return assemblyLineService.getByDepartmentId(departmentId);
    }
    @GetMapping("byStage/{stage}")
    public AssemblyLine getByStage(@PathVariable String stage){
        return assemblyLineService.getByStage(stage);
    }
    @GetMapping("getEmployees/{id}")
    public Set<Employee>getEmployees(@PathVariable Integer id){
        return assemblyLineService.getEmployees(id);
    }
    @PostMapping("add/{departmentId}")
    public void addAssemblyLine(@PathVariable Integer departmentId,
                             @RequestBody  AssemblyLine assemblyLine){
        assemblyLineService.addAssemblyLine(departmentId,assemblyLine);
    }
    @DeleteMapping("deleteById/{id}")
    public void deleteById(@PathVariable Integer id){
        assemblyLineService.deleteById(id);
    }
    @PutMapping("update/{id}")
    public void updateAssemblyLine(@PathVariable Integer id,
                                @RequestParam  String stage
    ){
        assemblyLineService.updateAssemblyLine(id,stage);
    }

}
