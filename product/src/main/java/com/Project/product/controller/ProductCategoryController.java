package com.Project.product.controller;

import com.Project.product.entity.ProductCategory;
import com.Project.product.service.ProductCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductCategoryController {
    static final Logger log =
            LoggerFactory.getLogger(ProductCategoryController.class);

    @Autowired
    public ProductCategoryService productCategoryService;
    @PostMapping("/admin/create-ProductCategory")
    public ResponseEntity createProductCategory(@RequestBody ProductCategory productCategory){

        try {
ProductCategory createProductCategory = productCategoryService.createProductCategory(productCategory);
            log.info("ProductCategory ID:" + createProductCategory.getId() + "was added successfully");
            return ResponseEntity.ok().body(createProductCategory);
        } catch (Exception e) {
            log.error("Error in createProductCategory() method: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/get-all-productCategory")
    public List<ProductCategory> getAllProductCategory() {
        try {
            return productCategoryService.findAll();
        } catch (Exception e) {
            log.error("Error in findAll() method: " + e.getMessage());
            return null;
        }
    }
    @PutMapping("/admin/update-productCategory/{id}")
    public ResponseEntity updateProductCategory(@PathVariable(name = "id") Long productCategoryId,
                                       @RequestBody ProductCategory productCategory) {
        try{
            ProductCategory updatedProductCategory = productCategoryService.updateProductCategory(productCategoryId, productCategory);
            log.info("ProductCategory ID:" + updatedProductCategory.getId() + "was updated successfully");
            return ResponseEntity.ok().body(updatedProductCategory);
        } catch (Exception e) {
            log.error("Error in editCategory() method: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/admin/deleted-productCategory/{id}")
    public ResponseEntity deletedProductCategory(@PathVariable(name = "id") Long productCategoryId){
        try{
            productCategoryService.deletedProductCategory(productCategoryId);
            String message =" ProductCategory with ID: "+ productCategoryId+"was deleted successfully";
            log.info(message);
            return ResponseEntity.ok().body((message));
        }catch (Exception e){
            log.error("Error in deletedProductCategory() method :" +e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}




