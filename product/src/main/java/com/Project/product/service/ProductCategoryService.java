package com.Project.product.service;

import com.Project.product.entity.ProductCategory;
import com.Project.product.repository.ProductCategoryReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductCategoryService {
    @Autowired
    private ProductCategoryReponsitory productCategoryReponsitory;
    public List<ProductCategory> findAll(){return productCategoryReponsitory.findAll();}
    public ProductCategory createProductCategory(ProductCategory productCategory) {return productCategoryReponsitory.save(productCategory);}
    public  void deletedProductCategory(Long id){productCategoryReponsitory.deleteById(id);}
    public ProductCategory updateProductCategory(Long id,ProductCategory productCategory){
        ProductCategory pr =productCategoryReponsitory.findById(id).get();
        pr.setName(productCategory.getName());
        return productCategoryReponsitory.save(pr);
    }
}
