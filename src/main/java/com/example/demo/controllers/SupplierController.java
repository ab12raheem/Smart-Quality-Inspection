package com.example.demo.controllers;

import com.example.demo.model.MaterialSupplier;
import com.example.demo.model.Supplier;
import com.example.demo.serveces.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('Admin')")
    public List<Supplier> getAllSuppliers(){
        return supplierService.getAllSuppliers();
    }
    @GetMapping("getById/{id}")
    @PreAuthorize("hasRole('Admin')")
    public Supplier getSupplierById(@PathVariable Integer id){
        return supplierService.getById(id);
    }
    @GetMapping("getByUserName/{userName}")
    @PreAuthorize("hasRole('Admin')")
    public Supplier getSupplierByUserName(@PathVariable String userName){
        return supplierService.getByUserName(userName);
    }
    @PostMapping("addSupplier")
    @PreAuthorize("hasRole('Admin')")
    public void addSupplier(@RequestBody Supplier supplier){
        supplierService.addSupplier( supplier);
    }
    @DeleteMapping("deleteById/{id}")
    @PreAuthorize("hasRole('Admin')")
    public void deleteSupplier(@PathVariable Integer id){
        supplierService.deleteById(id);
    }
    @DeleteMapping("deleteByUserName/{userName}")
    @PreAuthorize("hasRole('Admin')")
    public void deleteByUserName(@PathVariable String userName){
        supplierService.deleteByUserName(userName);
    }
    @PutMapping("updateByUserName/{userName}")
    @PreAuthorize("hasRole('Admin')")
    public void updateSupplier(@PathVariable String userName,
                               @RequestParam (required = false) String companyName,
                               @RequestParam (required = false) String address,
                               @RequestParam (required = false) String postalCode,
                               @RequestParam (required = false) String phoneNumber,
                               @RequestParam (required = false) String fax,
                               @RequestParam (required = false) String paymentMethode,
                               @RequestParam (required = false) String discountType){
        supplierService.updateSupplier(userName,companyName,address,postalCode,phoneNumber,fax,
                paymentMethode,discountType);
    }
    @GetMapping("getMaterials/{userName}")
    public List<MaterialSupplier>getMaterials(@PathVariable String userName){
        return supplierService.getMaterials(userName);
    }


}
