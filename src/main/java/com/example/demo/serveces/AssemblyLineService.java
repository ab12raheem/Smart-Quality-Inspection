package com.example.demo.serveces;

import com.example.demo.model.*;
import com.example.demo.repositries.AssemblyLineRepo;
import com.example.demo.repositries.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AssemblyLineService {
    private final AssemblyLineRepo assemblyLineRepo;
    private final DepartmentRepo departmentRepo;
    private final DepartmentService departmentService;
    @Autowired
    public AssemblyLineService(AssemblyLineRepo assemblyLineRepo, DepartmentRepo departmentRepo, DepartmentService departmentService) {
        this.assemblyLineRepo = assemblyLineRepo;
        this.departmentRepo = departmentRepo;
        this.departmentService = departmentService;
    }

    public List<AssemblyLine> getAll() {
        return assemblyLineRepo.findAll();
    }

    public AssemblyLine getById(Integer id) {
        Optional<AssemblyLine>assemblyLine=assemblyLineRepo.findById(id);
        if(!assemblyLine.isPresent()){
            throw new IllegalStateException("assemblyLine not found");
        }
        return assemblyLine.get();
    }
    public AssemblyLine getByName(String name){
        Department department= departmentService.getByName(name);
        Optional<AssemblyLine>assemblyLine=assemblyLineRepo.findByDepartment(department);
        if(!assemblyLine.isPresent()){
            throw new IllegalStateException("assemblyLine not found");
        }
        return assemblyLine.get();
    }

    public AssemblyLine getByDepartmentId(Integer departmentId) {
        Optional<Department>department=departmentRepo.findById(departmentId);
        if(!department.isPresent()){
            throw new IllegalStateException("department not found");
        }
        Optional<AssemblyLine>assemblyLine=assemblyLineRepo.findByDepartment(department.get());
        if(!assemblyLine.isPresent()){
            throw new IllegalStateException("assemblyLine not found");
        }
        return assemblyLine.get();
    }

    public AssemblyLine getByStage(String stage) {
        Optional<AssemblyLine>assemblyLine=assemblyLineRepo.findByStage(stage);
        if(!assemblyLine.isPresent()){
            throw new IllegalStateException("assemblyLine not found");
        }
        return assemblyLine.get();

    }

    public Set<Employee> getEmployees(String name) {
        Department department= departmentService.getByName(name);
        Optional<AssemblyLine>assemblyLine=assemblyLineRepo.findByDepartment(department);
        if(!assemblyLine.isPresent()){
            throw new IllegalStateException("assemblyLine not found");
        }
        return assemblyLine.get().getDepartment().getEmployeeSet();
    }

    public void addAssemblyLine(Integer departmentId, AssemblyLine assemblyLine) {
        Optional<Department>department=departmentRepo.findById(departmentId);
        if(!department.isPresent()){
            throw new IllegalStateException("department not found");
        }
        Optional<AssemblyLine>assemblyLine1=assemblyLineRepo.findByDepartment(department.get());
        if(assemblyLine1.isPresent()){

            throw new IllegalStateException("departmentId used before");
        }
        assemblyLine.setDepartment(department.get());
        assemblyLineRepo.save(assemblyLine);
    }

    public void deleteByName(String name) {
        Department department= departmentService.getByName(name);
        Optional<AssemblyLine>assemblyLine=assemblyLineRepo.findByDepartment(department);
        if(!assemblyLine.isPresent()){
            throw new IllegalStateException("assemblyLine not found");
        }
        assemblyLineRepo.delete(assemblyLine.get());
        departmentService.deleteByName(assemblyLine.get().getDepartment().getName());
    }
    @Transactional
    public void updateAssemblyLine(String name, String stage) {
        Department department= departmentService.getByName(name);
        Optional<AssemblyLine>assemblyLine=assemblyLineRepo.findByDepartment(department);
        if(!assemblyLine.isPresent()){
            throw new IllegalStateException("assemblyLine not found");
        }
        Optional<AssemblyLine>assemblyLine1=assemblyLineRepo.findByStage(stage);
        if(assemblyLine1.isPresent()){
            throw new IllegalStateException("stage has been used before");
        }
        if(stage!=null&&
        stage.length()>0){
            assemblyLine.get().setStage(stage);
        }
    }
}
