package com.example.demo.serveces;

import com.example.demo.model.*;
import com.example.demo.repositries.MaterialSupplierRepo;
import com.example.demo.repositries.SupplierRepo;
import com.example.demo.repositries.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SupplierService {
    private final SupplierRepo supplierRepo;
    private final UserRepo userRepo;
    private final MaterialSupplierRepo materialSupplierRepo;
    @Autowired
    public SupplierService(SupplierRepo supplierRepo, UserRepo userRepo, MaterialSupplierRepo materialSupplierRepo) {
        this.supplierRepo = supplierRepo;
        this.userRepo = userRepo;
        this.materialSupplierRepo = materialSupplierRepo;
    }

    public List<Supplier> getAllSuppliers() {
    return supplierRepo.findAll();
    }

    public Supplier getById(Integer id) {
        Optional<Supplier> supplier =supplierRepo.findById(id);
        if(!supplier.isPresent()){
            throw new IllegalStateException("supplier not found");
        }
        return supplier.get();
    }

    public Supplier getByUserName(String userName) {
        Optional<User> user = userRepo.getByUserName(userName);
        if (!user.isPresent()) {
            throw new IllegalStateException("Customer not found");
        }
        Optional<Supplier> supplier = supplierRepo.getByUser(user.get());
        if (!supplier.isPresent()) {
            throw new IllegalStateException("supplier not found");
        }
        return supplier.get();
    }

    public void addSupplier(Integer userId, Supplier supplier) {
        Optional<User>user=userRepo.findById(userId);

        if(!user.isPresent()){
            throw new IllegalStateException("user not found");

        }

        if(user.get().getRole()!=0){
            throw new IllegalStateException("user has been used before");
        }
        User user1=user.get();
        user1.setRole(3);
        userRepo.save(user1);
        supplier.setUser(user1);
        supplierRepo.save(supplier);
    }

    public void deleteById(Integer id) {
        Optional<Supplier>supplier=supplierRepo.findById(id);
        if(!supplier.isPresent()){
            throw new IllegalStateException("supplier not found ");
        }
        Optional<User>user=userRepo.findById(supplier.get().getUser().getId());
        if(!user.isPresent()){
            throw new IllegalStateException("user not found ");
        }
        List<MaterialSupplier>materialSuppliers=materialSupplierRepo.findAllBySupplier(supplier.get());
        for(MaterialSupplier materialSupplier:materialSuppliers){
            materialSupplierRepo.delete(materialSupplier);
        }

        supplierRepo.deleteById(id);
        userRepo.delete(user.get());
    }

    public void deleteByUserName(String userName) {
        Optional<User>user=userRepo.getByUserName(userName);
        if(!user.isPresent()){
            throw new IllegalStateException("user not found ");
        }
        Optional<Supplier> supplier=supplierRepo.getByUser(user.get());
        if(!supplier.isPresent()){
            throw new IllegalStateException("supplier not found ");
        }
        List<MaterialSupplier>materialSuppliers=materialSupplierRepo.findAllBySupplier(supplier.get());
        for(MaterialSupplier materialSupplier:materialSuppliers){
            materialSupplierRepo.delete(materialSupplier);
        }
        supplierRepo.delete(supplier.get());
        userRepo.delete(user.get());
    }
    @Transactional
    public void updateSupplier(Integer id, String companyName, String address,
                               String postalCode, String phoneNumber, String fax,
                               String paymentMethode, String discountType) {
        Optional<Supplier>supplier=supplierRepo.findById(id);
        if(!supplier.isPresent()){
            throw new IllegalStateException("customer not found");
        }
        if(address!=null &&
                address.length()>0&&
                !Objects.equals(supplier.get().getAddress(),address)){
            supplier.get().setAddress(address);
        }
        if(phoneNumber!=null &&
                phoneNumber.length()>0&&
                !Objects.equals(supplier.get().getPhoneNumber(),phoneNumber)){
            supplier.get().setPhoneNumber(phoneNumber);
        }
        if(companyName!=null &&
                companyName.length()>0&&
                !Objects.equals(supplier.get().getCompanyName(),companyName)){
            supplier.get().setCompanyName(companyName);
        }
        if(postalCode!=null &&
                postalCode.length()>0&&
                !Objects.equals(supplier.get().getPostalCode(),postalCode)){
            supplier.get().setPostalCode(postalCode);
        }
        if(paymentMethode!=null &&
                paymentMethode.length()>0&&
                !Objects.equals(supplier.get().getPaymentMethode(),paymentMethode)){
            supplier.get().setPaymentMethode(paymentMethode);
        }
        if(fax!=null &&
                fax.length()>0&&
                !Objects.equals(supplier.get().getFax(),fax)){
            supplier.get().setFax(fax);
        }
        if(discountType!=null &&
                discountType.length()>0&&
                !Objects.equals(supplier.get().getDiscountType(),discountType)){
            supplier.get().setDiscountType(discountType);
        }
    }

    public List<MaterialSupplier> getMaterials(Integer id) {
        Supplier supplier=getById(id);
        return materialSupplierRepo.findAllBySupplier(supplier);
    }
}
