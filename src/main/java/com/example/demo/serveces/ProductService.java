package com.example.demo.serveces;

import com.example.demo.model.EnrolledMaterials;
import com.example.demo.model.Material;
import com.example.demo.model.Product;
import com.example.demo.model.ProductsEnrolled;
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
    @Autowired
    public ProductService(ProductRepo productRepo, EnrolledMaterialsRepo enrolledMaterialsRepo, ProductsEnrolledRepo productsEnrolledRepo, MaterialService materialService) {
        this.productRepo = productRepo;
        this.enrolledMaterialsRepo = enrolledMaterialsRepo;
        this.productsEnrolledRepo = productsEnrolledRepo;
        this.materialService = materialService;
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
    }

    public void addEnrolledProduct(Integer productId, Integer enrolledProductId,
                                   ProductsEnrolled productsEnrolled) {
        Product product=getById(productId);
        Product enrolledProduct=getById(enrolledProductId);
        productsEnrolled.setProduct(product);
        productsEnrolled.setProductEnrolled(enrolledProduct);
        productsEnrolledRepo.save(productsEnrolled);
    }

    public void addProduct(Product product) {

         if(product.getHeight()<0 || product.getHeight()<0
                 ||product.getPrice()<0||product.getEstimatedTime()<0){
             throw new IllegalStateException("invalid input");
         }
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
                           String description, Integer width,
                           String photo, Integer height, Integer price) {
        Product product=getById(id);
        if(estimatedTime>0
                && estimatedTime!=null){
            product.setEstimatedTime(estimatedTime);
        }
        if(width>0
                && width!=null){
            product.setWidth(width);
        }
        if(height>0
                && height!=null){
            product.setHeight(height);
        }
        if(price>0
                && price!=null){
            product.setPrice(price);
        }
        if(description.length()>0
                && description!=null){
            product.setDescription(description);
        }
        if(photo.length()>0
                && photo!=null){
            product.setPhoto(photo);
        }

    }
}
