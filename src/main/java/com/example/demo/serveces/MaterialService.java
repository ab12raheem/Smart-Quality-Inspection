package com.example.demo.serveces;

import com.example.demo.model.Material;
import com.example.demo.model.MaterialSupplier;
import com.example.demo.model.Supplier;
import com.example.demo.model.WareHouse;
import com.example.demo.repositries.MaterialSupplierRepo;
import com.example.demo.repositries.MaterialsRepo;
import com.example.demo.repositries.WareHouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {
    private final MaterialsRepo materialsRepo;
    private final MaterialSupplierRepo materialSupplierRepo;
    private final WareHouseRepo wareHouseRepo;
    private final SupplierService supplierService;


    @Autowired
    public MaterialService(MaterialsRepo materialsRepo, MaterialSupplierRepo materialSupplierRepo, WareHouseRepo wareHouseRepo, SupplierService supplierService) {
        this.materialsRepo = materialsRepo;
        this.materialSupplierRepo = materialSupplierRepo;
        this.wareHouseRepo = wareHouseRepo;
        this.supplierService = supplierService;
    }

    public List<Material> getAll() {
        return materialsRepo.findAll();
    }

    public Material getById(Integer id) {
        Optional<Material> material = materialsRepo.findById(id);
        if (!material.isPresent()) {
            throw new IllegalStateException("material not found");
        }
        return material.get();
    }

    public List<MaterialSupplier> getSuppliers(Integer id) {
        Optional<Material> material = materialsRepo.findById(id);
        if (!material.isPresent()) {
            throw new IllegalStateException("material not found");
        }
        return materialSupplierRepo.findAllByMaterial(material.get());

    }

    public void addMaterial(Material material, Integer wareHouseId) {
        Optional<WareHouse> wareHouse = wareHouseRepo.findById(wareHouseId);
        if (!wareHouse.isPresent()) {
            throw new IllegalStateException("wareHouse not found");
        }
        material.setWareHouse(wareHouse.get());
        materialsRepo.save(material);
    }

    public void addSupplier(Integer supplierId, Integer materialId, MaterialSupplier materialSupplier) {
        Supplier supplier = supplierService.getById(supplierId);
        Material material = getById(materialId);
        materialSupplier.setMaterial(material);
        materialSupplier.setSupplier(supplier);
        if (materialSupplier.getPrice() < 0 || materialSupplier.getPrice() == null
                || materialSupplier.getPrice() > Integer.MAX_VALUE) {
            throw new IllegalStateException("price is not valid");
        }
        materialSupplierRepo.save(materialSupplier);
    }

    public void deleteById(Integer id) {
        Material material = getById(id);
        materialsRepo.delete(material);
    }
    @Transactional
    public void updateMaterial(Integer id, Integer availability,
                               String description, Integer count,
                               String image, Integer wareHouseId) {

        Material material = getById(id);
        if(wareHouseId!=null && wareHouseId>0) {
            Optional<WareHouse> wareHouse = wareHouseRepo.findById(wareHouseId);
            if (!wareHouse.isPresent()) {
                throw new IllegalStateException("warehouse not found");
            }
            material.setWareHouse(wareHouse.get());
        }
        if (availability != null &&
                availability > 0) {
            material.setAvailability(availability);
        }
        if (description != null &&
                description.length() > 0) {
            material.setDescription(description);
        }
        if (image != null &&
                image.length() > 0) {
            material.setImage(image);
        }
        if (count != null &&
                count > 0) {
            material.setCount(count);
        }





    }
}