package com.example.demo.serveces;

import com.example.demo.model.*;
import com.example.demo.repositries.DepartmentRepo;
import com.example.demo.repositries.WareHouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class WareHouseService {
    private final WareHouseRepo wareHouseRepo;
    private final DepartmentRepo departmentRepo;
    private final DepartmentService departmentService;
    @Autowired
    public WareHouseService(WareHouseRepo wareHouseRepo, DepartmentRepo departmentRepo, DepartmentService departmentService) {
        this.wareHouseRepo = wareHouseRepo;
        this.departmentRepo = departmentRepo;
        this.departmentService = departmentService;
    }

    public List<WareHouse> getAllWareHouses() {
        return wareHouseRepo.findAll();
    }

    public WareHouse getById(Integer id) {
        Optional<WareHouse>wareHouse=wareHouseRepo.findById(id);
        if(!wareHouse.isPresent()){
            throw new IllegalStateException("WareHouse not found");
        }
        return wareHouse.get();

    }

    public WareHouse getByDepartmentName(String name) {
        Optional<Department>department=departmentRepo.findByName(name);
        if(!department.isPresent()){
            throw new IllegalStateException("department not found");
        }
        Optional<WareHouse>wareHouse=wareHouseRepo.findByDepartment(department.get());
        if(!wareHouse.isPresent()){
            throw new IllegalStateException("wareHouse not found");
        }
        return wareHouse.get();
    }

    public void addWareHouse( WareHouse wareHouse) {
        Optional<Department>department=departmentRepo.findByName(wareHouse.getDepartment().getName());
        if(department.isPresent()){
            throw new IllegalStateException("department used before");
        }

        wareHouse.setDepartment(wareHouse.getDepartment());
        departmentRepo.save(wareHouse.getDepartment());
        wareHouseRepo.save(wareHouse);

    }

    public Set<Employee> getAllEmployees(String name) {
        WareHouse wareHouse=getByDepartmentName(name);

        return wareHouse.getDepartment().getEmployeeSet();
    }

    public Set<Material> getMaterials(String name) {
        WareHouse wareHouse=getByDepartmentName(name);
        return wareHouse.getMaterials();

    }
    public Set<Product> getProducts(String name) {
       WareHouse wareHouse=getByDepartmentName(name);
        return wareHouse.getProducts();

    }

    public void deleteByName(String name) {
        WareHouse wareHouse=getByDepartmentName(name);


        Set<Product> products=wareHouse.getProducts();
        for(Product product: products){
            product.setWareHouse(null);
        }
        Set<Material> materials=wareHouse.getMaterials();
        for(Material material: materials){
            System.out.println(material.toString());
            material.setWareHouse(null);
        }
        wareHouseRepo.delete(wareHouse);
        departmentService.deleteByName(wareHouse.getDepartment().getName());

    }
    @Transactional
    public void updateWareHouse(String name, Integer capacity) {
        WareHouse wareHouse=getByDepartmentName(name);
        if(capacity!=null &&
                !Objects.equals(wareHouse.getCapacity(), capacity)){
            wareHouse.setCapacity(capacity);
        }

    }
}
