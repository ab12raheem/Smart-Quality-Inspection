package com.example.demo.controllers;

import com.example.demo.model.MaterialSupplier;
import com.example.demo.model.Supplier;
import com.example.demo.serveces.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/supplier")
public class SupplierController {
    private final SupplierService supplierService;
    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }
    @GetMapping
    public List<Supplier> getAllSuppliers(){
        return supplierService.getAllSuppliers();
    }
    @GetMapping("getById/{id}")
    public Supplier getSupplierById(@PathVariable Integer id){
        return supplierService.getById(id);
    }
    @GetMapping("getByUserName/{userName}")
    public Supplier getSupplierByUserName(@PathVariable String userName){
        return supplierService.getByUserName(userName);
    }
    @PostMapping("addSupplier/{userId}")
    public void addSupplier(@PathVariable Integer userId,
                            @RequestBody Supplier supplier){
        supplierService.addSupplier(userId, supplier);
    }
    @DeleteMapping("deleteById/{id}")
    public void deleteSupplier(@PathVariable Integer id){
        supplierService.deleteById(id);
    }
    @DeleteMapping("deleteByUserName/{userName}")
    public void deleteByUserName(@PathVariable String userName){
        supplierService.deleteByUserName(userName);
    }
    @PutMapping("updateById/{id}")
    public void updateSupplier(@PathVariable Integer id,
                               @RequestParam (required = false) String companyName,
                               @RequestParam (required = false) String address,
                               @RequestParam (required = false) String postalCode,
                               @RequestParam (required = false) String phoneNumber,
                               @RequestParam (required = false) String fax,
                               @RequestParam (required = false) String paymentMethode,
                               @RequestParam (required = false) String discountType){
        supplierService.updateSupplier(id,companyName,address,postalCode,phoneNumber,fax,
                paymentMethode,discountType);
    }
    @GetMapping("getMaterials/{id}")
    public List<MaterialSupplier>getMaterials(@PathVariable Integer id){
        return supplierService.getMaterials(id);
    }


}
