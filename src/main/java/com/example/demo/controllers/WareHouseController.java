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
    @GetMapping("byDepartmentName/{departmentName}")
    public WareHouse getByDepartmentName(@PathVariable String departmentName){
        return wareHouseService.getByDepartmentName(departmentName);
    }
    @GetMapping("getEmployees/{name}")
    public Set<Employee> getEmployees(@PathVariable String name){
        return wareHouseService.getAllEmployees(name);
    }
    @GetMapping("getMaterials/{name}")
    public Set<Material> getMaterials(@PathVariable String name){
        return wareHouseService.getMaterials(name);
    }
    @GetMapping("getProducts/{name}")
    public Set<Product> getProducts(@PathVariable String name){
        return wareHouseService.getProducts(name);
    }
    @PostMapping("add")
    public void addWareHouse(
                              @RequestBody WareHouse wareHouse){
        wareHouseService.addWareHouse(wareHouse);
    }
    @DeleteMapping("deleteByName/{name}")
    public void deleteByName(@PathVariable String name){
        wareHouseService.deleteByName(name);
    }
    @PutMapping("update/{name}")
    public void updateWareHouse(@PathVariable String name,
                                @RequestParam  Integer capacity
                                ){
        wareHouseService.updateWareHouse(name,capacity);
    }


}
