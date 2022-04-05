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

    public void addSupplier( Supplier supplier) {

        Optional<User> user = userRepo.getByUserName(supplier.getUser().getUserName());
        Optional<User> user1 = userRepo.getByEmail(supplier.getUser().getEmail());

        if(user.isPresent()){
            throw new IllegalStateException("userName has been used before");

        }
        if(user1.isPresent()){
            throw new IllegalStateException("email has been used before");

        }



        supplier.getUser().setRole(3);
        userRepo.save(supplier.getUser());
        supplier.setUser(supplier.getUser());
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
    public void updateSupplier(String userName, String companyName, String address,
                               String postalCode, String phoneNumber, String fax,
                               String paymentMethode, String discountType) {
        Supplier supplier=getByUserName(userName);

        if(address!=null &&
                address.length()>0&&
                !Objects.equals(supplier.getAddress(),address)){
            supplier.setAddress(address);
        }
        if(phoneNumber!=null &&
                phoneNumber.length()>0&&
                !Objects.equals(supplier.getPhoneNumber(),phoneNumber)){
            supplier.setPhoneNumber(phoneNumber);
        }
        if(companyName!=null &&
                companyName.length()>0&&
                !Objects.equals(supplier.getCompanyName(),companyName)){
            supplier.setCompanyName(companyName);
        }
        if(postalCode!=null &&
                postalCode.length()>0&&
                !Objects.equals(supplier.getPostalCode(),postalCode)){
            supplier.setPostalCode(postalCode);
        }
        if(paymentMethode!=null &&
                paymentMethode.length()>0&&
                !Objects.equals(supplier.getPaymentMethode(),paymentMethode)){
            supplier.setPaymentMethode(paymentMethode);
        }
        if(fax!=null &&
                fax.length()>0&&
                !Objects.equals(supplier.getFax(),fax)){
            supplier.setFax(fax);
        }
        if(discountType!=null &&
                discountType.length()>0&&
                !Objects.equals(supplier.getDiscountType(),discountType)){
            supplier.setDiscountType(discountType);
        }
    }

    public List<MaterialSupplier> getMaterials(String userName) {
        Supplier supplier=getByUserName(userName);
        return materialSupplierRepo.findAllBySupplier(supplier);
    }
}
