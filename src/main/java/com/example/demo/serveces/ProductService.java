package com.example.demo.serveces;

import com.example.demo.model.*;
import com.example.demo.repositries.EnrolledMaterialsRepo;
import com.example.demo.repositries.ProductRepo;
import com.example.demo.repositries.ProductsEnrolledRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepo productRepo;
    private final EnrolledMaterialsRepo enrolledMaterialsRepo;
    private final ProductsEnrolledRepo productsEnrolledRepo;
    private final MaterialService materialService;
    private final WareHouseService wareHouseService;
    @Autowired
    public ProductService(ProductRepo productRepo, EnrolledMaterialsRepo enrolledMaterialsRepo, ProductsEnrolledRepo productsEnrolledRepo, MaterialService materialService, WareHouseService wareHouseService) {
        this.productRepo = productRepo;
        this.enrolledMaterialsRepo = enrolledMaterialsRepo;
        this.productsEnrolledRepo = productsEnrolledRepo;
        this.materialService = materialService;
        this.wareHouseService = wareHouseService;
    }

    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    public Product getById(Integer id) {
        Optional<Product> product=productRepo.findById(id);
        if(!product.isPresent()){
            throw new IllegalStateException("product not found");
        }
        return product.get();
    }


    public List<Product> getByPriceDesc() {
        return productRepo.findByOrderPriceDesc();
    }

    public List<EnrolledMaterials> getEnrolledMaterials(Integer id) {
        Optional<Product>product=productRepo.findById(id);
        if(!product.isPresent()){
            throw new IllegalStateException("product not found");
        }
        return enrolledMaterialsRepo.findAllByProduct(product.get());

    }

    public List<ProductsEnrolled> getProductsEnrolled(Integer id) {
        Optional<Product>product=productRepo.findById(id);
        if(!product.isPresent()){
            throw new IllegalStateException("product not found");
        }
        return productsEnrolledRepo.findAllByProduct(product.get());
    }

    public List<Product> getByPriceAsc() {
        return productRepo.findByOrderPriceAsc();
    }

    public void addMaterial(Integer materialId, Integer productId,
                            EnrolledMaterials enrolledMaterials) {
        Product product=getById(productId);
        Material material= materialService.getById(materialId);
        enrolledMaterials.setMaterial(material);
        enrolledMaterials.setProduct(product);
        enrolledMaterialsRepo.save(enrolledMaterials);
        product.setPrice();
        productRepo.save(product);
    }

    public void addEnrolledProduct(Integer productId, Integer enrolledProductId,
                                   ProductsEnrolled productsEnrolled) {
        Product product=getById(productId);
        Product enrolledProduct=getById(enrolledProductId);
        productsEnrolled.setProduct(product);
        productsEnrolled.setProductEnrolled(enrolledProduct);
        productsEnrolledRepo.save(productsEnrolled);
        product.setPrice();
        productRepo.save(product);
    }

    public void addProduct(Product product,String name) {
        WareHouse wareHouse=wareHouseService.getByDepartmentName(name);

         if(product.getHeight()<0.0 || product.getHeight()<0.0
                 ||product.getEstimatedTime()<0.0){
             throw new IllegalStateException("invalid input");
         }

         product.setWareHouse(wareHouse);
         productRepo.save(product);

    }

    public void deleteById(Integer id) {
        Product product=getById(id);
        enrolledMaterialsRepo.deleteAllByProduct(product);
        productsEnrolledRepo.deleteAllByProduct(product);
        productRepo.delete(product);

    }
    @Transactional
    public void updateById(Integer id, Integer estimatedTime,
                           String description, Double width,
                           String photo, Double height, Double percent) {
        Product product=getById(id);
        if(estimatedTime!=null &&estimatedTime>0){
            product.setEstimatedTime(estimatedTime);
        }
        if(width!=null
                &&width>0
                 ){
            product.setWidth(width);
        }
        if(percent!=null
                &&percent>0
        ){
            product.setPercent(percent);
            product.setPrice();
        }

        if(height!=null&&height>0
                ){
            product.setHeight(height);
        }

        if(description!=null && description.length()>0
                ){
            product.setDescription(description);
        }
        if(photo!=null&&photo.length()>0){
            product.setPhoto(photo);
        }

    }
}
