package com.example.demo.controllers;

import com.example.demo.model.EnrolledMaterials;
import com.example.demo.model.Product;
import com.example.demo.model.ProductsEnrolled;
import com.example.demo.serveces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getProducts();
    }
    @GetMapping("byId/{id}")
    public Product getById(@PathVariable Integer id){
        return productService.getById(id);
    }
    @GetMapping("ByPriceDesc")
    public List<Product> getOrderedByPrice(){
        return productService.getByPriceDesc();
    }
    @GetMapping("ByPriceAsc")
    public List<Product> getOrderedByPriceA(){
        return productService.getByPriceAsc();
    }
    @GetMapping("getMaterial/{id}")
    public List<EnrolledMaterials> getEnrolledMaterials(@PathVariable Integer id){
        return productService.getEnrolledMaterials(id);
    }
    @GetMapping("getProductsEnrolled/{id}")
    public  List<ProductsEnrolled>getProductsEnrolled(@PathVariable Integer id){
        return productService.getProductsEnrolled(id);
    }
    @PostMapping("addMaterial/{materialId}/{productId}")
    public void addMaterial(@PathVariable Integer materialId,
                            @PathVariable Integer productId,
                            @RequestBody EnrolledMaterials enrolledMaterials){
        productService.addMaterial(materialId,productId,enrolledMaterials);
    }
    @PostMapping("addEnrolledProduct/{productId}/{enrolledProductId}")
    public void addProductEnrolled(@PathVariable Integer productId,
                                   @PathVariable Integer enrolledProductId,
                                   @RequestBody ProductsEnrolled productsEnrolled)
    {
        productService.addEnrolledProduct(productId,enrolledProductId,productsEnrolled);
    }
    @PostMapping("addProduct")
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);
    }
    @DeleteMapping("deleteById/{id}")
    public void deleteProductById(@PathVariable Integer id){
        productService.deleteById(id);
    }

    @PutMapping("updateById/{id}")
    public void updateById(@PathVariable Integer id,
                           @RequestParam (required = false) Integer estimatedTime,
                           @RequestParam (required = false) String description,
                           @RequestParam (required = false) Integer width,
                           @RequestParam (required = false) Integer height,
                           @RequestParam (required = false) String photo,
                           @RequestParam (required = false) Integer price
                           ){
        productService.updateById(id, estimatedTime,description,width,photo,height,price);

    }




    }


