package com.example.demo.controllers;

import com.example.demo.model.Material;
import com.example.demo.model.MaterialSupplier;
import com.example.demo.serveces.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/material")
@CrossOrigin(origins = "http://localhost:3000")

public class MaterialController {
    private final MaterialService materialService;
    @Autowired
    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('Admin','Employee','Head')")
    public List<Material> getAllMaterials(){
        return materialService.getAll();
    }
    @GetMapping("byId/{id}")
    @PreAuthorize("hasAnyRole('Admin','Employee','Head')")
    public Material findById(@PathVariable Integer id){
        return materialService.getById(id);
    }
    @GetMapping("getSuppliers/{id}")
    @PreAuthorize("hasAnyRole('Admin','Head')")
    public List<MaterialSupplier> getSuppliers(@PathVariable Integer id){
        return materialService.getSuppliers(id);
    }
    @PostMapping("addMaterial/{departmentName}")
    @PreAuthorize("hasRole('Admin')")
    public void addMaterial(@RequestBody Material material,
                            @PathVariable String departmentName){
        materialService.addMaterial(material,departmentName);
    }
    @PostMapping("addSupplier/{supplierUserName}/{materialId}")
    @PreAuthorize("hasRole('Admin')")
    public void addSupplier(@PathVariable String supplierUserName,
                            @PathVariable Integer materialId,
                            @RequestBody MaterialSupplier materialSupplier){
        materialService.addSupplier(supplierUserName,materialId,materialSupplier);
    }
    @DeleteMapping("deleteById/{id}")
    @PreAuthorize("hasRole('Admin')")
    public void deleteById(@PathVariable Integer id){
        materialService.deleteById(id);
    }
    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('Admin')")
    public void updateMaterial(@PathVariable Integer id,
                               @RequestParam (required = false) Integer availability,
                               @RequestParam (required = false) String description,
                               @RequestParam (required = false) Integer count,
                               @RequestParam (required = false) String image,
                               @RequestParam (required = false) Integer wareHouseId ){
        materialService.updateMaterial(id,availability,description,count,image,wareHouseId);
    }


}
