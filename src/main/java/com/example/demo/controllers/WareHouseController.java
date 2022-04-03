package com.example.demo.controllers;

import com.example.demo.model.Employee;
import com.example.demo.model.Material;
import com.example.demo.model.Product;
import com.example.demo.model.WareHouse;
import com.example.demo.serveces.WareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/wareHouse")
public class WareHouseController {
    private final WareHouseService wareHouseService;
    @Autowired
    public WareHouseController(WareHouseService wareHouseService) {
        this.wareHouseService = wareHouseService;
    }
    @GetMapping
    public List<WareHouse> getAllWareHouses(){
        return wareHouseService.getAllWareHouses();
    }
    @GetMapping("byId/{id}")
    public WareHouse getById(@PathVariable Integer id){
        return wareHouseService.getById(id);
    }
    @GetMapping("byDepartmentId/{departmentId}")
    public WareHouse getByDepartmentId(@PathVariable Integer departmentId){
        return wareHouseService.getByDepartmentId(departmentId);
    }
    @GetMapping("getEmployees/{id}")
    public Set<Employee> getEmployees(@PathVariable Integer id){
        return wareHouseService.getAllEmployees(id);
    }
    @GetMapping("getMaterials/{id}")
    public Set<Material> getMaterials(@PathVariable Integer id){
        return wareHouseService.getMaterials(id);
    }
    @GetMapping("getProducts/{id}")
    public Set<Product> getProducts(@PathVariable Integer id ){
        return wareHouseService.getProducts(id);
    }
    @PostMapping("add/{departmentId}")
    public void addWareHouse(@PathVariable Integer departmentId,
                              @RequestBody WareHouse wareHouse){
        wareHouseService.addWareHouse(departmentId,wareHouse);
    }
    @DeleteMapping("deleteById/{id}")
    public void deleteById(@PathVariable Integer id){
        wareHouseService.deleteById(id);
    }
    @PutMapping("update/{id}")
    public void updateWareHouse(@PathVariable Integer id,
                                @RequestParam  Integer capacity
                                ){
        wareHouseService.updateWareHouse(id,capacity);
    }


}
