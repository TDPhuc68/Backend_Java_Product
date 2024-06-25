package com.Project.product.controller;

import com.Project.product.entity.Product;
import com.Project.product.repository.ProductReponsitory;
import com.Project.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class ProductController {
    static final Logger log =
            LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;
    @Autowired
    private ProductReponsitory productReponsitory;
    @PostMapping(value="/admin/create-Product")
    public ResponseEntity createProduct(@RequestBody Product product){
        Product addProduct = new Product();
        try {
            addProduct.setName(product.getName());
            addProduct.setPrice(product.getPrice());
            addProduct.setImage(product.getImage());
            addProduct.setDescription(product.getDescription());
            addProduct.setBrand(product.getBrand());
            addProduct.setQuantity(product.getQuantity());
            productService.createProduct(addProduct);

            log.info("Product ID:" + addProduct.getId() + "was added successfully");
            return ResponseEntity.ok().body(addProduct);
        } catch (Exception e) {
            log.error("Error in createProduct() method: " + e.getMessage());
        return ResponseEntity.status(500).body(e.getMessage());

        }
    }
    @GetMapping("/get-all-product")
    public List<Product> getAllProduct() {
        try {
            return productService.getAllProduct();
        } catch (Exception e) {
            log.error("Error in getAllProduct() method: " + e.getMessage());
            return null;
        }
    }
    @PutMapping("/admin/update-product/{id}")
    public ResponseEntity updateProduct(@PathVariable(name ="id") Long productId,
                                                 @RequestBody Product product) {
        try {
            Product updateProduct = productService.updateProduct(productId, product);
            log.info("Product ID:" + updateProduct.getId() + "was updated successfully");
            return  ResponseEntity.ok().body(updateProduct);
        } catch (Exception e) {
            log.error("Error in updateProduct() method :" + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/admin/deleted-product/{id}")
    public ResponseEntity deletedProduct(@PathVariable("id") Long productId){

        try {
            productService.deletedProduct(productId);
            String message = "Product with ID: " + productId + " was deleted successfully";
            log.info(message);
            return ResponseEntity.ok().body(message);
        } catch (Exception e) {
            log.error("Error in deleteProduct() method: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
