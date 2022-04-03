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

    public WareHouse getByDepartmentId(Integer departmentId) {
        Optional<Department>department=departmentRepo.findById(departmentId);
        if(!department.isPresent()){
            throw new IllegalStateException("department not found");
        }
        Optional<WareHouse>wareHouse=wareHouseRepo.findByDepartment(department.get());
        if(wareHouse.isPresent()){
            throw new IllegalStateException("wareHouse not found");
        }
        return wareHouse.get();
    }

    public void addWareHouse(Integer departmentId, WareHouse wareHouse) {
        Optional<Department>department=departmentRepo.findById(departmentId);
        if(!department.isPresent()){
            throw new IllegalStateException("department not found");
        }
        Optional<WareHouse>wareHouse1=wareHouseRepo.findByDepartment(department.get());
        if(wareHouse1.isPresent()){

            throw new IllegalStateException("departmentId used before");
        }
        wareHouse.setDepartment(department.get());
        wareHouseRepo.save(wareHouse);

    }

    public Set<Employee> getAllEmployees(Integer id) {
        Optional<WareHouse>wareHouse=wareHouseRepo.findById(id);
        if(!wareHouse.isPresent()){
            throw new IllegalStateException("wareHouse not found");
        }
        return wareHouse.get().getDepartment().getEmployeeSet();
    }

    public Set<Material> getMaterials(Integer id) {
        Optional<WareHouse>wareHouse=wareHouseRepo.findById(id);
        if(!wareHouse.isPresent()){
            throw new IllegalStateException("wareHouse not found");
        }
        return wareHouse.get().getMaterials();

    }
    public Set<Product> getProducts(Integer id) {
        Optional<WareHouse>wareHouse=wareHouseRepo.findById(id);
        if(!wareHouse.isPresent()){
            throw new IllegalStateException("wareHouse not found");
        }
        return wareHouse.get().getProducts();

    }

    public void deleteById(Integer id) {
        Optional<WareHouse>wareHouse=wareHouseRepo.findById(id);
        if(!wareHouse.isPresent()){
            throw new IllegalStateException("warehouse not found");
        }

        Set<Product> products=wareHouse.get().getProducts();
        for(Product product: products){
            product.setWareHouse(null);
        }
        Set<Material> materials=wareHouse.get().getMaterials();
        for(Material material: materials){
            System.out.println(material.toString());
            material.setWareHouse(null);
        }
        wareHouseRepo.delete(wareHouse.get());
        departmentService.deleteByID(wareHouse.get().getDepartment().getId());

    }
    @Transactional
    public void updateWareHouse(Integer id, Integer capacity) {
        Optional<WareHouse>wareHouse=wareHouseRepo.findById(id);
        if(!wareHouse.isPresent()){
            throw new IllegalStateException("wareHouse not found");
        }
        if(capacity!=null &&
                !Objects.equals(wareHouse.get().getCapacity(), capacity)){
            wareHouse.get().setCapacity(capacity);
        }

    }
}
